package ie.licenta.artassistant.common;

public class ArtNotFoundException extends GenericException {
    public ArtNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
