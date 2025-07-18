package dev.qna.tutorSession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class StartSessionResponseDTO {
    private Long sessionId;
    private List<Long> questionIdList;
}
