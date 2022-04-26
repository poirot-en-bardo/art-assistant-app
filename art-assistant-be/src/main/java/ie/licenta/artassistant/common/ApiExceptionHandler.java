package ie.licenta.artassistant.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArtNotFoundException.class)
    public ResponseEntity<ArtError> handleArtNotFoundException(ArtNotFoundException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(new ArtError(errorCode, errorCode.EN_MESSAGE), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArtBadRequestException.class)
    public ResponseEntity<ArtError> handleArtBadRequestException(ArtBadRequestException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(new ArtError(errorCode, errorCode.EN_MESSAGE), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArtInternalServerErrorException.class)
    public ResponseEntity<ArtError> handleArtInternalServerErrorException(ArtInternalServerErrorException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(new ArtError(errorCode, errorCode.EN_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArtForbiddenSessionException.class)
    public ResponseEntity<ArtError> handleArtForbiddenSessionException(ArtForbiddenSessionException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(new ArtError(errorCode, errorCode.EN_MESSAGE), HttpStatus.FORBIDDEN);
    }
}
