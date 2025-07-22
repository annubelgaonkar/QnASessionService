package dev.qna.qna_session_service.tutorsessionModule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StartSessionRequestDTO {
    private String topic;
}
