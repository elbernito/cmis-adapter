package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for ObjectDto.
 * Verifies builder, constructors, getters, setters and toString.
 */
class ObjectDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        ObjectDto dto = ObjectDto.builder()
                .id("obj1")
                .name("TestObject")
                .type("document")
                .path("/docs/file.pdf")
                .build();

        assertEquals("obj1", dto.getId());
        assertEquals("TestObject", dto.getName());
        assertEquals("document", dto.getType());
        assertEquals("/docs/file.pdf", dto.getPath());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        ObjectDto dto = new ObjectDto();
        dto.setId("obj2");
        dto.setName("Object2");
        dto.setType("folder");
        dto.setPath("/folder/path");

        assertEquals("obj2", dto.getId());
        assertEquals("Object2", dto.getName());
        assertEquals("folder", dto.getType());
        assertEquals("/folder/path", dto.getPath());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        ObjectDto dto = new ObjectDto("idX", "ObjX", "document", "/x");

        assertEquals("idX", dto.getId());
        assertEquals("ObjX", dto.getName());
        assertEquals("document", dto.getType());
        assertEquals("/x", dto.getPath());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        ObjectDto dto = ObjectDto.builder()
                .id("OBJX")
                .name("Test")
                .path("/a/b")
                .build();

        String str = dto.toString();
        assertTrue(str.contains("OBJX"));
        assertTrue(str.contains("Test"));
        assertTrue(str.contains("/a/b"));
    }
}
