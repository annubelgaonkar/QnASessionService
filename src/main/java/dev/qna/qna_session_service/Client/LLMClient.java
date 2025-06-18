package dev.qna.qna_session_service.Client;

import dev.qna.qna_session_service.dto.llm.EvaluationRequestDTO;
import dev.qna.qna_session_service.dto.llm.EvaluationResponseDTO;
import dev.qna.qna_session_service.dto.llm.QuestionRequestDTO;
import dev.qna.qna_session_service.dto.llm.QuestionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "qna-question-service", url = "http://localhost:8087")
public interface LLMClient {

    //to communicate with LLMService

    @PostMapping("/llm/generate")
    QuestionResponseDTO generateQuestion(@RequestBody QuestionRequestDTO request);

    @PostMapping("/llm/evaluate")
    EvaluationResponseDTO evaluateAnswer(@RequestBody EvaluationRequestDTO request);
}
