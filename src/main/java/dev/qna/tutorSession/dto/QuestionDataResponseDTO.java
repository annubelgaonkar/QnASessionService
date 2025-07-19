package dev.qna.tutorSession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDataResponseDTO {
    private Long questionId;
    private String question;
    private String answer;          //will be null because this dto is for generating question only
}
