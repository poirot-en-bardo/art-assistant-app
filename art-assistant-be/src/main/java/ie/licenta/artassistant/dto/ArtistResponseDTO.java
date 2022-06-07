package ie.licenta.artassistant.dto;

import ie.licenta.artassistant.models.ArtworkEntity;
import ie.licenta.artassistant.models.CountryEntity;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistResponseDTO {

    private int id;

    private String firstName;

    private String lastName;

    private int birthYear;

    private int deathYear;

    private String biography;

    private String countryName;

}
