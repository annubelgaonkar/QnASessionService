package dev.qna.tutorSession.repository;

import dev.qna.tutorSession.model.TutorSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorSessionRepository extends JpaRepository<TutorSession, Long> {
}
