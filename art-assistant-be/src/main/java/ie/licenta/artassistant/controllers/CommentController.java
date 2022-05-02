package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.CommentRequestDTO;
import ie.licenta.artassistant.dto.CommentResponseDTO;
import ie.licenta.artassistant.services.CommentService;
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
@ApiOperation("Comment API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Comment requests")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "Get Comment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Comment id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDTO.class)))
    })
    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable int id,
                                                           @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get comments by artwork id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Artworks not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CommentResponseDTO.class))))
    })
    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByArtworkId(
            @RequestParam(required = true) int artworkId,
            @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(commentService.getAllCommentsByArtwork(artworkId), HttpStatus.OK);
    }


    @Operation(summary = "Add a new Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "201", description = "Comment added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDTO.class)))
    })

    //TODO: add RequestAttribute id
    @PostMapping("/comment")
    public ResponseEntity<CommentResponseDTO> addComment(@Valid @RequestBody CommentRequestDTO CommentRequestDTO,
                                                       @RequestHeader("session_id") int sessionId) {
        return new ResponseEntity<>(commentService.addComment(CommentRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an Comment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Comment id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successfully deleted the Comment",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<CommentResponseDTO> deleteCommentById(@PathVariable int id,
                                                              @RequestHeader("session_id") int sessionId){
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
