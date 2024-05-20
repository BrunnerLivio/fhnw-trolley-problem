package ch.fhnw.webec.trolleyproblem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class ProblemCreateDto {
    String question;
    ArrayList<VictimDto> leftVictims;
    ArrayList<VictimDto> rightVictims;
    Long categoryId;
    String leftLabel;
    String rightLabel;
}
