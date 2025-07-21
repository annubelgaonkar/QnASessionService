package dev.qna.qna_session_service.tutorSession.dto.llm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenerateQuestionResponseDTO {

    private String question;
}
