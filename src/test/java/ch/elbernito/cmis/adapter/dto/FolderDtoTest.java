package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for FolderDto.
 * Verifies builder, constructors, getters, setters, List handling and toString.
 */
class FolderDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        List<String> children = Arrays.asList("doc1", "folder2");
        FolderDto dto = FolderDto.builder()
                .id("f1")
                .name("MyFolder")
                .parentId("parent")
                .path("/archive")
                .childrenIds(children)
                .build();

        assertEquals("f1", dto.getId());
        assertEquals("MyFolder", dto.getName());
        assertEquals("parent", dto.getParentId());
        assertEquals("/archive", dto.getPath());
        assertEquals(children, dto.getChildrenIds());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        FolderDto dto = new FolderDto();
        List<String> children = Collections.singletonList("child1");

        dto.setId("f2");
        dto.setName("Folder2");
        dto.setParentId("p2");
        dto.setPath("/root");
        dto.setChildrenIds(children);

        assertEquals("f2", dto.getId());
        assertEquals("Folder2", dto.getName());
        assertEquals("p2", dto.getParentId());
        assertEquals("/root", dto.getPath());
        assertEquals(children, dto.getChildrenIds());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        List<String> children = Arrays.asList("a", "b");
        FolderDto dto = new FolderDto("idX", "FolderX", "parentX", "/x", children);

        assertEquals("idX", dto.getId());
        assertEquals("FolderX", dto.getName());
        assertEquals("parentX", dto.getParentId());
        assertEquals("/x", dto.getPath());
        assertEquals(children, dto.getChildrenIds());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        FolderDto dto = FolderDto.builder()
                .id("FOL")
                .name("A")
                .path("/docs")
                .build();

        String str = dto.toString();
        assertTrue(str.contains("FOL"));
        assertTrue(str.contains("A"));
        assertTrue(str.contains("/docs"));
    }
}
