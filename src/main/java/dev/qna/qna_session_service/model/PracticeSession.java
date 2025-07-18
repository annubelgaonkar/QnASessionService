package dev.qna.qna_session_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "practice_sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PracticeSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String email;
    private String difficulty;

    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @Column(name = "userAnswer", columnDefinition = "TEXT")
    private String userAnswer;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    private String status;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
