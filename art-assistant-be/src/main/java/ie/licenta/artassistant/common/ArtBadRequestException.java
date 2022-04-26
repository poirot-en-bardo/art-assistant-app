package ie.licenta.artassistant.common;

public class ArtBadRequestException extends GenericException {
    public ArtBadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
