package dev.qna.tutorSession.controller;

import dev.qna.tutorSession.dto.*;
import dev.qna.tutorSession.service.TutorSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

@AllArgsConstructor
@RestController
@RequestMapping("/tutorSession")
public class TutorSessionController {

    private final TutorSessionService tutorSessionService;

    /**
     * Starts a new tutoring session based on topic and token (user identity).
     */
    @PostMapping("/start")
    public ResponseEntity<StartSessionResponseDTO> startSession(
            @RequestBody StartSessionRequestDTO startSessionRequestDTO){
        StartSessionResponseDTO responseDTO =
                tutorSessionService.startSession(startSessionRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Fetches full question data by question ID.
     */
    @PostMapping("/{questionId}")
    public ResponseEntity<QuestionDataResponseDTO> getQuestionData(
            @RequestBody QuestionDataRequestDTO requestDTO){
            QuestionDataResponseDTO responseDTO =
                    tutorSessionService.getQuestionData(requestDTO.getQuestionId());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Evaluates user's answer, returns updated question trail.
     */
    @PostMapping("/answerEvaluation")
    public ResponseEntity<AnswerEvaluationResponseDTO> evaluateAnswer(
            @RequestBody AnswerEvaluationRequestDTO requestDTO){
        AnswerEvaluationResponseDTO responseDTO =
                tutorSessionService.evaluateAnswer(requestDTO){
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    /**
     * Marks a session as ended.
     */
    @PostMapping("/end")
    public ResponseEntity<SessionStatusDTO> endSession(
            @RequestBody EndSessionRequestDTO requestDTO){
        SessionStatusDTO sessionStatusDTO =
                tutorSessionService.endSession(requestDTO.getSessionId());
        return new ResponseEntity<>(sessionStatusDTO, HttpStatus.OK);
    }

}
