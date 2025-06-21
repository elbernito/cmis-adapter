package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for MetadataDto.
 * Verifies builder, constructors, getters, setters and toString.
 */
class MetadataDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        MetadataDto dto = MetadataDto.builder()
                .id("meta1")
                .documentId("docX")
                .key("department")
                .value("Finance")
                .build();

        assertEquals("meta1", dto.getId());
        assertEquals("docX", dto.getDocumentId());
        assertEquals("department", dto.getKey());
        assertEquals("Finance", dto.getValue());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        MetadataDto dto = new MetadataDto();
        dto.setId("id2");
        dto.setDocumentId("doc2");
        dto.setKey("project");
        dto.setValue("CMIS Adapter");

        assertEquals("id2", dto.getId());
        assertEquals("doc2", dto.getDocumentId());
        assertEquals("project", dto.getKey());
        assertEquals("CMIS Adapter", dto.getValue());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        MetadataDto dto = new MetadataDto("id3", "doc3", "foo", "bar");

        assertEquals("id3", dto.getId());
        assertEquals("doc3", dto.getDocumentId());
        assertEquals("foo", dto.getKey());
        assertEquals("bar", dto.getValue());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        MetadataDto dto = MetadataDto.builder()
                .id("metaX")
                .key("K")
                .value("V")
                .build();

        String str = dto.toString();
        assertTrue(str.contains("metaX"));
        assertTrue(str.contains("K"));
        assertTrue(str.contains("V"));
    }
}
