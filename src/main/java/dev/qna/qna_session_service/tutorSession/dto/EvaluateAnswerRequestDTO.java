package dev.qna.qna_session_service.tutorSession.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateAnswerRequestDTO {
    private Long sessionId;
    private QuestionAnswerDTO question;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionAnswerDTO{
        private Long questionId;
        private String answer;
    }
}
