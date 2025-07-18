package dev.qna.qna_session_service.Client;

import dev.qna.qna_session_service.dto.BaseResponseDTO;
import dev.qna.qna_session_service.dto.llm.EvaluationRequestDTO;
import dev.qna.qna_session_service.dto.llm.EvaluationResponseDTO;
import dev.qna.qna_session_service.dto.llm.QuestionRequestDTO;
import dev.qna.qna_session_service.dto.llm.QuestionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "qna-question-service", url = "${llm.service.base-url}")
public interface LLMClient {

    //to communicate with LLMService

    @PostMapping("/llm/generate")
    BaseResponseDTO<QuestionResponseDTO> generateQuestion(@RequestBody QuestionRequestDTO request);

    @PostMapping("/llm/evaluate")
    BaseResponseDTO<EvaluationResponseDTO> evaluateAnswer(@RequestBody EvaluationRequestDTO request);
}
