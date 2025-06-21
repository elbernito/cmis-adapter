package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AclDto.
 * Tests all constructors, builder, setters/getters and toString.
 */
class AclDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        // Arrange & Act
        AclDto dto = AclDto.builder()
                .id("acl123")
                .objectId("doc123")
                .principal("jane.doe")
                .permission("read")
                .direct(true)
                .build();

        // Assert
        assertEquals("acl123", dto.getId());
        assertEquals("doc123", dto.getObjectId());
        assertEquals("jane.doe", dto.getPrincipal());
        assertEquals("read", dto.getPermission());
        assertTrue(dto.isDirect());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        // Arrange
        AclDto dto = new AclDto();

        // Act
        dto.setId("id2");
        dto.setObjectId("obj2");
        dto.setPrincipal("max.mustermann");
        dto.setPermission("write");
        dto.setDirect(false);

        // Assert
        assertEquals("id2", dto.getId());
        assertEquals("obj2", dto.getObjectId());
        assertEquals("max.mustermann", dto.getPrincipal());
        assertEquals("write", dto.getPermission());
        assertFalse(dto.isDirect());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        // Arrange & Act
        AclDto dto = new AclDto("id3", "obj3", "group1", "all", true);

        // Assert
        assertEquals("id3", dto.getId());
        assertEquals("obj3", dto.getObjectId());
        assertEquals("group1", dto.getPrincipal());
        assertEquals("all", dto.getPermission());
        assertTrue(dto.isDirect());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        // Arrange
        AclDto dto = AclDto.builder()
                .id("ACLX")
                .principal("principalX")
                .build();

        // Act
        String str = dto.toString();

        // Assert
        assertTrue(str.contains("ACLX"));
        assertTrue(str.contains("principalX"));
    }
}
