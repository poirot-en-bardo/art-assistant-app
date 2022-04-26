package ie.licenta.artassistant.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericException extends RuntimeException {
    public ErrorCode errorCode;
}
