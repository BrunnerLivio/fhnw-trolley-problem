package ch.fhnw.webec.trolleyproblem.trolleyProblem;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trolley_problem")
public class TrolleyProblemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    private String leftOption;
    private int leftVotes;
    @Enumerated(EnumType.ORDINAL)
    private TrolleyProblemItem leftItem;

    private String rightOption;
    private int rightVotes;
    // Move to 1..N relationship
    @Enumerated(EnumType.ORDINAL)
    private TrolleyProblemItem rightItem;

    // Add category
}
