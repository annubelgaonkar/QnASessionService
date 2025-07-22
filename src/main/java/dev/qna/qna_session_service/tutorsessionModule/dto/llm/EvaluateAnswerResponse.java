package dev.qna.qna_session_service.tutorsessionModule.dto.llm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateAnswerResponse {
    private String feedback;
    private String nextQuestion;
}
