package dev.qna.qna_session_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionResponseDTO {

    private Long sessionId;
    private String email;
    private String topic;
    private String difficulty;
    private String question;
    private String userAnswer;
    private String feedback;
    private String status;
}
