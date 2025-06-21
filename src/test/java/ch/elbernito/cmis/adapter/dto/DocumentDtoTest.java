package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DocumentDto.
 * Verifies builder, getters, setters, all-args constructor, toString and byte[] content handling.
 */
class DocumentDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        byte[] content = "Testdata".getBytes(StandardCharsets.UTF_8);
        DocumentDto dto = DocumentDto.builder()
                .id("doc1")
                .name("MyDoc.pdf")
                .mimeType("application/pdf")
                .content(content)
                .folderId("folderA")
                .versionLabel("2.1")
                .checkedOut(true)
                .build();

        assertEquals("doc1", dto.getId());
        assertEquals("MyDoc.pdf", dto.getName());
        assertEquals("application/pdf", dto.getMimeType());
        assertArrayEquals(content, dto.getContent());
        assertEquals("folderA", dto.getFolderId());
        assertEquals("2.1", dto.getVersionLabel());
        assertTrue(dto.isCheckedOut());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        DocumentDto dto = new DocumentDto();
        byte[] content = new byte[]{1, 2, 3};

        dto.setId("doc2");
        dto.setName("doc2.pdf");
        dto.setMimeType("image/png");
        dto.setContent(content);
        dto.setFolderId("f2");
        dto.setVersionLabel("1.0");
        dto.setCheckedOut(false);

        assertEquals("doc2", dto.getId());
        assertEquals("doc2.pdf", dto.getName());
        assertEquals("image/png", dto.getMimeType());
        assertArrayEquals(content, dto.getContent());
        assertEquals("f2", dto.getFolderId());
        assertEquals("1.0", dto.getVersionLabel());
        assertFalse(dto.isCheckedOut());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        byte[] content = "XYZ".getBytes(StandardCharsets.UTF_8);
        DocumentDto dto = new DocumentDto("idX", "file.txt", "text/plain", content, "folderX", "0.9", true);

        assertEquals("idX", dto.getId());
        assertEquals("file.txt", dto.getName());
        assertEquals("text/plain", dto.getMimeType());
        assertArrayEquals(content, dto.getContent());
        assertEquals("folderX", dto.getFolderId());
        assertEquals("0.9", dto.getVersionLabel());
        assertTrue(dto.isCheckedOut());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        DocumentDto dto = DocumentDto.builder()
                .id("docX")
                .name("X.pdf")
                .mimeType("application/xml")
                .versionLabel("3.0")
                .checkedOut(true)
                .build();
        String str = dto.toString();
        assertTrue(str.contains("docX"));
        assertTrue(str.contains("X.pdf"));
        assertTrue(str.contains("3.0"));
        assertTrue(str.contains("true"));
    }

    @Test
    @DisplayName("testWhenContentSetThenIsBase64Compatible")
    void testWhenContentSetThenIsBase64Compatible() {
        // Arrange
        String text = "Hello world!";
        byte[] content = text.getBytes(StandardCharsets.UTF_8);

        DocumentDto dto = DocumentDto.builder().content(content).build();

        // Act: encode content as Base64 (API delivers base64 if configured)
        String base64 = Base64.getEncoder().encodeToString(dto.getContent());

        // Assert
        assertEquals(Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8)), base64);
    }
}
