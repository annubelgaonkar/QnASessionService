package dev.qna.qna_session_service.controller;

import dev.qna.qna_session_service.JwtUtil.JwtUtil;
import dev.qna.qna_session_service.dto.*;
import dev.qna.qna_session_service.service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SessionControllerTest {

    @Autowired
    private SessionController sessionController;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void Test_startSession_RunsSuccessfully() {
        //Arrange
        StartSessionRequestDTO requestDTO = new StartSessionRequestDTO();
        SessionResponseDTO sessionResponseDTO = new SessionResponseDTO();
        when(sessionService.startSession(requestDTO)).thenReturn(sessionResponseDTO);


        //Act
        ResponseEntity<BaseResponseDTO<SessionResponseDTO>> response =
                sessionController.startSession(requestDTO);

        //Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Session started successfully", response.getBody().getMessage());
        assertEquals(sessionResponseDTO, response.getBody().getData());

    }

    @Test
    public void Test_startSession_WithNullRequestThrowsException() {
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            sessionController.startSession(null);
        });
    }

    @Test
    public void Test_UpdateSession_RunsSuccessfully() {

        //Arrange
        SessionResponseDTO sessionResponseDTO = new SessionResponseDTO();
        UpdateSessionRequestDTO requestDTO = new UpdateSessionRequestDTO();
        when(sessionService.updateSession(requestDTO)).thenReturn(sessionResponseDTO);

        //Act
        ResponseEntity<BaseResponseDTO<SessionResponseDTO>> response =
                sessionController.updateSession(requestDTO);

        //Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Session updated successfully", response.getBody().getMessage());
        assertTrue(response.getBody().isSuccess());
        assertEquals(sessionResponseDTO, response.getBody().getData());
    }

    @Test
    public void Test_UpdateSession_SessionNotFound_ReturnsNullOrError(){

        //Arrange
        UpdateSessionRequestDTO requestDTO =
                new UpdateSessionRequestDTO();

        //simulate session not found
        when(sessionService.updateSession(requestDTO)).thenReturn(null);

        //Act
        ResponseEntity<BaseResponseDTO<SessionResponseDTO>> response =
                sessionController.updateSession(requestDTO);

        //Assert
        assertNotNull(response);
        assertNull(response.getBody().getData());
    }

    @Test
    public void Test_GetSessionHistory_RunsSuccessfully(){
        //Arrange
        String token = "Bearer faketoken";
        String email = "test@example.com";
        List<SessionSummaryDTO> history = Arrays.asList(
                new SessionSummaryDTO(1L, "java",
                        "Beginner","What is a class in java?",
                        "A class is a blueprint for creating objects",
                        "Good job. You are right.", LocalDateTime.now()),
                new SessionSummaryDTO(2L, "java",
                        "Beginner", "What is an Object in java?",
                        "An object is an instance of a class",
                        "Good job. You are on right track.", LocalDateTime.now()
                )
        );
        when(jwtUtil.extractEmail("faketoken")).thenReturn(email);
        when(sessionService.getSessionHistory(email, 0, 5)).thenReturn(history);

        //Act
        ResponseEntity<BaseResponseDTO<List<SessionSummaryDTO>>> sessionResponse =
                sessionController.getSessionHistory(token, 0, 5);

        //Assert
        assertEquals(200,sessionResponse.getStatusCodeValue());
        assertTrue(sessionResponse.getBody().isSuccess());
        assertEquals("History fetched successfully", sessionResponse.getBody().getMessage());
        assertEquals(2, sessionResponse.getBody().getData().size());
    }

    @Test
    public void Test_GetSessionDetails_RunsSuccessfully(){
        //Arrange
        Long sessionId = 123L;
        SessionResponseDTO sessionResponseDTO =
                new SessionResponseDTO();
        when(sessionService.getSessionDetails(sessionId)).thenReturn(sessionResponseDTO);

        //Act
        ResponseEntity<BaseResponseDTO<SessionResponseDTO>> response =
                sessionController.getSessionDetails(sessionId);
        //Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Session details fetched", response.getBody().getMessage());
        assertEquals(sessionResponseDTO, response.getBody().getData());
    }
}