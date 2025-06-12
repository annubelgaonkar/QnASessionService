package dev.qna.qna_session_service.dto;

import dev.qna.qna_session_service.model.SessionStatus;

import java.time.LocalDateTime;

public class PracticeSessionDTO {
    private Long sessionId;
    private String topic;
    private String difficulty;
    private String question;
    private String answer;
    private String feedback;
    private SessionStatus status;
    private LocalDateTime createdAt;
}
