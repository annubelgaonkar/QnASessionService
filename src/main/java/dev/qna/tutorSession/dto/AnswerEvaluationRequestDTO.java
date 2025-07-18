package dev.qna.tutorSession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AnswerEvaluationRequestDTO {

    private Long sessionId;
    private QuestionAnswerDTO question;

}
