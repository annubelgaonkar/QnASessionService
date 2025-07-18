package dev.qna.tutorSession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEvaluationRequestDTO {

    private Long sessionId;
    private QuestionAnswerDTO question;

}
