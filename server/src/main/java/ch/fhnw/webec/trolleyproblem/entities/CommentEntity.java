package ch.fhnw.webec.trolleyproblem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String author;

    @ManyToOne
    private ProblemEntity problem;

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
