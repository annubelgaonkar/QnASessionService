package dev.qna.tutorSession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StartSessionResponseDTO {
    private Long sessionId;
    private List<Long> questionIdList;
}
