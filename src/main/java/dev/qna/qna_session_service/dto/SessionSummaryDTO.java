package dev.qna.qna_session_service.dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionSummaryDTO {
    private Long sessionId;
    private String topic;
    private String difficulty;
    private String question;
    private String userAnswer;
    private String feedback;
    private LocalDateTime createdAt;

    public SessionSummaryDTO(Long sessionId,
                             String topic,
                             String difficulty,
                             String question,
                             String userAnswer,
                             String feedback,
                             LocalDateTime createdAt) {
        this.sessionId = sessionId;
        this.topic = topic;
        this.difficulty = difficulty;
        this.question = truncate(question);
        this.userAnswer = truncate(userAnswer);
        this.feedback = truncate(feedback);
        this.createdAt = createdAt;
    }

    private String truncate(String text){
        if(text == null) return null;
        return text.length() > 150 ? text.substring(0, 147) + "..." : text;
    }
}
