package dev.qna.qna_session_service.dto.llm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationResponseDTO {
    private String feedback;
}
