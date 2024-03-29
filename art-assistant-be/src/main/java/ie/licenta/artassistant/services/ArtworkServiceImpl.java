package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.ArtistResponseDTO;
import ie.licenta.artassistant.dto.ArtworkRequestDTO;
import ie.licenta.artassistant.dto.ArtworkResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.ArtistEntity;
import ie.licenta.artassistant.models.ArtworkEntity;
import ie.licenta.artassistant.persistence.ArtistRepository;
import ie.licenta.artassistant.persistence.ArtworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArtworkServiceImpl implements  ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final ArtMapper artMapper;

    @Override
    public List<ArtworkResponseDTO> getAllArtworks() {
        return null;
    }

    @Override
    public ArtworkResponseDTO getArtworkById(int id) {
        ArtworkEntity artwork = artworkRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_03_ARTWORK_NOT_FOUND));
        return artMapper.artworkEntityToArtworkResponseDTO(artwork);
    }

    @Override
    @Transactional
    public ArtworkResponseDTO addArtwork(ArtworkRequestDTO artworkRequestDTO) {
        return artMapper.artworkEntityToArtworkResponseDTO(artworkRepository.save(
                artMapper.artworkRequestDTOToArtworkEntity(artworkRequestDTO)));
    }

    @Override
    @Transactional
    public ArtworkResponseDTO updateArtwork(int id, ArtworkRequestDTO artworkRequestDTO) {
        ArtworkEntity oldArtwork = artworkRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_03_ARTWORK_NOT_FOUND));
        if (oldArtwork == null) {
            return null;
        } else {
            ArtworkEntity artworkEntity = artMapper.artworkRequestDTOToArtworkEntityWithId(id, artworkRequestDTO);
            return artMapper.artworkEntityToArtworkResponseDTO(artworkRepository.save(artworkEntity));
        }
    }

    @Override
    @Transactional
    public void deleteArtworkById(int id) {
        ArtworkEntity artwork = artworkRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_03_ARTWORK_NOT_FOUND));
        artworkRepository.deleteById(id);
    }

    @Override
    public List<ArtworkResponseDTO> getAllArtworksByRoomIdOrderByPosition(int roomId) {
        Optional<List<ArtworkEntity>> artworkListOptional = Optional.ofNullable(
                artworkRepository.findAllByRoomIdOrderByPosition(roomId));
        if (artworkListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_03_ARTWORK_NOT_FOUND);
        }
        List<ArtworkEntity> entities = artworkListOptional.get();
        return artMapper.artworkEntityListToArtworkResponseDTOList(artworkListOptional.get());
    }

    @Override
    public List<ArtworkResponseDTO> getAllArtworksByArtistId(int artistId) {
        Optional<List<ArtworkEntity>> artworkListOptional = Optional.ofNullable(
                artworkRepository.findAllByArtistIdOrderByTitle(artistId));
        if (artworkListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_03_ARTWORK_NOT_FOUND);
        }
        return artMapper.artworkEntityListToArtworkResponseDTOList(artworkListOptional.get());
    }


    @Override
    public List<ArtworkResponseDTO> getAllArtworksByGenreId(int genreId) {
        Optional<List<ArtworkEntity>> artworkListOptional = Optional.ofNullable(
                artworkRepository.findAllByGenreIdOrderByTitle(genreId));
        if (artworkListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_03_ARTWORK_NOT_FOUND);
        }
        return artMapper.artworkEntityListToArtworkResponseDTOList(artworkListOptional.get());
    }

    @Override
    public List<Integer> getListOfIds() {
        return artworkRepository.findAll().stream().map(artwork -> artwork.getId()).collect(Collectors.toList());
    }

    @Override
    public ArtistResponseDTO getArtistByArtworkId(int artworkId) {
        Optional<ArtworkEntity> artwork = artworkRepository.findById(artworkId);
        if(artwork.isEmpty()){
            throw new ArtNotFoundException(ErrorCode.ERR_03_ARTWORK_NOT_FOUND);
        }
        return artMapper.artistEntityToArtistResponseDTO(artwork.get().getArtist());
    }
}
