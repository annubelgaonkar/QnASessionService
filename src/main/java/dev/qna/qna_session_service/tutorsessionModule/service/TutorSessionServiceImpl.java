package dev.qna.qna_session_service.tutorsessionModule.service;


import dev.qna.qna_session_service.tutorsessionModule.client.LLMClient;
import dev.qna.qna_session_service.dto.StartSessionRequestDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.EvaluateAnswerRequestDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.QuestionDataResponseDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.QuestionIdsResponseDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.StartSessionResponseDTO;
import dev.qna.qna_session_service.tutorsessionModule.dto.llm.EvaluateAnswerResponse;
import dev.qna.qna_session_service.tutorsessionModule.model.Question;
import dev.qna.qna_session_service.tutorsessionModule.model.TutorSession;
import dev.qna.qna_session_service.tutorsessionModule.repository.QuestionRepository;
import dev.qna.qna_session_service.tutorsessionModule.repository.TutorSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TutorSessionServiceImpl implements TutorSessionService {

    private final TutorSessionRepository tutorSessionRepository;
    private final QuestionRepository questionRepository;
    private final LLMClient llmClient;

    @Override
    public StartSessionResponseDTO startSession(StartSessionRequestDTO request){

        //Call LLM to generate the first question
        String questionText = llmClient.generateFirstQuestion(request.getTopic());
        System.out.println("Generated question: " + questionText);

        // Create session
        TutorSession session = new TutorSession();
        session.setTopic(request.getTopic());
        session.setActive(true);
        session.setQuestionIds(new ArrayList<>());
        session = tutorSessionRepository.save(session);

        //save question
        Question question = new Question();
        question.setQuestionText(questionText);
        question.setSession(session);
        question.setAnswer(null);
        question = questionRepository.save(question);

        // Update session with question ID
        session.getQuestionIds().add(question.getQuestionId());
        tutorSessionRepository.save(session);

        return new StartSessionResponseDTO(session.getSessionId(), session.getQuestionIds());
    }

    @Override
    public QuestionDataResponseDTO getQuestionById(Long questionId){
        Question q = questionRepository.findById(questionId)
                .orElseThrow(() -> new NoSuchElementException("Question not found: " + questionId));

        return new QuestionDataResponseDTO(q.getQuestionId(), q.getQuestionText(), q.getAnswer());
    }

    @Override
    public QuestionIdsResponseDTO evaluateAnswer(EvaluateAnswerRequestDTO request){
        Long sessionId = request.getSessionId();
        Long questionId = request.getQuestion().getQuestionId();
        String userAnswer = request.getQuestion().getAnswer();

        // Fetch session and question
        TutorSession session =
                tutorSessionRepository.findById(sessionId)
                        .orElseThrow(() -> new NoSuchElementException("Session not found: " + sessionId));

        Question exitingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new NoSuchElementException("Question not found:" + questionId));

        // Save user's answer
        exitingQuestion.setAnswer(userAnswer);
        questionRepository.save(exitingQuestion);

        // Call LLM for feedback and next question
        EvaluateAnswerResponse llmResponse = llmClient.evaluateAnswer(
                exitingQuestion.getQuestionText(), userAnswer);

        // Save new question
        Question nextQ = new Question();
        nextQ.setQuestionText(llmResponse.getNextQuestion());
        nextQ.setSession(session);
        nextQ.setAnswer(null);
        nextQ = questionRepository.save(nextQ);

        // Update session
        session.getQuestionIds().add(nextQ.getQuestionId());
        tutorSessionRepository.save(session);

        return new QuestionIdsResponseDTO(session.getQuestionIds());

    }

    @Override
    public void endSession(Long sessionId){
        TutorSession session = tutorSessionRepository.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("Session not found: " + sessionId));

        session.setActive(false);
        tutorSessionRepository.save(session);
    }

}
