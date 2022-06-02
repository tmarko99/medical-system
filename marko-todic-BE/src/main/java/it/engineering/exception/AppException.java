package it.engineering.exception;

import it.engineering.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException extends RuntimeException {
    ApiResponse apiResponse;

    public AppException(ApiResponse apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }
}
