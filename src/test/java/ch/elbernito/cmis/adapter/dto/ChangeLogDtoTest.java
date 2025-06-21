package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for ChangeLogDto.
 * Tests all constructors, builder, setters/getters and toString, using LocalDateTime for changeTime.
 */
class ChangeLogDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        // Arrange
        LocalDateTime dt = LocalDateTime.of(2024, 6, 20, 19, 30, 0);

        // Act
        ChangeLogDto dto = ChangeLogDto.builder()
                .id("clog123")
                .changeType("created")
                .objectId("doc42")
                .user("alice")
                .changeTime(dt)
                .info("Version updated")
                .build();

        // Assert
        assertEquals("clog123", dto.getId());
        assertEquals("created", dto.getChangeType());
        assertEquals("doc42", dto.getObjectId());
        assertEquals("alice", dto.getUser());
        assertEquals(dt, dto.getChangeTime());
        assertEquals("Version updated", dto.getInfo());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        // Arrange
        ChangeLogDto dto = new ChangeLogDto();
        LocalDateTime dt = LocalDateTime.now();

        // Act
        dto.setId("id2");
        dto.setChangeType("deleted");
        dto.setObjectId("doc2");
        dto.setUser("bob");
        dto.setChangeTime(dt);
        dto.setInfo("Removed by admin");

        // Assert
        assertEquals("id2", dto.getId());
        assertEquals("deleted", dto.getChangeType());
        assertEquals("doc2", dto.getObjectId());
        assertEquals("bob", dto.getUser());
        assertEquals(dt, dto.getChangeTime());
        assertEquals("Removed by admin", dto.getInfo());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        // Arrange
        LocalDateTime dt = LocalDateTime.of(2025, 1, 1, 8, 15, 0);

        // Act
        ChangeLogDto dto = new ChangeLogDto("id3", "updated", "doc99", "carol", dt, "Changed title");

        // Assert
        assertEquals("id3", dto.getId());
        assertEquals("updated", dto.getChangeType());
        assertEquals("doc99", dto.getObjectId());
        assertEquals("carol", dto.getUser());
        assertEquals(dt, dto.getChangeTime());
        assertEquals("Changed title", dto.getInfo());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        // Arrange
        LocalDateTime dt = LocalDateTime.of(2030, 7, 10, 12, 0);
        ChangeLogDto dto = ChangeLogDto.builder()
                .id("CLG")
                .changeTime(dt)
                .changeType("archived")
                .build();

        // Act
        String str = dto.toString();

        // Assert
        assertTrue(str.contains("CLG"));
        assertTrue(str.contains("archived"));
        assertTrue(str.contains("2030-07-10T12:00"));
    }
}
