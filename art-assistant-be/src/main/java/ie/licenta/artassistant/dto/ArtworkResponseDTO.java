package ie.licenta.artassistant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtworkResponseDTO {

    private int id;

    private String title;

    private String technique;

    private String description;

    private byte[] imagePath;

    private int roomId;

    private int position;

    private String artistFirstName;

    private String artistLastName;

    private String genreName;

    private int genreId;
}
