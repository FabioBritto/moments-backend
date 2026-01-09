package br.com.britto.appmoments.exception;

public class WrongLoginDataException extends RuntimeException {
    public WrongLoginDataException(String message) {
        super(message);
    }
}
