package ie.licenta.artassistant.mappers;

import ie.licenta.artassistant.dto.*;
import ie.licenta.artassistant.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtMapper {

    ArtistResponseDTO artistEntityToArtistResponseDTO(ArtistEntity artistEntity);
    ArtistEntity artistRequestDTOToArtistEntity(ArtistRequestDTO artistRequestDTO);

    ArtworkResponseDTO artworkEntityToArtworkResponseDTO(ArtworkEntity artworkEntity);
    List<ArtworkResponseDTO> artworkEntityListToArtworkResponseDTOList(List<ArtworkEntity> artworkEntityList);
    ArtworkEntity artworkRequestDTOToArtworkEntity(ArtworkRequestDTO artworkRequestDTO);

    @Mapping(target = "id", source = "artworkId")
    ArtworkEntity artworkRequestDTOToArtworkEntityWithId(Integer artworkId, ArtworkRequestDTO artworkRequestDTO);

    CommentResponseDTO commentEntityToCommentResponseDTO(CommentEntity commentEntity);

    CommentEntity commentRequestDTOToCommentEntity(CommentRequestDTO commentRequestDTO);

    CountryResponseDTO countryEntityToCountryResponseDTO(CountryEntity countryEntity);

    CountryEntity countryRequestDTOToCountryEntity(CountryRequestDTO countryRequestDTO);

    FavouriteArtistResponseDTO favouriteArtistEntityToFavouriteArtistResponseDTO(FavouriteArtistEntity favouriteArtistEntity);

    FavouriteArtistEntity favouriteArtistRequestDTOToFavouriteArtistEntity(FavouriteArtistRequestDTO favouriteArtistRequestDTO);

    FavouriteArtworkResponseDTO favouriteArtworkEntityToFavouriteArtworkResponseDTO(FavouriteArtworkEntity favouriteArtworkEntity);

    FavouriteArtworkEntity favouriteArtworkRequestDTOToArtworkEntity(FavouriteArtworkRequestDTO favouriteArtworkRequestDTO);

    GalleryResponseDTO galleryEntityToGalleryResponseDTO(GalleryEntity galleryEntity);

    GalleryEntity galleryRequestDTOToGalleryEntity(GalleryRequestDTO galleryRequestDTO);

    GenreResponseDTO genreEntityToGenreResponseDTO(GenreEntity genreEntity);

    GenreEntity genreRequestDTOToGenreEntity(GenreRequestDTO genreRequestDTO);

    MuseumResponseDTO museumEntityToMuseumResponseDTO(MuseumEntity museumEntity);

    MuseumEntity museumRequestDTOToMuseumEntity(MuseumRequestDTO museumRequestDTO);

    UserResponseDTO userEntityToUserResponseDTO(UserEntity userEntity);

    UserEntity userRequestDTOToUserEntity(UserRequestDTO userRequestDTO);
}
