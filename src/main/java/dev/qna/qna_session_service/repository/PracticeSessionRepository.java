package dev.qna.qna_session_service.repository;

import dev.qna.qna_session_service.model.PracticeSession;
import dev.qna.qna_session_service.model.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {
    Optional<PracticeSession> findTopByUserIdAndStatusOrderByCreatedAtDesc(Long userId, SessionStatus status);
}
