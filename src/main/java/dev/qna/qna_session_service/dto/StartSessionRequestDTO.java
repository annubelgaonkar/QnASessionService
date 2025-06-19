package dev.qna.qna_session_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartSessionRequestDTO {
    private String topic;
    private String difficulty;
}
