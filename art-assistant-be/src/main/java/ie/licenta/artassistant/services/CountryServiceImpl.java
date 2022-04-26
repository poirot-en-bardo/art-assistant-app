package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.CountryRequestDTO;
import ie.licenta.artassistant.dto.CountryResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    @Override
    public List<CountryResponseDTO> getAllCountries() {
        return null;
    }

    @Override
    public CountryResponseDTO getCountryById(int id) {
        return null;
    }

    @Override
    public CountryResponseDTO addCountry(CountryRequestDTO CountryRequestDTO) {
        return null;
    }

    @Override
    public CountryResponseDTO updateCountry(int id, CountryRequestDTO CountryRequestDTO) {
        return null;
    }

    @Override
    public void deleteCountryById(int id) {

    }
}
