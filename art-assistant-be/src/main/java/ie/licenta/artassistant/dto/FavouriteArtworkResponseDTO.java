package ie.licenta.artassistant.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteArtworkResponseDTO {

    private List<Integer> artworkIds;
}
