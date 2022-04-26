package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.MuseumRequestDTO;
import ie.licenta.artassistant.dto.MuseumResponseDTO;

import java.util.List;

public interface MuseumService {
    
    List<MuseumResponseDTO> getAllMuseums();

    MuseumResponseDTO getMuseumById(int id);

    List<MuseumResponseDTO> getAllMuseumsByCountryId(int countryId);

    MuseumResponseDTO addMuseum(MuseumRequestDTO MuseumRequestDTO);

    MuseumResponseDTO updateMuseum(int id, MuseumRequestDTO MuseumRequestDTO);

    void deleteMuseumById(int id);
}
