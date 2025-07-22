package dev.qna.qna_session_service.tutorsessionModule.client;

import dev.qna.qna_session_service.tutorsessionModule.dto.llm.EvaluateAnswerRequest;
import dev.qna.qna_session_service.tutorsessionModule.dto.llm.EvaluateAnswerResponse;
import dev.qna.qna_session_service.tutorsessionModule.dto.llm.GenerateQuestionRequestDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.llm.GenerateQuestionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
@AllArgsConstructor
public class LLMClient {

    private final WebClient webClient;

    public String generateFirstQuestion(String topic){
        try{
            GenerateQuestionRequestDTO request =
                    new GenerateQuestionRequestDTO(topic);
            GenerateQuestionResponseDTO response = webClient.post()
                    .uri("/llm/generate")
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
                    .uri("/llm/evaluate")
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
