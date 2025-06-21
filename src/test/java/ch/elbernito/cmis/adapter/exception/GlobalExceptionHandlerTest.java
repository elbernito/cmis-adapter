package ch.elbernito.cmis.adapter.exception;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for GlobalExceptionHandler.
 * Each test verifies correct HTTP status, message and logging for the respective exception.
 */
class GlobalExceptionHandlerTest {

    /**
     * Tests handling of ResourceNotFoundException.
     */
    @Test
    @DisplayName("testWhenResourceNotFoundThenReturns404")
    void testWhenResourceNotFoundThenReturns404() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResourceNotFoundException ex = new ResourceNotFoundException("test resource");

        // Act
        ResponseEntity<String> response = handler.handleResourceNotFound(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("test resource"));
    }

    /**
     * Tests handling of ConstraintViolationException.
     */
    @Test
    @DisplayName("testWhenConstraintViolationThenReturns400")
    void testWhenConstraintViolationThenReturns400() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ConstraintViolationException ex = new ConstraintViolationException("violation", Collections.emptySet());

        // Act
        ResponseEntity<String> response = handler.handleConstraintViolation(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("violation"));
    }

    /**
     * Tests handling of MethodArgumentNotValidException.
     */
    @Test
    @DisplayName("testWhenMethodArgumentNotValidThenReturns400")
    void testWhenMethodArgumentNotValidThenReturns400() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        BindException bindException = new BindException(new Object(), "test");
        bindException.addError(new ObjectError("object", "error 1"));
        bindException.addError(new ObjectError("object", "error 2"));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException.getBindingResult());

        // Act
        ResponseEntity<String> response = handler.handleMethodArgumentNotValid(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("error 1"));
        assertTrue(response.getBody().contains("error 2"));
    }

    /**
     * Tests handling of MissingServletRequestParameterException.
     */
    @Test
    @DisplayName("testWhenMissingServletRequestParameterThenReturns400")
    void testWhenMissingServletRequestParameterThenReturns400() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        MissingServletRequestParameterException ex =
                new MissingServletRequestParameterException("paramName", "String");

        // Act
        ResponseEntity<String> response = handler.handleMissingServletRequestParameter(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("paramName"));
    }

    /**
     * Tests handling of HttpMediaTypeNotSupportedException.
     */
    @Test
    @DisplayName("testWhenMediaTypeNotSupportedThenReturns415")
    void testWhenMediaTypeNotSupportedThenReturns415() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        HttpMediaTypeNotSupportedException ex =
                new HttpMediaTypeNotSupportedException("application/unsupported-type");

        // Act
        ResponseEntity<String> response = handler.handleHttpMediaTypeNotSupported(ex);

        // Assert
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
        assertTrue(response.getBody().contains("application/unsupported-type"));
    }

    /**
     * Tests handling of IllegalArgumentException.
     */
    @Test
    @DisplayName("testWhenIllegalArgumentThenReturns400")
    void testWhenIllegalArgumentThenReturns400() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        IllegalArgumentException ex = new IllegalArgumentException("illegal arg");

        // Act
        ResponseEntity<String> response = handler.handleIllegalArgument(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("illegal arg"));
    }

    /**
     * Tests handling of RuntimeException.
     */
    @Test
    @DisplayName("testWhenRuntimeExceptionThenReturns500")
    void testWhenRuntimeExceptionThenReturns500() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException ex = new RuntimeException("runtime error");

        // Act
        ResponseEntity<String> response = handler.handleRuntimeException(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("runtime error"));
    }

    /**
     * Tests handling of generic Exception.
     */
    @Test
    @DisplayName("testWhenGenericExceptionThenReturns500")
    void testWhenGenericExceptionThenReturns500() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        Exception ex = new Exception("unexpected error");

        // Act
        ResponseEntity<String> response = handler.handleException(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("unexpected error"));
    }
}
