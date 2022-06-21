package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.ArtworkResponseDTO;
import ie.licenta.artassistant.dto.FavouriteArtwork;
import ie.licenta.artassistant.dto.FavouriteArtworkRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtworkResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.ArtworkEntity;
import ie.licenta.artassistant.models.FavouriteArtworkEntity;
import ie.licenta.artassistant.models.FavouriteArtworkIdEntity;
import ie.licenta.artassistant.persistence.ArtworkRepository;
import ie.licenta.artassistant.persistence.FavouriteArtworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavouriteArtworkServiceImpl implements FavouriteArtworkService {

    private FavouriteArtworkRepository favouriteArtworkRepository;
    private ArtworkRepository artworkRepository;
    private ArtMapper artMapper;

    @Override
    public List<FavouriteArtwork> getFavouriteArtworksByUserId(int id) {
        Optional<List<FavouriteArtworkEntity>> favouriteListOptional = Optional.ofNullable(favouriteArtworkRepository
                .findAllByUserId(id));
        if (favouriteListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_01_RESOURCE_NOT_FOUND);
        }

        List<ArtworkEntity> artworkEntitites = favouriteListOptional.get().stream().map(favouriteArtwork ->
                artworkRepository.findById(favouriteArtwork.getArtworkId()).get()).collect(Collectors.toList());
        List<FavouriteArtwork> favouriteArtworks = artMapper.artworkEntityListToFavouriteArtworkList(artworkEntitites);
        return favouriteArtworks;
    }

    @Override
    @Transactional
    public FavouriteArtwork addFavouriteArtwork(FavouriteArtworkRequestDTO favouriteArtworkRequestDTO) {
        FavouriteArtworkEntity favouriteArtworkEntity = artMapper
                .favouriteArtworkRequestDTOToFavouriteArtworkEntity(favouriteArtworkRequestDTO);
        favouriteArtworkRepository.save(favouriteArtworkEntity);
        ArtworkEntity artworkEntity = artworkRepository.findById(favouriteArtworkEntity.getArtworkId()).get();
        return artMapper.artworkEntityToFavouriteArtwork(artworkEntity);
    }

    @Override
    @Transactional
    public void deleteFavouriteArtworkById(FavouriteArtworkIdEntity idEntity) {
        Optional<FavouriteArtworkEntity> favouriteOptional = Optional.ofNullable(favouriteArtworkRepository
                .findFavouriteArtworkEntityByUserIdAndArtworkId(idEntity.getUserId(), idEntity.getArtworkId()));
        if (favouriteOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_01_RESOURCE_NOT_FOUND);
        }
        favouriteArtworkRepository.deleteFavouriteArtworkEntityByUserIdAndArtworkId(
                idEntity.getUserId(), idEntity.getArtworkId());

    }
}
