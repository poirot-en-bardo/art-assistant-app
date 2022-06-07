package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.*;
import ie.licenta.artassistant.models.FavouriteArtworkIdEntity;
import ie.licenta.artassistant.services.FavouriteArtworkService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@ApiOperation("Favourite Artworks API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Favourite Artworks requests")
@AllArgsConstructor
public class FavouriteArtworkController {

    private final FavouriteArtworkService favouriteArtworkService;

    @Operation(summary = "Get favourite Artworks by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Favourite Artworks not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArtworkResponseDTO.class))))
    })
    @GetMapping("/favourite_artworks/{userId}")
    public ResponseEntity<List<FavouriteArtworkResponseDTO>> getArtworksByGalleryIdAndRoomNumber(
            @PathVariable int userId,
            @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(favouriteArtworkService.getFavouriteArtworksByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Add a new favourite Artwork")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "201", description = "Favourite Artwork added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtworkResponseDTO.class)))
    })
    @PostMapping("/favourite_artwork")
    public ResponseEntity<FavouriteArtworkResponseDTO> addArtwork(@Valid @RequestBody FavouriteArtworkRequestDTO favouriteArtworkRequestDTO,
                                                                @RequestHeader("session_id") String sessionId,
                                                                @RequestAttribute("userId") int userId) {
        return new ResponseEntity<>(favouriteArtworkService.addFavouriteArtwork(userId, favouriteArtworkRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a favourite Artwork by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Artwork not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Artwork id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successfully deleted the Artwork",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/favourite_artwork/{artworkId}")
    public ResponseEntity<FavouriteArtworkResponseDTO> deleteFavouriteArtworkById(@PathVariable int artworkId,
                                                                                @RequestHeader("session_id") String sessionId,
                                                                                @RequestAttribute("userId") int userId) {
        FavouriteArtworkIdEntity idEntity = new FavouriteArtworkIdEntity(userId, artworkId);
        favouriteArtworkService.deleteFavouriteArtworkById(idEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
