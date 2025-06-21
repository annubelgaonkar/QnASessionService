package dev.qna.qna_session_service.service;

import dev.qna.qna_session_service.dto.SessionResponseDTO;
import dev.qna.qna_session_service.dto.SessionSummaryDTO;
import dev.qna.qna_session_service.dto.StartSessionRequestDTO;
import dev.qna.qna_session_service.dto.UpdateSessionRequestDTO;
import java.util.List;

public interface SessionService {

    SessionResponseDTO startSession(StartSessionRequestDTO request);
    SessionResponseDTO updateSession(UpdateSessionRequestDTO request);
    List<SessionSummaryDTO> getSessionHistory(String email, int page, int size);
    SessionResponseDTO getSessionDetails(Long sessionId);
}
