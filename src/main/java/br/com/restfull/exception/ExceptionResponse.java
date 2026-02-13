package br.com.restfull.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}
