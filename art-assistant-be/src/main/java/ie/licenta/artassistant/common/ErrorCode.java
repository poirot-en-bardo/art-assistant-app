package ie.licenta.artassistant.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    ERR_01_ARTIST_NOT_FOUND("Artist not found"),
    ERR_02_ARTWORK_NOT_FOUND("No artworks found"),
    ERR_02_BAD_REQUEST("Bad Request"),
    ERR_03_INTERNAL_SERVER_ERROR("Internal server error"),
    ERR_04_SESSION_NOT_FOUND("Session not found");

    public final String EN_MESSAGE;
}
