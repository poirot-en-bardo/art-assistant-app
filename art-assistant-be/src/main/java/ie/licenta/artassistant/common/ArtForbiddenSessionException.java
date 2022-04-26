package ie.licenta.artassistant.common;

public class ArtForbiddenSessionException extends GenericException {
    public ArtForbiddenSessionException(ErrorCode errorCode) {
        super(errorCode);
    }
}
