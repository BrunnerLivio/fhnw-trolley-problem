package ch.fhnw.webec.trolleyproblem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDto {
    private Long id;
    private String question;
    private Date createdAt;
    private int leftVotes;
    private int rightVotes;
    private String leftLabel;
    private String rightLabel;
    private CategoryDto category;

    private List<VictimDto> leftVictims;
    private List<VictimDto> rightVictims;
    private Optional<Long> nextProblemId;
}
