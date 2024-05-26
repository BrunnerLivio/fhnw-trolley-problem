package ch.fhnw.webec.trolleyproblem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
    private Long id;
    @NotBlank(message = "Comment is required")
    @Size(max = 255, message = "Comment must not be longer than 255 characters")
    @Size(min = 10, message = "Comment must be at least 10 characters long")
    private String text;
    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author must not be longer than 255 characters")
    @Size(min = 3, message = "Author must be at least 3 characters long")
    private String author;
    private String initials;
    private String authorColor;
    private boolean createdByCurrentUser;
    private Date createdAt;
}
