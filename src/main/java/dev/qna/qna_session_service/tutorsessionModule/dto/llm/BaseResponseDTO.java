package dev.qna.qna_session_service.tutorsessionModule.dto.llm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;
}
