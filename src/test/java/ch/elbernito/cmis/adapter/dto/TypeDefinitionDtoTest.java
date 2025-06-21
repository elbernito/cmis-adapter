package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TypeDefinitionDto.
 */
class TypeDefinitionDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        TypeDefinitionDto dto = TypeDefinitionDto.builder()
                .id("type1")
                .displayName("Type")
                .description("Desc")
                .parentTypeId("parent")
                .baseType(true)
                .build();
        assertEquals("type1", dto.getId());
        assertEquals("Type", dto.getDisplayName());
        assertEquals("Desc", dto.getDescription());
        assertEquals("parent", dto.getParentTypeId());
        assertTrue(dto.isBaseType());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        TypeDefinitionDto dto = new TypeDefinitionDto();
        dto.setId("type2");
        dto.setDisplayName("Type2");
        dto.setDescription("Desc2");
        dto.setParentTypeId("parent2");
        dto.setBaseType(false);

        assertEquals("type2", dto.getId());
        assertEquals("Type2", dto.getDisplayName());
        assertEquals("Desc2", dto.getDescription());
        assertEquals("parent2", dto.getParentTypeId());
        assertFalse(dto.isBaseType());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        TypeDefinitionDto dto = new TypeDefinitionDto("id", "n", "d", "pid", true);
        assertEquals("id", dto.getId());
        assertEquals("n", dto.getDisplayName());
        assertEquals("d", dto.getDescription());
        assertEquals("pid", dto.getParentTypeId());
        assertTrue(dto.isBaseType());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        TypeDefinitionDto dto = TypeDefinitionDto.builder().id("TYP").build();
        assertTrue(dto.toString().contains("TYP"));
    }
}
