package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for PolicyDto.
 * Verifies builder, constructors, getters, setters and toString.
 */
class PolicyDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        PolicyDto dto = PolicyDto.builder()
                .id("pol1")
                .name("Confidentiality")
                .description("Docs covered by this policy must not be shared externally.")
                .type("confidentiality")
                .build();

        assertEquals("pol1", dto.getId());
        assertEquals("Confidentiality", dto.getName());
        assertEquals("Docs covered by this policy must not be shared externally.", dto.getDescription());
        assertEquals("confidentiality", dto.getType());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        PolicyDto dto = new PolicyDto();
        dto.setId("id2");
        dto.setName("Policy2");
        dto.setDescription("D2");
        dto.setType("type2");

        assertEquals("id2", dto.getId());
        assertEquals("Policy2", dto.getName());
        assertEquals("D2", dto.getDescription());
        assertEquals("type2", dto.getType());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        PolicyDto dto = new PolicyDto("id3", "N3", "Desc3", "Type3");

        assertEquals("id3", dto.getId());
        assertEquals("N3", dto.getName());
        assertEquals("Desc3", dto.getDescription());
        assertEquals("Type3", dto.getType());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        PolicyDto dto = PolicyDto.builder()
                .id("POLX")
                .name("A")
                .description("B")
                .type("C")
                .build();

        String str = dto.toString();
        assertTrue(str.contains("POLX"));
        assertTrue(str.contains("A"));
        assertTrue(str.contains("B"));
        assertTrue(str.contains("C"));
    }
}
