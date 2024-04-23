package ch.fhnw.webec.trolleyproblem.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "problem")
public class ProblemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    private int leftVotes;
    private int rightVotes;

    @OneToMany(mappedBy = "problem")
    private List<ProblemVictimEntity> victims;

    @OneToMany(mappedBy = "problem")
    private List<CommentEntity> comments;

    @ManyToOne
    private CategoryEntity category;

    // Add category
}
