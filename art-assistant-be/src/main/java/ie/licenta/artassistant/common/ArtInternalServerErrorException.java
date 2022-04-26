package ie.licenta.artassistant.common;

public class ArtInternalServerErrorException extends GenericException {
    public ArtInternalServerErrorException(ErrorCode errorCode) {
        super(errorCode);
    }
}
