package dev.qna.qna_session_service.tutorsessionModule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TutorSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private String topic;
    private boolean active = true;

    @ElementCollection
    @CollectionTable(
            name = "tutor_session_question_ids",
            joinColumns = @JoinColumn(name = "session_id")
    )
    @Column(name = "question_id")
    private List<Long> questionIds = new ArrayList<>();

}
