package ie.licenta.artassistant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtworkRequestDTO {

    private String title;

    private String technique;

    private String description;

    private byte[] imagePath;

    private int roomId;

    private int year;

    private int position;

    private int artistId;

    private int genreId;
}
