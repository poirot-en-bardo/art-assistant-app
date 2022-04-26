package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.GalleryRequestDTO;
import ie.licenta.artassistant.dto.GalleryResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GalleryServiceImpl implements GalleryService {
    @Override
    public List<GalleryResponseDTO> getAllGalleries() {
        return null;
    }

    @Override
    public GalleryResponseDTO getGalleryById(int id) {
        return null;
    }

    @Override
    public List<GalleryResponseDTO> getAllGalleriesByMuseumId(int museumId) {
        return null;
    }

    @Override
    public GalleryResponseDTO addGallery(GalleryRequestDTO GalleryRequestDTO) {
        return null;
    }

    @Override
    public GalleryResponseDTO updateGallery(int id, GalleryRequestDTO GalleryRequestDTO) {
        return null;
    }

    @Override
    public void deleteGalleryById(int id) {

    }
}
