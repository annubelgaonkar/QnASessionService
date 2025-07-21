package dev.qna.qna_session_service.tutorSession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class StartSessionResponseDTO {
    private Long sessionId;
    private List<Long> questionIdList;
}
