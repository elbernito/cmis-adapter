package ch.elbernito.cmis.adapter.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for ResourceNotFoundException.
 * Tests creation and message propagation.
 */
class ResourceNotFoundExceptionTest {

    @Test
    @DisplayName("testWhenCreateWithMessageThenMessageIsAvailable")
    void testWhenCreateWithMessageThenMessageIsAvailable() {
        // Arrange
        String message = "Document not found";

        // Act
        ResourceNotFoundException ex = new ResourceNotFoundException(message);

        // Assert
        assertNotNull(ex);
        assertEquals(message, ex.getMessage());
    }
}
