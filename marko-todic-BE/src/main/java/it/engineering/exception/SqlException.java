package it.engineering.exception;

import it.engineering.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SqlException extends SQLException {
    private ApiResponse apiResponse;


    public SqlException(String message) {
        super(message);
    }

    public SqlException(String reason, ApiResponse apiResponse) {
        super(reason);
        this.apiResponse = apiResponse;
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }
}
