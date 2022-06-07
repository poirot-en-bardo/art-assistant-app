package ie.licenta.artassistant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {

    private int id;

    private int rating;

    private String text;

    private LocalDateTime createdAt;

    private String userFirstName;

    private String userLastName;
}
