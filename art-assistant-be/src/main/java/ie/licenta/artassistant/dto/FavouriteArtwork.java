package ie.licenta.artassistant.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteArtwork {

    private int id;

    private String title;

    private String artistFirstName;

    private String artistLastName;

    private byte[] imagePath;

    private String museumName;

}
