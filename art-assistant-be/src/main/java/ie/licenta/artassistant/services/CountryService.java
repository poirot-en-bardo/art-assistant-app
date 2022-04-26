package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.CountryRequestDTO;
import ie.licenta.artassistant.dto.CountryResponseDTO;

import java.util.List;

public interface CountryService {
    List<CountryResponseDTO> getAllCountries();

    CountryResponseDTO getCountryById(int id);

    CountryResponseDTO addCountry(CountryRequestDTO CountryRequestDTO);

    CountryResponseDTO updateCountry(int id, CountryRequestDTO CountryRequestDTO);

    void deleteCountryById(int id);

}
