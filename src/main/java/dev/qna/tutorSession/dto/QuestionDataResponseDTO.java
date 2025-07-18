package dev.qna.tutorSession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class QuestionDataResponseDTO {
    private Long questionId;
    private String question;
    private String answer;
}
