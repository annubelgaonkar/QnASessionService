package dev.qna.qna_session_service.controller;

import dev.qna.qna_session_service.dto.BaseResponseDTO;
import dev.qna.qna_session_service.dto.SessionResponseDTO;
import dev.qna.qna_session_service.dto.StartSessionRequestDTO;
import dev.qna.qna_session_service.dto.UpdateSessionRequestDTO;
import dev.qna.qna_session_service.repository.PracticeSessionRepository;
import dev.qna.qna_session_service.service.SessionService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/start")
    public ResponseEntity<BaseResponseDTO<SessionResponseDTO>> startSession(
            @RequestBody StartSessionRequestDTO requestDTO) {
        SessionResponseDTO response = sessionService.startSession(requestDTO);
        BaseResponseDTO<SessionResponseDTO> baseResponseDTO = new BaseResponseDTO<>(
                true,
                "Session started successfully",
                response
        );
        return ResponseEntity.ok(baseResponseDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponseDTO<SessionResponseDTO>> updateSession(
            @RequestBody UpdateSessionRequestDTO request) {
        SessionResponseDTO response = sessionService.updateSession(request);
        BaseResponseDTO<SessionResponseDTO> baseResponseDTO = new BaseResponseDTO<>(
                true,
                "Session updated successfully",
                response
        );
        return ResponseEntity.ok(baseResponseDTO);
    }
}
