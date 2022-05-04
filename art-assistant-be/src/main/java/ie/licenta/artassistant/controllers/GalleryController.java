package ie.licenta.artassistant.controllers;


import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.GalleryResponseDTO;
import ie.licenta.artassistant.services.GalleryService;
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

import java.util.List;

@RestController
@ApiOperation("Gallery API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Gallery requests")
@AllArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @Operation(summary = "Get gallery by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Gallery not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid gallery id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GalleryResponseDTO.class)))
    })
    @GetMapping("/gallery/{id}")
    public ResponseEntity<GalleryResponseDTO> getGalleryById(@PathVariable int id,
                                                             @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(galleryService.getGalleryById(id), HttpStatus.OK);
    }


    @Operation(summary = "Get galleries by museum id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Favourite Artworks not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GalleryResponseDTO.class))))
    })
    @GetMapping("/galleries")
    public ResponseEntity<List<GalleryResponseDTO>> getAllGalleriesByMuseumId(@RequestParam(required = true) int museumId,
                                                                              @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(galleryService.getAllGalleriesByMuseumId(museumId), HttpStatus.OK);
    }
}
