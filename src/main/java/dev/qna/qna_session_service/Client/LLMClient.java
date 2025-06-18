package dev.qna.qna_session_service.Client;

import dev.qna.qna_session_service.dto.BaseResponseDTO;
import dev.qna.qna_session_service.dto.llm.EvaluationRequestDTO;
import dev.qna.qna_session_service.dto.llm.EvaluationResponseDTO;
import dev.qna.qna_session_service.dto.llm.QuestionRequestDTO;
import dev.qna.qna_session_service.dto.llm.QuestionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "qna-question-service", url = "http://localhost:8087/llm")
public interface LLMClient {

    //to communicate with LLMService

    @PostMapping("/generate")
    BaseResponseDTO<QuestionResponseDTO> generateQuestion(@RequestBody QuestionRequestDTO request);

    @PostMapping("/evaluate")
    BaseResponseDTO<EvaluationResponseDTO> evaluateAnswer(@RequestBody EvaluationRequestDTO request);
}
