package ie.licenta.artassistant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteArtworkIdEntity implements Serializable {
    private int userId;
    private int artworkId;
}
