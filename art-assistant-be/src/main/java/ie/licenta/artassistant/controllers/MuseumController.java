package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.GalleryRequestDTO;
import ie.licenta.artassistant.dto.GalleryResponseDTO;
import ie.licenta.artassistant.dto.MuseumRequestDTO;
import ie.licenta.artassistant.dto.MuseumResponseDTO;
import ie.licenta.artassistant.services.MuseumService;
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
@ApiOperation("Museum API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Museum requests")
@AllArgsConstructor
public class MuseumController {

    private final MuseumService museumService;

    @Operation(summary = "Get all museums")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Museums not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MuseumResponseDTO.class))))
    })
    @GetMapping("/museums")
    public ResponseEntity<List<MuseumResponseDTO>> getAllMuseums(
            @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(museumService.getAllMuseums(), HttpStatus.OK);
    }

    @Operation(summary = "Get museum by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Museum not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Museum id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MuseumResponseDTO.class)))
    })
    @GetMapping("/museum/{id}")
    public ResponseEntity<MuseumResponseDTO> getMuseumById(@PathVariable int id,
                                                         @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(museumService.getMuseumById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get museums by country id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Museums not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MuseumResponseDTO.class))))
    })
    @GetMapping("/museum")
    public ResponseEntity<List<MuseumResponseDTO>> getMuseumsByCountryId(
            @RequestParam(required = true) int countryId,
            @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(museumService
                .getAllMuseumsByCountryId(countryId), HttpStatus.OK);
    }


    @Operation(summary = "Add a new museum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "201", description = "Museum added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MuseumResponseDTO.class)))
    })
    @PostMapping("/museum")
    public ResponseEntity<MuseumResponseDTO> addMuseum(@Valid @RequestBody MuseumRequestDTO museumRequestDTO,
                                                         @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(museumService.addMuseum(museumRequestDTO), HttpStatus.CREATED);
    }


    @Operation(summary = "Update a museum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful update",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MuseumResponseDTO.class)))
    })
    @PutMapping("/museum/{id}")
    public ResponseEntity<MuseumResponseDTO> updateMuseumById(@Valid @RequestBody MuseumRequestDTO MuseumRequestDTO,
                                                                @PathVariable int id,
                                                                @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(museumService.updateMuseum(id, MuseumRequestDTO), HttpStatus.OK);
    }
}
