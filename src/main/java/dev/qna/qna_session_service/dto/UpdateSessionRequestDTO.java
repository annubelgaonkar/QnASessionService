package dev.qna.qna_session_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSessionRequestDTO {
    private Long sessionId;
    private String userAnswer;
}
