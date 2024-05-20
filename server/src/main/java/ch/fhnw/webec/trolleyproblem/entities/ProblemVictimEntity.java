package ch.fhnw.webec.trolleyproblem.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problem_victim")
public class ProblemVictimEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "problem_id", insertable = false, updatable = false)
    @ManyToOne
    private ProblemEntity problem;

    @JoinColumn(name = "victim_id", insertable = false, updatable = false)
    @ManyToOne
    private VictimEntity victim;

    @Enumerated(EnumType.ORDINAL)
    private TrackPosition position;

    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "victim_id")
    private Long victimId;
}
