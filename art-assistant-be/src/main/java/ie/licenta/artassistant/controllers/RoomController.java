package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.dto.RoomRequestDTO;
import ie.licenta.artassistant.dto.RoomResponseDTO;
import ie.licenta.artassistant.dto.RoomResponseDTO;
import ie.licenta.artassistant.dto.RoomResponseDTO;
import ie.licenta.artassistant.services.RoomService;
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
@ApiOperation("Room API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Room requests")
@AllArgsConstructor
public class RoomController {
    
    private final RoomService roomService;

    @Operation(summary = "Get room by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Room not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Room id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponseDTO.class)))
    })
    @GetMapping("/room/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable int id
//                                                           @RequestHeader("session_id") String sessionId
    ) {
        return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
    }


    @Operation(summary = "Get rooms by gallery id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Rooms not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomResponseDTO.class))))
    })
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsByGalleryId(
            @RequestParam(required = true) int galleryId
//            @RequestHeader("session_id") String sessionId
    ) {
        return new ResponseEntity<>(roomService
                .getAllRoomsByGalleryId(galleryId), HttpStatus.OK);
    }

    @Operation(summary = "Add a new room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "201", description = "Room added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponseDTO.class)))
    })
    @PostMapping("/room")
    public ResponseEntity<RoomResponseDTO> addRoom(@Valid @RequestBody RoomRequestDTO roomRequestDTO,
                                                       @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(roomService.addRoom(roomRequestDTO), HttpStatus.CREATED);
    }


    @Operation(summary = "Update a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful update",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponseDTO.class)))
    })
    @PutMapping("/room/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoomById(@Valid @RequestBody RoomRequestDTO roomRequestDTO,
                                                              @PathVariable int id,
                                                              @RequestHeader("session_id") String sessionId) {
        return new ResponseEntity<>(roomService.updateRoom(id, roomRequestDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete a room by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Room not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid room id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successfully deleted the room",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/room/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable int id,
                                                 @RequestHeader("session_id") String sessionId){
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
