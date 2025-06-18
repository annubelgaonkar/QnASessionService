package dev.qna.qna_session_service.service;

import dev.qna.qna_session_service.Client.LLMClient;
import dev.qna.qna_session_service.dto.SessionResponseDTO;
import dev.qna.qna_session_service.dto.StartSessionRequestDTO;
import dev.qna.qna_session_service.dto.UpdateSessionRequestDTO;
import dev.qna.qna_session_service.dto.llm.EvaluationRequestDTO;
import dev.qna.qna_session_service.dto.llm.EvaluationResponseDTO;
import dev.qna.qna_session_service.dto.llm.QuestionRequestDTO;
import dev.qna.qna_session_service.dto.llm.QuestionResponseDTO;
import dev.qna.qna_session_service.model.PracticeSession;
import dev.qna.qna_session_service.repository.PracticeSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final PracticeSessionRepository practiceSessionRepository;
    private final LLMClient llmClient;

    @Override
    public SessionResponseDTO startSession(StartSessionRequestDTO request) {
        QuestionRequestDTO questionReq = new QuestionRequestDTO(
                request.getTopic(), request.getDifficulty());
        QuestionResponseDTO questionRes = llmClient.generateQuestion(questionReq);

        PracticeSession session = PracticeSession.builder()
                .email(request.getEmail())
                .topic(questionRes.getTopic())
                .difficulty(questionRes.getDifficulty())
                .question(questionRes.getQuestionText())
                .status("IN_PROGRESS")
                .build();

        PracticeSession saved = practiceSessionRepository.save(session);
        return toDTO(saved);
    }


    @Override
    public SessionResponseDTO updateSession(UpdateSessionRequestDTO request) {
        PracticeSession session = practiceSessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        EvaluationRequestDTO evalReq = new EvaluationRequestDTO();
        evalReq.setTopic(session.getTopic());
        evalReq.setQuestion(session.getQuestion());
        evalReq.setUserAnswer(request.getUserAnswer());

        EvaluationResponseDTO evalRes = llmClient.evaluateAnswer(evalReq);

        session.setUserAnswer(request.getUserAnswer());
        session.setFeedback(evalRes.getFeedback());
        session.setStatus("COMPLETED");

        PracticeSession updated = practiceSessionRepository.save(session);
        return toDTO(updated);
    }

    private SessionResponseDTO toDTO(PracticeSession session) {
        SessionResponseDTO dto = new SessionResponseDTO();
        dto.setSessionId(session.getId());
        dto.setEmail(session.getEmail());
        dto.setTopic(session.getTopic());
        dto.setDifficulty(session.getDifficulty());
        dto.setQuestion(session.getQuestion());
        dto.setUserAnswer(session.getUserAnswer());
        dto.setFeedback(session.getFeedback());
        dto.setStatus(session.getStatus());
        return dto;
    }
    }
