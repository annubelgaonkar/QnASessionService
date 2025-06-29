package dev.qna.qna_session_service.repository;

import dev.qna.qna_session_service.model.PracticeSession;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {
   // List<PracticeSession> findByEmailOrderByIdDesc(String email);
   Optional<PracticeSession> findTopByEmailAndStatusOrderByCreatedAtDesc(String email, String status);
    Page<PracticeSession> findByEmail(String email, Pageable pageable);

}
