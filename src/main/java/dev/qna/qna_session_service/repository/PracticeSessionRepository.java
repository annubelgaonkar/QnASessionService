package dev.qna.qna_session_service.repository;

import dev.qna.qna_session_service.model.PracticeSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {

}
