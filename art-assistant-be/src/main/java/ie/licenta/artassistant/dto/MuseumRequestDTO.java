package ie.licenta.artassistant.dto;

import ie.licenta.artassistant.models.CountryEntity;
import ie.licenta.artassistant.models.GalleryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuseumRequestDTO {

    private String name;

    private String address;

    private int countryId;
}
