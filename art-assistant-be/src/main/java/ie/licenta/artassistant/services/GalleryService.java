package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.GalleryRequestDTO;
import ie.licenta.artassistant.dto.GalleryResponseDTO;

import java.util.List;

public interface GalleryService {

    List<GalleryResponseDTO> getAllGalleries();

    GalleryResponseDTO getGalleryById(int id);

    List<GalleryResponseDTO> getAllGalleriesByMuseumId(int museumId);

    GalleryResponseDTO addGallery(GalleryRequestDTO GalleryRequestDTO);

    GalleryResponseDTO updateGallery(int id, GalleryRequestDTO GalleryRequestDTO);

    void deleteGalleryById(int id);
}
