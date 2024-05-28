package ch.fhnw.webec.trolleyproblem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.text.BreakIterator;
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

    @JoinColumn(name = "scenario_id", insertable = false, updatable = false)
    @ManyToOne
    private ScenarioEntity scenario;

    @Column(name = "scenario_id")
    private Long scenarioId;

    /**
     * Get the first character of a string and takes care of surrogate pairs
     *
     * @return First character
     */
    public static String getFirstCharacter(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        BreakIterator boundary = BreakIterator.getCharacterInstance();
        boundary.setText(text);

        int start = boundary.first();
        int end = boundary.next();

        return text.substring(start, end);
    }

    /**
     * Get the initials of the author
     *
     * @return Initials
     */
    public String getInitials() {
        if (author == null || author.isEmpty()) {
            return "";
        }

        final String[] parts = author.split(" ");
        final StringBuilder initials = new StringBuilder();

        String firstPart = parts[0];
        String lastPart = parts[parts.length - 1];
        if (!firstPart.isEmpty()) {
            initials.append(getFirstCharacter(firstPart));
        }
        if (!lastPart.isEmpty() && parts.length > 1) {
            initials.append(getFirstCharacter(lastPart));
        }

        return initials.toString();
    }

    /**
     * Generate color based on the author's name
     *
     * @return HSL Color
     */
    public String getAuthorColor() {
        // Inspired by https://stackoverflow.com/a/66494926/4412964
        final int hue = author.hashCode() % 360;
        return "hsl(" + hue + ", 70%, 40%)";
    }
}
