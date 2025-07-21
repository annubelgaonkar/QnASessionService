package dev.qna.qna_session_service.tutorSession.service;


import dev.qna.qna_session_service.tutorSession.dto.*;

public interface TutorSessionService {
    StartSessionResponseDTO startSession(StartSessionRequestDTO requestDTO);
    QuestionDataResponseDTO getQuestionById(Long questionId);
    QuestionIdsResponseDTO evaluateAnswer(EvaluateAnswerRequestDTO request);
    void endSession(Long sessionId);
}
