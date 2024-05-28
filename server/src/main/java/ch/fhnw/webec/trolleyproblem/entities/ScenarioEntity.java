package ch.fhnw.webec.trolleyproblem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scenario")
public class ScenarioEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String question;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    private int leftVotes;
    private int rightVotes;

    @NonNull
    private String leftLabel;
    @NonNull
    private String rightLabel;

    @OneToMany(mappedBy = "scenario")
    @Cascade(CascadeType.ALL)
    private List<ScenarioVictimEntity> victims;

    @OneToMany(mappedBy = "scenario")
    @Cascade(CascadeType.ALL)
    private List<CommentEntity> comments;

    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @ManyToOne
    private CategoryEntity category;

    @Column(name = "category_id")
    private Long categoryId;
}
