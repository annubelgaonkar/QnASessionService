package dev.qna.tutorSession.controller;

import dev.qna.tutorSession.dto.*;
import dev.qna.tutorSession.service.TutorSessionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
public class TutorSessionController {

    private final TutorSessionService tutorSessionService;

    // 1. Start a new session
    @PostMapping("/start")
    public ResponseEntity<StartSessionResponseDTO> startSession(
            @RequestBody StartSessionRequestDTO req){
        return ResponseEntity.ok(tutorSessionService.startSession(req));
    }

    // 2. Get question data by ID
    @GetMapping("/questions/{questionId}")
    public ResponseEntity<QuestionDataResponseDTO> getQuestion(@PathVariable Long questionId){
        return ResponseEntity.ok(tutorSessionService.getQuestionById(questionId));
    }

    // 3. Evaluate user's answer
    @PostMapping("/evaluate")
    public ResponseEntity<QuestionIdsResponseDTO> evaluate(
            @RequestBody EvaluateAnswerRequestDTO req){
        return ResponseEntity.ok(tutorSessionService.evaluateAnswer(req));
    }

    // 4. End the session
    @PostMapping("/end/{sessionId}")
    public ResponseEntity<Void> endSession(@PathVariable Long sessionId){
        tutorSessionService.endSession(sessionId);
        return ResponseEntity.noContent().build();
    }
}
