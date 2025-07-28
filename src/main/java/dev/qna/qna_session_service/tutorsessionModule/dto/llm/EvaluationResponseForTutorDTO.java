package dev.qna.qna_session_service.tutorsessionModule.dto.llm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class EvaluationResponseForTutorDTO {

    private String feedback;
    private String nextQuestion;
}
