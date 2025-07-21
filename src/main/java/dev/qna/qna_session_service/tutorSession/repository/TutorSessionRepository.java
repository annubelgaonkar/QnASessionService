package dev.qna.qna_session_service.tutorSession.repository;

import dev.qna.qna_session_service.tutorSession.model.TutorSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorSessionRepository extends JpaRepository<TutorSession, Long> {
}
