package ch.fhnw.webec.trolleyproblem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private String author;
}
