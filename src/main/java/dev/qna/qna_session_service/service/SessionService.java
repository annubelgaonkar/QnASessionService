package dev.qna.qna_session_service.service;

import dev.qna.qna_session_service.dto.SessionResponseDTO;
import dev.qna.qna_session_service.dto.StartSessionRequestDTO;
import dev.qna.qna_session_service.dto.UpdateSessionRequestDTO;
import dev.qna.qna_session_service.model.PracticeSession;
import org.springframework.web.bind.annotation.RequestBody;

public interface SessionService {
    SessionResponseDTO startSession(StartSessionRequestDTO request);
    SessionResponseDTO updateSession(UpdateSessionRequestDTO request);
}
