package dev.qna.qna_session_service.dto;

public class UpdateSessionRequestDTO {
    private Long sessionId;
    private String answer;
    private String feedback;

    public UpdateSessionRequestDTO(Long sessionId,
                                   String answer,
                                   String feedback) {
        this.sessionId = sessionId;
        this.answer = answer;
        this.feedback = feedback;
    }

    public UpdateSessionRequestDTO() {

    }
    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
