package ch.fhnw.webec.trolleyproblem.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "scenario_victim")
public class ScenarioVictimEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "scenario_id", insertable = false, updatable = false)
    @ManyToOne
    private ScenarioEntity scenario;

    @JoinColumn(name = "victim_id", insertable = false, updatable = false)
    @ManyToOne
    private VictimEntity victim;

    @Enumerated(EnumType.ORDINAL)
    @NonNull
    private TrackPosition position;

    @Column(name = "scenario_id")
    @NonNull
    private Long scenarioId;

    @Column(name = "victim_id")
    @NonNull
    private Long victimId;
}
