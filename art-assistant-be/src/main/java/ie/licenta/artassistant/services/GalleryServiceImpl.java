package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.GalleryRequestDTO;
import ie.licenta.artassistant.dto.GalleryResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.GalleryEntity;
import ie.licenta.artassistant.persistence.GalleryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GalleryServiceImpl implements GalleryService {
    
    private final GalleryRepository galleryRepository;
    private final ArtMapper artMapper;
    
    
    @Override
    public List<GalleryResponseDTO> getAllGalleries() {
        return null;
    }

    @Override
    public GalleryResponseDTO getGalleryById(int id) {
        GalleryEntity gallery = galleryRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_05_GALLERY_NOT_FOUND));
        return artMapper.galleryEntityToGalleryResponseDTO(gallery);
    }

    @Override
    public List<GalleryResponseDTO> getAllGalleriesByMuseumId(int museumId) {
        Optional<List<GalleryEntity>> galleryListOptional = Optional.ofNullable(
                galleryRepository.findGalleryEntitiesByMuseumIdOrderByName(museumId));
        if (galleryListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_05_GALLERY_NOT_FOUND);
        }
        return artMapper.galleryEntityListToGalleryResponseDTOList(galleryListOptional.get());
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
