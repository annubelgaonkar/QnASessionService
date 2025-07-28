package dev.qna.qna_session_service.tutorsessionModule.controller;

import dev.qna.qna_session_service.dto.StartSessionRequestDTO;
import dev.qna.qna_session_service.exception.ResourceNotFoundException;
import dev.qna.qna_session_service.tutorsessionModule.dto.EvaluateAnswerRequestDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.QuestionDataResponseDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.QuestionIdsResponseDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.StartSessionResponseDTO;
import dev.qna.qna_session_service.tutorsessionModule.model.Question;
import dev.qna.qna_session_service.tutorsessionModule.repository.QuestionRepository;
import dev.qna.qna_session_service.tutorsessionModule.service.TutorSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
public class TutorSessionController {

    private final TutorSessionService tutorSessionService;
    private final QuestionRepository questionRepository;

    // 1. Start a new session
    @PostMapping("/start")
    public ResponseEntity<StartSessionResponseDTO> startSession(
            @RequestBody StartSessionRequestDTO req){
        return ResponseEntity.ok(tutorSessionService.startSession(req));
    }

    // 2. Get question data by ID
    @GetMapping("/question/{questionId}")
    public ResponseEntity<QuestionDataResponseDTO> getQuestion(@PathVariable Long questionId){
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        return ResponseEntity.ok(new QuestionDataResponseDTO(
                question.getQuestionId(),
                question.getQuestionText(),          //is this null?
                null
        ));
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
