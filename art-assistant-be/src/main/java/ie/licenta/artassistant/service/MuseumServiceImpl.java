package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.MuseumRequestDTO;
import ie.licenta.artassistant.dto.MuseumResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MuseumServiceImpl implements MuseumService {
    @Override
    public List<MuseumResponseDTO> getAllMuseums() {
        return null;
    }

    @Override
    public MuseumResponseDTO getMuseumById(int id) {
        return null;
    }

    @Override
    public List<MuseumResponseDTO> getAllMuseumsByCountryId(int countryId) {
        return null;
    }

    @Override
    public MuseumResponseDTO addMuseum(MuseumRequestDTO MuseumRequestDTO) {
        return null;
    }

    @Override
    public MuseumResponseDTO updateMuseum(int id, MuseumRequestDTO MuseumRequestDTO) {
        return null;
    }

    @Override
    public void deleteMuseumById(int id) {

    }
}
