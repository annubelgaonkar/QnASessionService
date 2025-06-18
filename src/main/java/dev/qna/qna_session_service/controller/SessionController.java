package dev.qna.qna_session_service.controller;

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
    public ResponseEntity<SessionResponseDTO> startSession(
            @RequestBody StartSessionRequestDTO requestDTO) {
        SessionResponseDTO response = sessionService.startSession(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public SessionResponseDTO updateSession(@RequestBody UpdateSessionRequestDTO request) {
        return sessionService.updateSession(request);
    }
}
