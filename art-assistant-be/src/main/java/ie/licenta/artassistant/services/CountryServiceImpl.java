package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.CountryRequestDTO;
import ie.licenta.artassistant.dto.CountryResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.CountryEntity;
import ie.licenta.artassistant.models.MuseumEntity;
import ie.licenta.artassistant.persistence.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ArtMapper artMapper;

    @Override
    public List<CountryResponseDTO> getAllCountries() {
        Optional<List<CountryEntity>> countryListOptional = Optional.of(countryRepository.findAllByOrderByNameAsc());
        if (countryListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_14_COUNTRY_NOT_FOUND);
        }
        return artMapper
                .countryEntityListToCountryResponseDTOList(countryListOptional.get());
    }

    @Override
    public CountryResponseDTO getCountryById(int id) {
        CountryEntity country = countryRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_01_RESOURCE_NOT_FOUND));
        return artMapper.countryEntityToCountryResponseDTO(country);
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
