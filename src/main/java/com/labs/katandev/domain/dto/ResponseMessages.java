package com.labs.katandev.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ResponseMessages {

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "MM-dd-yyyy hh:mm:ss",
            timezone = "Asia/Jakarta"
    )
    private Date timeStamp;

    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    public ResponseMessages() {}

    public ResponseMessages(
            int httpStatusCode,
            HttpStatus httpStatus,
            String reason,
            String message
    ) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
    }
}
