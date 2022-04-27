package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.MuseumRequestDTO;
import ie.licenta.artassistant.dto.MuseumResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.MuseumEntity;
import ie.licenta.artassistant.persistence.MuseumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MuseumServiceImpl implements MuseumService {

    private final MuseumRepository museumRepository;
    private final ArtMapper artMapper;


    @Override
    public List<MuseumResponseDTO> getAllMuseums() {
        Optional<List<MuseumEntity>> museumListOptional = Optional.of(museumRepository.findAll());
        if (museumListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_07_MUSEUM_NOT_FOUND);
        }
        return artMapper
                .museumEntityListToMuseumResponseDTOList(museumListOptional.get());
    }

    @Override
    public MuseumResponseDTO getMuseumById(int id) {
        MuseumEntity museum = museumRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_07_MUSEUM_NOT_FOUND));
        return artMapper.museumEntityToMuseumResponseDTO(museum);
    }

    @Override
    public List<MuseumResponseDTO> getAllMuseumsByCountryId(int countryId) {
        Optional<List<MuseumEntity>> museumListOptional = Optional.ofNullable(
                museumRepository.findAllByCountryId(countryId));
        if (museumListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_07_MUSEUM_NOT_FOUND);
        }
        return artMapper.museumEntityListToMuseumResponseDTOList(museumListOptional.get());
    }

    @Override
    public MuseumResponseDTO addMuseum(MuseumRequestDTO MuseumRequestDTO) {
        return null;
    }

    @Override
    public MuseumResponseDTO updateMuseum(int id, MuseumRequestDTO museumRequestDTO) {
        MuseumEntity museumEntity = museumRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_07_MUSEUM_NOT_FOUND));
        museumEntity.setAddress(museumRequestDTO.getAddress());
        museumEntity.setCountry(museumRequestDTO.getCountry());
        museumEntity.setName(museumRequestDTO.getName());
        museumEntity.setGalleries(museumRequestDTO.getGalleries());
        return artMapper.museumEntityToMuseumResponseDTO(museumRepository.save(museumEntity));
    }

    @Override
    public void deleteMuseumById(int id) {

    }
}
