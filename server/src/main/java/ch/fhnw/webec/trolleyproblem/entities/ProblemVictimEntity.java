package ch.fhnw.webec.trolleyproblem.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    private TrackPosition position;

    @Column(name = "problem_id")
    @NonNull
    private Long problemId;

    @Column(name = "victim_id")
    @NonNull
    private Long victimId;
}
