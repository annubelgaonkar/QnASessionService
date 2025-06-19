package dev.qna.qna_session_service.service;

import dev.qna.qna_session_service.Client.LLMClient;
import dev.qna.qna_session_service.dto.*;
import dev.qna.qna_session_service.dto.llm.EvaluationRequestDTO;
import dev.qna.qna_session_service.dto.llm.EvaluationResponseDTO;
import dev.qna.qna_session_service.dto.llm.QuestionRequestDTO;
import dev.qna.qna_session_service.dto.llm.QuestionResponseDTO;
import dev.qna.qna_session_service.exception.ResourceNotFoundException;
import dev.qna.qna_session_service.exception.SessionNotFoundException;
import dev.qna.qna_session_service.model.PracticeSession;
import dev.qna.qna_session_service.repository.PracticeSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final PracticeSessionRepository practiceSessionRepository;
    private final LLMClient llmClient;

    @Override
    public SessionResponseDTO startSession(StartSessionRequestDTO request) {
        // Extract email from SecurityContext (set by JWT filter)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Call LLM to generate a question
        QuestionRequestDTO questionReq = new QuestionRequestDTO(
                request.getTopic(), request.getDifficulty());

        BaseResponseDTO<QuestionResponseDTO> questionRes = llmClient.generateQuestion(questionReq);

        QuestionResponseDTO questionResData = questionRes.getData();

        PracticeSession session = PracticeSession.builder()
                .email(email)
                .topic(questionResData.getTopic())
                .difficulty(questionResData.getDifficulty())
                .question(questionResData.getQuestionText())
                .status("IN_PROGRESS")
                .build();

        PracticeSession saved = practiceSessionRepository.save(session);
        return toDTO(saved);
    }


    @Override
    public SessionResponseDTO updateSession(UpdateSessionRequestDTO request) {
        PracticeSession session = practiceSessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + request.getSessionId()));

        EvaluationRequestDTO evalReq = new EvaluationRequestDTO();
        evalReq.setTopic(session.getTopic());
        evalReq.setQuestion(session.getQuestion());
        evalReq.setUserAnswer(request.getUserAnswer());

        BaseResponseDTO<EvaluationResponseDTO> evalRes = llmClient.evaluateAnswer(evalReq);

        session.setUserAnswer(request.getUserAnswer());
        EvaluationResponseDTO eval = evalRes.getData();
        session.setFeedback(eval.getFeedback());
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

    @Override
    public List<SessionSummaryDTO> getSessionHistory(String email){
        List<PracticeSession> sessions = practiceSessionRepository.findByEmailOrderByIdDesc(email);
        return sessions.stream()
                .map(session -> new SessionSummaryDTO(
                        session.getId(),
                        session.getTopic(),
                        session.getDifficulty(),
                        session.getQuestion(),
                        session.getUserAnswer(),
                        session.getFeedback(),
                        session.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public SessionResponseDTO getSessionDetails(Long sessionId){
        PracticeSession session = practiceSessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + sessionId));
        return toDTO(session);
    }

}
