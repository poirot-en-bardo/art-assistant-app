package ie.licenta.artassistant.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {

    private String text;

    private int userId;

    private int artworkId;
}
