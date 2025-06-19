package dev.qna.qna_session_service.controller;

import dev.qna.qna_session_service.JwtUtil.JwtUtil;
import dev.qna.qna_session_service.dto.*;
import dev.qna.qna_session_service.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;
    private final JwtUtil jwtUtil;

    /**To start a new session and then call LLMService
     *
     *
     * */
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

    /** Updates the session when users answers the question
     *  LLMClient will be used to evaluate the answer and then mark status as COMPLETED
     * */
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

    /**Returns a list of past sessions for the logged in user without detail feedback
     *
     * */
    @GetMapping("/history")
    public ResponseEntity<BaseResponseDTO<List<SessionSummaryDTO>>> getSessionHistory(
            @RequestHeader("Authorization") String token){

            String email = jwtUtil.extractEmail(token.substring(7));
            List<SessionSummaryDTO> history = sessionService.getSessionHistory(email);
            return ResponseEntity.ok(new BaseResponseDTO<>(
                    true,
                    "History fetched successfully",
                    history
            ));
    }

    /** Return details (question, userAnswer, detailed feedback) for a specific session ID.
     *
     * */

    @GetMapping("/{sessionId}")
    public ResponseEntity<BaseResponseDTO<SessionResponseDTO>> getSessionDetails(
            @PathVariable Long sessionId){
            SessionResponseDTO dto = sessionService.getSessionDetails(sessionId);
            return ResponseEntity.ok(new BaseResponseDTO<>(
                    true,
                    "Session details fetched",
                    dto
            ));
    }


}
