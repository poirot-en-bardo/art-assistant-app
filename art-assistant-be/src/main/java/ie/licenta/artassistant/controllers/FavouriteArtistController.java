package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.*;
import ie.licenta.artassistant.models.FavouriteArtistIdEntity;
import ie.licenta.artassistant.services.FavouriteArtistService;
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
@ApiOperation("Favourite Artists API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Favourite Artists requests")
@AllArgsConstructor
public class FavouriteArtistController {

    private final FavouriteArtistService favouriteArtistService;

    @Operation(summary = "Get favourite artists by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Favourite Artists not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArtworkResponseDTO.class))))
    })
    @GetMapping("/favourite_artists/{userId}")
    public ResponseEntity<List<FavouriteArtistResponseDTO>> getArtworksByGalleryIdAndRoomNumber(
            @PathVariable int userId,
            @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(favouriteArtistService.getFavouriteArtistsByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Add a new favourite artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "201", description = "Favourite artist added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtistResponseDTO.class)))
    })
    @PostMapping("/favourite_artist")
    public ResponseEntity<FavouriteArtistResponseDTO> addArtist(@Valid @RequestBody FavouriteArtistRequestDTO favouriteArtistRequestDTO,
                                                                @RequestHeader("session_id") String sessionId,
                                                                @RequestAttribute("userId") int userId) {
        return new ResponseEntity<>(favouriteArtistService.addFavouriteArtist(userId, favouriteArtistRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a favourite artist by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Artist not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Artist id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successfully deleted the artist",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/favourite_artist/{artistId}")
    public ResponseEntity<FavouriteArtistResponseDTO> deleteFavouriteArtistById(@PathVariable int artistId,
                                                                                @RequestHeader("session_id") String sessionId,
                                                                                @RequestAttribute("userId") int userId) {
        FavouriteArtistIdEntity idEntity = new FavouriteArtistIdEntity(userId, artistId);
        favouriteArtistService.deleteFavouriteArtistById(idEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
