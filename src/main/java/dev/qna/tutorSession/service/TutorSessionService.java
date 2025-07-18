package dev.qna.tutorSession.service;

import dev.qna.tutorSession.dto.*;
import dev.qna.tutorSession.model.Question;
import dev.qna.tutorSession.model.TutorSession;
import dev.qna.tutorSession.repository.QuestionRepository;
import dev.qna.tutorSession.repository.TutorSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TutorSessionService {

    private final TutorSessionRepository tutorSessionRepository;
    private final QuestionRepository questionRepository;


    public StartSessionResponseDTO startSession(StartSessionRequestDTO requestDTO){
        Long userId = extractUserIdFromToken(requestDTO.getToken());

        //create a session
        TutorSession session = new TutorSession();
        session.setUserId(userId);
        session.setTopic(requestDTO.getTopic());
        session.setStartedAt(LocalDateTime.now());
        session.setActive(true);
        session.setQuestionIdList(new ArrayList<>());

        //generate 1st question
        Question question = new Question();
        question.setSession(session);
        question.setQuestionText(generateFirstQuestion(requestDTO.getTopic()));
        question = questionRepository.save(question);

        //Link question to a session
        session.getQuestionIdList().add(question.getId());
        session = tutorSessionRepository.save(session);

        //Return DTO
        StartSessionResponseDTO responseDTO = new StartSessionResponseDTO();
        requestDTO.setSessionId(session.getId());
        responseDTO.setQuestionIdList(List.of(question.getId()));
        return responseDTO;
    }


    public QuestionDataResponseDTO getQuestionData(Long questionId){
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        QuestionDataResponseDTO dto = new QuestionDataResponseDTO();
        dto.setQuestionId(question.getId());
        dto.setQuestion(question.getQuestionText());
        dto.setAnswer(question.getUserAnswer());
        return dto;
    }


    public AnswerEvaluationResponseDTO evaluateAnswer(AnswerEvaluationRequestDTO requestDTO){
        //get current question and save user answer
        Question question = questionRepository.findById(requestDTO.getQuestion().getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setUserAnswer(requestDTO.getQuestion().getAnswer());
        question.setEvaluatedFeedback("your answer is....");
        questionRepository.save(question);

        //generate next Question using feedback logic
        String nextQuestionText = evaluateAnswerAndGenerateNext(question.getQuestionText(),
                question.getUserAnswer());
        Question nextQuestion = new Question();
        nextQuestion.setSessionId(question.getSession());
        nextQuestion.setQuestionText(nextQuestionText);
        nextQuestion = questionRepository.save(nextQuestion);

        //Update session with new question ID
        TutorSession session = tutorSessionRepository.findById(requestDTO.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));


    }
}
