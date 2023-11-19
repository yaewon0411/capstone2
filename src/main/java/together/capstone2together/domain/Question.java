package together.capstone2together.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Question {
    @Id @GeneratedValue
    @Column(name="question_id")
    private Long id;
    private List<String> questionList = new ArrayList<>();
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "survey_id")
//    private Survey survey;
    public Question(List<String> questionList) {
        this.questionList = questionList;
    }
}
