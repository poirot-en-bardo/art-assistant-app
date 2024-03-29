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

    private int year;

    private String description;

    private byte[] imagePath;

    private int roomId;

    private int position;

    private String artistFirstName;

    private String artistLastName;

    private int artistId;

    private String genreName;

    private int genreId;

    private String museumName;

    private String galleryName;
}
