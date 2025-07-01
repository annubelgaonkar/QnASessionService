package dev.qna.qna_session_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.qna.qna_session_service.JwtUtil.JwtUtil;
import dev.qna.qna_session_service.dto.SessionSummaryDTO;
import dev.qna.qna_session_service.service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@TestPropertySource(properties = "LLM_SERVICE_BASE_URL=localhost:8088")
@AutoConfigureMockMvc(addFilters = false)
public class SessionControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private JwtUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void Test_GetSessionHistory_WithValidJwt_ReturnHistory() throws Exception{
        String jwt = "valid.jwt.token";
        String bearerToken = "Bearer " + jwt;
        String email = "user@example.com";

        List<SessionSummaryDTO> mockHistory = List.of(
                new SessionSummaryDTO(1L, "java", "Intermediate",
                        "What are the 4 pillars of OOP?", "Inheritance, Abstraction,Polymorphism, Encapsulation",
                        "You are spot on. Let's elaborate....", LocalDateTime.now()),
                new SessionSummaryDTO(2L, "java", "Intermediate",
                        "What is multithreading?", "A thread is a...",
                        "Your answer describes....", LocalDateTime.now())
        );
        when(jwtUtil.extractEmail(jwt)).thenReturn(email);
        when(sessionService.getSessionHistory(email, 0, 5)).thenReturn(mockHistory);

        mockMvc.perform(get("/session/history")
                        .header("Authorization", bearerToken)
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("History fetched successfully"))
                .andExpect(jsonPath("$.data.length()").value(2));
        }

        @Test
        void TestGetSessionHistory_MissingToken_ReturnsError() throws Exception{
            mockMvc.perform(get("/session/history")
                    .param("page", "0")
                    .param("size", "5"))
                    .andExpect(status().isInternalServerError()
            );
        }

        @Test
        void TestGetSessionHistory_InvalidTokenPrefix_ReturnsError() throws Exception{
            mockMvc.perform(get("/session/history")
                    .header("Authorization", "Token.xyz.invlaid")
                    .param("page", "0")
                    .param("size", "5"))
                    .andExpect(status().isInternalServerError());
        }

        @Test
        void testGetSessionHistory_ServiceReturnsEmptyList() throws Exception{
            String jwt = "empty.jwt.token";
            String bearerToken = "Bearer " + jwt;
            String email = "user@example.com";

            when(jwtUtil.extractEmail(jwt)).thenReturn(email);
            when(sessionService.getSessionHistory(email, 0, 5)).thenReturn(List.of());

            mockMvc.perform(get("/session/history")
                    .header("Authorization", bearerToken)
                    .param("page", "0")
                    .param("size", "5"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.data.length()").value(0));
        }
}
