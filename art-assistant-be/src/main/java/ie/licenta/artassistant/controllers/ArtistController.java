package ie.licenta.artassistant.controllers;


import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.ArtistRequestDTO;
import ie.licenta.artassistant.dto.ArtistResponseDTO;
import ie.licenta.artassistant.services.ArtistService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@ApiOperation("Artist API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Artist requests")
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @Operation(summary = "Get artist by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Artist not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Artist id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtistResponseDTO.class)))
    })
    @GetMapping("/artist/{id}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable int id,
                                                           @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(artistService.getArtistById(id), HttpStatus.OK);
    }

    @Operation(summary = "Add a new artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "201", description = "Artist added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtistResponseDTO.class)))
    })
    @PostMapping("/artist")
    public ResponseEntity<ArtistResponseDTO> addArtist(@Valid @RequestBody ArtistRequestDTO artistRequestDTO,
                                                       @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(artistService.addArtist(artistRequestDTO), HttpStatus.CREATED);
    }


    @Operation(summary = "Update an artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful update",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtistResponseDTO.class)))
    })
    @PutMapping("/artist/{id}")
    public ResponseEntity<ArtistResponseDTO> updateArtistById(@Valid @RequestBody ArtistRequestDTO artistRequestDTO,
                                                                @PathVariable int id,
                                                                @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(artistService.updateArtist(id, artistRequestDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete an artist by id")
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
    @DeleteMapping("/artist/{id}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable int id,
                                                           @RequestHeader("session_id") String sessionId){
        artistService.deleteArtistById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
