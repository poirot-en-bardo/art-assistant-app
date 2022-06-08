package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.RoomRequestDTO;
import ie.licenta.artassistant.dto.RoomResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.RoomEntity;
import ie.licenta.artassistant.persistence.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ArtMapper artMapper;

    @Override
    public RoomResponseDTO getRoomById(int id) {
        RoomEntity room = roomRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_15_ROOM_NOT_FOUND));
        return artMapper.roomEntityToRoomResponseDTO(room);
    }

    @Override
    public List<RoomResponseDTO> getAllRoomsByGalleryId(int galleryId) {
        Optional<List<RoomEntity>> roomListOptional = Optional.ofNullable(
                roomRepository.findRoomEntitiesByGalleryIdOrderById(galleryId));
        if (roomListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_15_ROOM_NOT_FOUND);
        }
        return artMapper.roomEntityListToRoomResponseDTOList(roomListOptional.get());
    }

    @Override
    @Transactional
    public RoomResponseDTO addRoom(RoomRequestDTO roomRequestDTO) {
        return artMapper.roomEntityToRoomResponseDTO(roomRepository.save(
                artMapper.roomRequestDTOToRoomEntity(roomRequestDTO)));
    }

    @Override
    public RoomResponseDTO updateRoom(int id, RoomRequestDTO roomRequestDTO) {
        RoomEntity oldRoom = roomRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_15_ROOM_NOT_FOUND));
        if (oldRoom == null) {
            return null;
        } else {
            RoomEntity roomEntity = artMapper.roomRequestDTOToRoomEntityWithId(id, roomRequestDTO);
            return artMapper.roomEntityToRoomResponseDTO(roomRepository.save(roomEntity));
        }
    }

    @Override
    @Transactional
    public void deleteRoomById(int id) {
        RoomEntity room = roomRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_15_ROOM_NOT_FOUND));
        roomRepository.deleteById(id);
    }
}
