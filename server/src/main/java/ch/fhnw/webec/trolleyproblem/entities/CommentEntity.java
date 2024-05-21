package ch.fhnw.webec.trolleyproblem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String author;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @JoinColumn(name = "problem_id", insertable = false, updatable = false)
    @ManyToOne
    private ProblemEntity problem;

    @Column(name = "problem_id")
    private Long problemId;

    public String getInitials() {
        if (author == null || author.isEmpty()) {
            return "";
        }

        final String[] parts = author.split(" ");
        final StringBuilder initials = new StringBuilder();
        for (String part : parts) {
            initials.append(part.charAt(0));
        }
        return initials.toString();
    }

    /**
     * Generate color based on the author's name
     * @return HSL Color
     */
    public String getAuthorColor() {
        // Inspired by https://stackoverflow.com/a/66494926/4412964
        final int hue = author.hashCode() % 360;
        return "hsl(" + hue + ", 70%, 40%)";
    }
}
