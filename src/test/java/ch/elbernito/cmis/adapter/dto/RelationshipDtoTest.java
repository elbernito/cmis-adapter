package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for RelationshipDto.
 * Verifies builder, constructors, getters, setters and toString.
 */
class RelationshipDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        RelationshipDto dto = RelationshipDto.builder()
                .id("rel1")
                .sourceId("src1")
                .targetId("tgt1")
                .type("related")
                .build();

        assertEquals("rel1", dto.getId());
        assertEquals("src1", dto.getSourceId());
        assertEquals("tgt1", dto.getTargetId());
        assertEquals("related", dto.getType());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        RelationshipDto dto = new RelationshipDto();
        dto.setId("id2");
        dto.setSourceId("src2");
        dto.setTargetId("tgt2");
        dto.setType("type2");

        assertEquals("id2", dto.getId());
        assertEquals("src2", dto.getSourceId());
        assertEquals("tgt2", dto.getTargetId());
        assertEquals("type2", dto.getType());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        RelationshipDto dto = new RelationshipDto("id3", "src3", "tgt3", "type3");

        assertEquals("id3", dto.getId());
        assertEquals("src3", dto.getSourceId());
        assertEquals("tgt3", dto.getTargetId());
        assertEquals("type3", dto.getType());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        RelationshipDto dto = RelationshipDto.builder()
                .id("RELX")
                .sourceId("S")
                .targetId("T")
                .type("TT")
                .build();

        String str = dto.toString();
        assertTrue(str.contains("RELX"));
        assertTrue(str.contains("S"));
        assertTrue(str.contains("T"));
        assertTrue(str.contains("TT"));
    }
}
