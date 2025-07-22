package dev.qna.qna_session_service.tutorsessionModule.service;


import dev.qna.qna_session_service.dto.StartSessionRequestDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.EvaluateAnswerRequestDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.QuestionDataResponseDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.QuestionIdsResponseDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.StartSessionResponseDTO;

public interface TutorSessionService {
    StartSessionResponseDTO startSession(StartSessionRequestDTO requestDTO);
    QuestionDataResponseDTO getQuestionById(Long questionId);
    QuestionIdsResponseDTO evaluateAnswer(EvaluateAnswerRequestDTO request);
    void endSession(Long sessionId);
}
