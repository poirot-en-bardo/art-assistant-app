package ie.licenta.artassistant.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    ERR_01_RESOURCE_NOT_FOUND("The resource you're looking for doesn't exist"),
    ERR_02_BAD_REQUEST("Bad Request"),
    ERR_03_INTERNAL_SERVER_ERROR("Internal server error"),
    ERR_04_SESSION_NOT_FOUND("Session not found");

    public final String EN_MESSAGE;
}
