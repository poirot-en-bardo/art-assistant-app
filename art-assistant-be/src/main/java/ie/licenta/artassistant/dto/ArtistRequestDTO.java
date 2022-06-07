package ie.licenta.artassistant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ie.licenta.artassistant.models.ArtworkEntity;
import ie.licenta.artassistant.models.CountryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistRequestDTO {

    private String firstName;

    private String lastName;

    private int birthYear;

    private int deathYear;

    private String biography;

    private int countryId;

}
