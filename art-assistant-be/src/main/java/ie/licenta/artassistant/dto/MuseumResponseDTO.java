package ie.licenta.artassistant.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ie.licenta.artassistant.models.CountryEntity;
import ie.licenta.artassistant.models.GalleryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MuseumResponseDTO {

    private int id;

    private String name;

    private String address;

    private String countryName;

    private int countryId;

}
