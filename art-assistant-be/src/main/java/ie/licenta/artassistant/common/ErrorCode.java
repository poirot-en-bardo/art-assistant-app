package ie.licenta.artassistant.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    ERR_01_RESOURCE_NOT_FOUND("Resource not found"),
    ERR_02_ARTIST_NOT_FOUND("Artist not found"),
    ERR_03_ARTWORK_NOT_FOUND("No artworks found"),
    ERR_04_COMMENT_NOT_FOUND("Comment not found"),
    ERR_05_GALLERY_NOT_FOUND("Gallery not found"),
    ERR_06_GENRE_NOT_FOUND("Genre not found"),
    ERR_07_MUSEUM_NOT_FOUND("Museum not found"),
    ERR_08_USER_NOT_FOUND("User not found"),
    ERR_09_USER_EMAIL_TAKEN("The email is already registered"),
    ERR_02_BAD_REQUEST("Bad Request"),
    ERR_03_INTERNAL_SERVER_ERROR("Internal server error"),
    ERR_10_SESSION_NOT_FOUND("Session not found"),
    ERR_11_SESSION_FORBIDDEN("Session not found");

    public final String EN_MESSAGE;
}
