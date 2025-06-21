package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for RepositoryMetaDto.
 * Verifies builder, constructors, getters, setters and toString.
 */
class RepositoryMetaDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        // Arrange & Act
        RepositoryMetaDto dto = RepositoryMetaDto.builder()
                .id("repo1")
                .name("Test Repo")
                .description("My repo")
                .build();

        // Assert
        assertEquals("repo1", dto.getId());
        assertEquals("Test Repo", dto.getName());
        assertEquals("My repo", dto.getDescription());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        // Arrange
        RepositoryMetaDto dto = new RepositoryMetaDto();

        // Act
        dto.setId("id2");
        dto.setName("Name2");
        dto.setDescription("Desc2");

        // Assert
        assertEquals("id2", dto.getId());
        assertEquals("Name2", dto.getName());
        assertEquals("Desc2", dto.getDescription());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        // Arrange & Act
        RepositoryMetaDto dto = new RepositoryMetaDto("id3", "Repo3", "Desc3");

        // Assert
        assertEquals("id3", dto.getId());
        assertEquals("Repo3", dto.getName());
        assertEquals("Desc3", dto.getDescription());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        // Arrange
        RepositoryMetaDto dto = RepositoryMetaDto.builder()
                .id("abc")
                .name("Test")
                .description("Text")
                .build();

        // Act
        String result = dto.toString();

        // Assert
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("Test"));
        assertTrue(result.contains("Text"));
    }
}
