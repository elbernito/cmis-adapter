package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for RetentionDto.
 * Verifies builder, constructors, getters, setters and toString.
 */
class RetentionDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        RetentionDto dto = RetentionDto.builder()
                .id("ret1")
                .objectId("doc1")
                .name("Legal Hold")
                .description("Tax compliance until 2030")
                .expirationDate("2030-12-31T23:59:59Z")
                .build();

        assertEquals("ret1", dto.getId());
        assertEquals("doc1", dto.getObjectId());
        assertEquals("Legal Hold", dto.getName());
        assertEquals("Tax compliance until 2030", dto.getDescription());
        assertEquals("2030-12-31T23:59:59Z", dto.getExpirationDate());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        RetentionDto dto = new RetentionDto();
        dto.setId("id2");
        dto.setObjectId("obj2");
        dto.setName("Retention2");
        dto.setDescription("Desc2");
        dto.setExpirationDate("2029-01-01T00:00:00Z");

        assertEquals("id2", dto.getId());
        assertEquals("obj2", dto.getObjectId());
        assertEquals("Retention2", dto.getName());
        assertEquals("Desc2", dto.getDescription());
        assertEquals("2029-01-01T00:00:00Z", dto.getExpirationDate());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        RetentionDto dto = new RetentionDto(
                "id3", "obj3", "Ret3", "Desc3", "2040-12-31T23:59:59Z"
        );

        assertEquals("id3", dto.getId());
        assertEquals("obj3", dto.getObjectId());
        assertEquals("Ret3", dto.getName());
        assertEquals("Desc3", dto.getDescription());
        assertEquals("2040-12-31T23:59:59Z", dto.getExpirationDate());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        RetentionDto dto = RetentionDto.builder()
                .id("RET")
                .expirationDate("2031-01-01T00:00:00Z")
                .build();

        String str = dto.toString();
        assertTrue(str.contains("RET"));
        assertTrue(str.contains("2031-01-01T00:00:00Z"));
    }
}
