package dev.qna.tutorSession.repository;

import dev.qna.tutorSession.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public class QuestionRepository extends JpaRepository<Question, Long> {

}
