package ie.licenta.artassistant.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtError extends RuntimeException {

    private final ErrorCode errorCode;
    private final String errorMessage;
}
