package ch.fhnw.webec.trolleyproblem.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProblemDto {
    private Long id;
    private String question;
    private Date createdAt;
    private int leftVotes;
    private int rightVotes;
    private CategoryDto category;

    private List<VictimDto> leftVictims;
    private List<VictimDto> rightVictims;
}
