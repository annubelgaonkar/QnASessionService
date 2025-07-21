package dev.qna.tutorSession.client;

import dev.qna.tutorSession.dto.llm.EvaluateAnswerRequest;
import dev.qna.tutorSession.dto.llm.EvaluateAnswerResponse;
import dev.qna.tutorSession.dto.llm.GenerateQuestionRequestDTO;
import dev.qna.tutorSession.dto.llm.GenerateQuestionResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class LLMClient {

    private final WebClient webClient;

    public String generateFirstQuestion(String topic){
        try{
            GenerateQuestionRequestDTO request =
                    new GenerateQuestionRequestDTO(topic);
            GenerateQuestionResponseDTO response = webClient.post()
                    .uri("/generate-question")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(GenerateQuestionResponseDTO.class)
                    .block();

            return response != null ? response.getQuestion() : null;
        }catch (Exception ex){
            log.error("Error generating question from LLM", ex);
            throw new RuntimeException("Failed to generate question");
        }
    }

    public EvaluateAnswerResponse evaluateAnswer(String question, String answer){
        try{
            EvaluateAnswerRequest request = new EvaluateAnswerRequest(question, answer);
            return webClient.post()
                    .uri("/evaluate")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(EvaluateAnswerResponse.class)
                    .block();
        } catch (Exception ex){
            log.error("Error evaluating answer via LLM", ex);
            throw  new RuntimeException("Failed to evaluate answer");
        }
    }
}
