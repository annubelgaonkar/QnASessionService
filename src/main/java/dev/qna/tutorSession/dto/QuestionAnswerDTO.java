package dev.qna.tutorSession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDTO {
    private Long questionId;
    private String answer;

}
