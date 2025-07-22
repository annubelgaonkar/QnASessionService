package dev.qna.qna_session_service.tutorsessionModule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class QuestionIdsResponseDTO {
    private List<Long> questionIdsList;
}
