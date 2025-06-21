package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for VersionDto.
 * Follows Arrange/Act/Assert schema for each test.
 */
class VersionDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        // Arrange
        String label = "1.0";
        String documentId = "doc123";
        String timestamp = "2024-06-20T19:48:00Z";
        String author = "john.doe";
        String comment = "Initial version";

        // Act
        VersionDto dto = VersionDto.builder()
                .label(label)
                .documentId(documentId)
                .timestamp(timestamp)
                .author(author)
                .comment(comment)
                .build();

        // Assert
        assertEquals(label, dto.getLabel());
        assertEquals(documentId, dto.getDocumentId());
        assertEquals(timestamp, dto.getTimestamp());
        assertEquals(author, dto.getAuthor());
        assertEquals(comment, dto.getComment());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        // Arrange
        VersionDto dto = new VersionDto();

        // Act
        dto.setLabel("1.2");
        dto.setDocumentId("docX");
        dto.setTimestamp("2025-01-01T10:00:00Z");
        dto.setAuthor("jane.doe");
        dto.setComment("Bugfix");

        // Assert
        assertEquals("1.2", dto.getLabel());
        assertEquals("docX", dto.getDocumentId());
        assertEquals("2025-01-01T10:00:00Z", dto.getTimestamp());
        assertEquals("jane.doe", dto.getAuthor());
        assertEquals("Bugfix", dto.getComment());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        // Arrange
        String label = "2.0";
        String documentId = "docZ";
        String timestamp = "2030-12-31T23:59:59Z";
        String author = "max.mustermann";
        String comment = "Major release";

        // Act
        VersionDto dto = new VersionDto(label, documentId, timestamp, author, comment);

        // Assert
        assertEquals(label, dto.getLabel());
        assertEquals(documentId, dto.getDocumentId());
        assertEquals(timestamp, dto.getTimestamp());
        assertEquals(author, dto.getAuthor());
        assertEquals(comment, dto.getComment());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        // Arrange
        VersionDto dto = VersionDto.builder()
                .label("VER1")
                .documentId("DOCID")
                .timestamp("2222-01-01T01:01:01Z")
                .author("AUTH")
                .build();

        // Act
        String str = dto.toString();

        // Assert
        assertTrue(str.contains("VER1"));
        assertTrue(str.contains("DOCID"));
        assertTrue(str.contains("2222-01-01T01:01:01Z"));
        assertTrue(str.contains("AUTH"));
    }
}
