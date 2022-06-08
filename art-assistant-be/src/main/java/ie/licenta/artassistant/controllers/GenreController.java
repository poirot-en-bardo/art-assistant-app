package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.*;
import ie.licenta.artassistant.services.GenreService;
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
@ApiOperation("Genre API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Genre requests")
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @Operation(summary = "Get genre by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Genre not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Genre id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreResponseDTO.class)))
    })
    @GetMapping("/genre/{id}")
    public ResponseEntity<GenreResponseDTO> getGenreById(@PathVariable int id,
                                                             @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(genreService.getGenreById(id), HttpStatus.OK);
    }

    @Operation(summary = "Add a new genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "201", description = "Genre added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreResponseDTO.class)))
    })
    @PostMapping("/genre")
    public ResponseEntity<GenreResponseDTO> addGenre(@Valid @RequestBody GenreRequestDTO genreRequestDTO,
                                                         @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(genreService.addGenre(genreRequestDTO), HttpStatus.CREATED);
    }


    @Operation(summary = "Update a genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful update",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreResponseDTO.class)))
    })
    @PutMapping("/genre/{id}")
    public ResponseEntity<GenreResponseDTO> updateGenreById(@Valid @RequestBody GenreRequestDTO genreRequestDTO,
                                                                @PathVariable int id,
                                                                @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(genreService.updateGenre(id, genreRequestDTO), HttpStatus.OK);
    }
}
