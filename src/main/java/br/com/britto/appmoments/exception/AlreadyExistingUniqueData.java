package br.com.britto.appmoments.exception;

public class AlreadyExistingUniqueData extends RuntimeException {
    public AlreadyExistingUniqueData(String message) {
        super(message);
    }
}
