package dev.qna.qna_session_service.tutorSession.dto.llm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateAnswerRequest {
    private String question;
    private String answer;
}
