package ch.fhnw.webec.trolleyproblem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VictimDto {
    private Long id;
    private String name;
    private String imageUrl;
}
