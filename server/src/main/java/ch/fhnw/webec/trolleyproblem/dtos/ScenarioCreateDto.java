package ch.fhnw.webec.trolleyproblem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class ScenarioCreateDto {
    @NotBlank(message = "Question is required")
    @Size(max = 255, message = "Question must not be longer than 255 characters")
    @Size(min = 10, message = "Question must be at least 10 characters long")
    String question;

    @Size(max = 5, message = "There must be at most 5 victims on each side")
    ArrayList<VictimDto> leftVictims;

    @Size(max = 5, message = "There must be at most 5 victims on each side")
    ArrayList<VictimDto> rightVictims;
    Long categoryId;

    @Size(max = 16, message = "Left label must not be longer than 16 characters")
    String leftLabel;
    @Size(max = 16, message = "Right label must not be longer than 16 characters")
    String rightLabel;

    public String getQuestion() {
        if (question != null && !question.startsWith("Oh no!")) {
            return "Oh no! " + question;
        }
        return question;
    }
}
