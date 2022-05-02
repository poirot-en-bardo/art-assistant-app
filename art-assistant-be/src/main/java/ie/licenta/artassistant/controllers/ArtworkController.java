package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.ArtworkRequestDTO;
import ie.licenta.artassistant.dto.ArtworkResponseDTO;
import ie.licenta.artassistant.services.ArtworkService;
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
@ApiOperation("Artwork API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Artwork requests")
@AllArgsConstructor
public class ArtworkController {

    private final ArtworkService artworkService;

    @Operation(summary = "Get Artwork by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Artwork not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Artwork id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtworkResponseDTO.class)))
    })
    @GetMapping("/artwork/{id}")
    public ResponseEntity<ArtworkResponseDTO> getArtworkById(@PathVariable int id,
                                                           @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(artworkService.getArtworkById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get artworks by gallery id and room number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Artworks not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArtworkResponseDTO.class))))
    })
    @GetMapping("/artworks")
    public ResponseEntity<List<ArtworkResponseDTO>> getArtworksByGalleryIdAndRoomNumber(
            @RequestParam(required = true) int galleryId,
            @RequestParam(required = true) int roomNumber,
            @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(artworkService.
                getAllArtworksByGalleryIdAndRoomNumber(galleryId, roomNumber), HttpStatus.OK);
    }

    @Operation(summary = "Get artworks by artist id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Artworks not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArtworkResponseDTO.class))))
    })
    @GetMapping("/artwork")
    public ResponseEntity<List<ArtworkResponseDTO>> getArtworksByArtistId(
            @RequestParam(required = true) int artistId,
            @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(artworkService.
                getAllArtworksByArtistId(artistId), HttpStatus.OK);
    }

    @Operation(summary = "Add a new artwork")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "201", description = "Artwork added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtworkResponseDTO.class)))
    })
    @PostMapping("/artwork")
    public ResponseEntity<ArtworkResponseDTO> addArtwork(@Valid @RequestBody ArtworkRequestDTO artworkRequestDTO,
                                                       @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(artworkService.addArtwork(artworkRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an artwork")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful update",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtworkResponseDTO.class)))
    })
    @PutMapping("/artwork/{id}")
    public ResponseEntity<ArtworkResponseDTO> updateArtworkById(@Valid @RequestBody ArtworkRequestDTO artworkRequestDTO,
                                                         @PathVariable int id,
                                                         @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(artworkService.updateArtwork(id, artworkRequestDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete an Artwork by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Artwork not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Artwork id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successfully deleted the artwork",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/artwork/{id}")
    public ResponseEntity<ArtworkResponseDTO> deleteArtworkById(@PathVariable int id,
                                                              @RequestHeader("session_id") int sessionId){
        artworkService.deleteArtworkById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
