package dev.qna.qna_session_service.tutorsessionModule.client;

import dev.qna.qna_session_service.tutorsessionModule.dto.llm.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
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
            BaseResponseDTO<GenerateQuestionResponseDTO> response = webClient.post()
                    .uri("/llm/generateTutor")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<BaseResponseDTO<GenerateQuestionResponseDTO>>() {
                    })
                    .block();

            return response != null && response.getData() != null
                    ? response.getData().getQuestion()
                    : null;
        }catch (Exception ex){
            log.error("Error generating question from LLM", ex);
            throw new RuntimeException("Failed to generate question");
        }
    }

    public EvaluateAnswerResponse evaluateAnswer(String question, String answer){
        try{
            EvaluateAnswerRequest request = new EvaluateAnswerRequest(question, answer);

            BaseResponseDTO<EvaluateAnswerResponse> response = webClient.post()
                    .uri("/llm/evaluate")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<BaseResponseDTO<EvaluateAnswerResponse>>() {})
                    .block();

            return response != null ? response.getData() : null;
        } catch (Exception ex){
            log.error("Error evaluating answer via LLM", ex);
            throw  new RuntimeException("Failed to evaluate answer");
        }
    }
}
