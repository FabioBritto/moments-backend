package br.com.britto.appmoments.advice;

import br.com.britto.appmoments.dto.ExceptionDTO;
import br.com.britto.appmoments.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleClienteNotFound(Exception e) {
        return ResponseEntity.status(404).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(UuidNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleUuidNotFound(Exception e) {
        return ResponseEntity.status(404).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(InvalidEventoException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidException(Exception e) {
        return ResponseEntity.status(400).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ExceptionDTO> handleFileStorageException(Exception e) {
        return ResponseEntity.status(400).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(StatusFinanceiroException.class)
    public ResponseEntity<ExceptionDTO> handleStatusFinanceiroException(Exception e) {
        return ResponseEntity.status(400).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(FotoConversionException.class)
    public ResponseEntity<ExceptionDTO> handleFotoConversion(Exception e) {
        return ResponseEntity.status(400).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(AlreadyExistingUniqueData.class)
    public ResponseEntity<ExceptionDTO> handleAlreadyExistingUniqueData(Exception e) {
        return ResponseEntity.status(400).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(WrongLoginDataException.class)
    public ResponseEntity<ExceptionDTO> handleWrongLoginData(Exception e) {
        return ResponseEntity.status(400).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(EventoNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleEventoNotFound(Exception e) {
        return ResponseEntity.status(404).body(new ExceptionDTO(e.getMessage()));
    }

    @ExceptionHandler(PagamentoException.class)
    public ResponseEntity<ExceptionDTO> handlePagamentoException(Exception e) {
        return ResponseEntity.status(400).body(new ExceptionDTO(e.getMessage()));
    }


}
