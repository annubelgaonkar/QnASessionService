package dev.qna.qna_session_service.dto;

public class StartSessionRequestDTO {
    private Long userId;
    private String topic;
    private String difficulty;
    private String question;

    public StartSessionRequestDTO(Long userId,
                                  String topic,
                                  String difficulty,
                                  String question) {
        this.userId = userId;
        this.topic = topic;
        this.difficulty = difficulty;
        this.question = question;
    }
    public StartSessionRequestDTO() {

    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
