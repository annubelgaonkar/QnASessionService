package dev.qna.qna_session_service.tutorsessionModule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDataResponseDTO {
    private Long questionId;
    private String question;
    private String answer;
}
