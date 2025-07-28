package dev.qna.qna_session_service.tutorsessionModule.client;

import dev.qna.qna_session_service.dto.llm.EvaluationRequestDTO;
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

    public String generateFirstQuestion(String topic) {
        try {
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
        } catch (Exception ex) {
            log.error("Error generating question from LLM", ex);
            throw new RuntimeException("Failed to generate question");
        }
    }

    public EvaluateAnswerResponse evaluateAnswer(String question, String answer) {
        try {
            EvaluateAnswerRequest request = new EvaluateAnswerRequest(question, answer);

            BaseResponseDTO<EvaluateAnswerResponse> response = webClient.post()
                    .uri("/llm/evaluate")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<BaseResponseDTO<EvaluateAnswerResponse>>() {})
                    .block();

            if (response == null || response.getData() == null) {
                log.error("LLM response is null or missing data for question: {}", question);
                throw new IllegalStateException("LLM returned no data");
            }

            EvaluateAnswerResponse data = response.getData();

            if (data.getNextQuestion() == null || data.getNextQuestion().isBlank()) {
                log.warn("LLM returned empty nextQuestion for Q='{}' /A='{}'", question, answer);
                data.setNextQuestion("Sorry, could not generate a follow-up question. Try again.");
            }
            return data;
        } catch (Exception ex) {
            log.error("Error evaluating answer via LLM", ex);
            throw new RuntimeException("Failed to evaluate answer");
        }
    }

    public EvaluationResponseForTutorDTO evaluateAnswerForTutor(String topic, String question,String userAnswer){
        try{
            EvaluationRequestDTO request = new EvaluationRequestDTO(topic, question, userAnswer);
            BaseResponseDTO<EvaluationResponseForTutorDTO> response = webClient.post()
                    .uri("/llm/evaluateForTutor")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<BaseResponseDTO<EvaluationResponseForTutorDTO>>() {})
                    .block();
            if (response == null || response.getData() == null) {
                log.error("LLM tutor evaluation response is null");
                throw new IllegalStateException("Failed to evaluate answer via tutor path");
            }

            return response.getData();
        } catch (Exception ex) {
            log.error("Error in evaluateAnswerForTutor", ex);
            throw new RuntimeException("LLM tutor evaluation failed");
        }
    }
}
