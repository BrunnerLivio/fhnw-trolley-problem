package ch.fhnw.webec.trolleyproblem.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "problem")
public class ProblemEntity {
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

    @OneToMany(mappedBy = "problem")
    @Cascade(CascadeType.ALL)
    private List<ProblemVictimEntity> victims;

    @OneToMany(mappedBy = "problem")
    @Cascade(CascadeType.ALL)
    private List<CommentEntity> comments;

    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @ManyToOne
    private CategoryEntity category;

    @Column(name = "category_id")
    private Long categoryId;
}
