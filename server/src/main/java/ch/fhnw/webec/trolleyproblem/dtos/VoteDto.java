package ch.fhnw.webec.trolleyproblem.dtos;

import ch.fhnw.webec.trolleyproblem.entities.TrackPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VoteDto {
    TrackPosition position;
}
