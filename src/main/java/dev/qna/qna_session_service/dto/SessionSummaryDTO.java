package dev.qna.qna_session_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SessionSummaryDTO {
    private Long sessionId;
    private String topic;
    private String difficulty;
    private String question;
    private String userAnswer;
    private String feedback;
    private LocalDateTime createdAt;
}
