package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.VersionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DocumentServiceProdImpl.
 * Ensures that PROD implementation sends correct REST requests using the configured RestTemplate.
 */
class DocumentServiceProdImplTest {

    private RestTemplate restTemplate;
    private DocumentServiceProdImpl documentService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        documentService = new DocumentServiceProdImpl(restTemplate);
        // Simulate @Value injection for environmentTag
        documentService.environmentTag = "PROD";
    }

    /**
     * Tests that createDocument sends a POST request and returns the created document.
     */
    @Test
    @DisplayName("testWhenCreateDocumentThenPostsAndReturnsDocument")
    void testWhenCreateDocumentThenPostsAndReturnsDocument() {
        // Arrange
        DocumentDto input = DocumentDto.builder().id("doc1").name("Doc").build();
        when(restTemplate.postForObject("/documents", input, DocumentDto.class)).thenReturn(input);

        // Act
        DocumentDto result = documentService.createDocument(input);

        // Assert
        assertNotNull(result);
        assertEquals("doc1", result.getId());
        verify(restTemplate).postForObject("/documents", input, DocumentDto.class);
    }

    /**
     * Tests that getDocument sends a GET request and returns the document.
     */
    @Test
    @DisplayName("testWhenGetDocumentThenReturnsDocument")
    void testWhenGetDocumentThenReturnsDocument() {
        // Arrange
        String docId = "doc1";
        DocumentDto expected = DocumentDto.builder().id(docId).build();
        when(restTemplate.getForObject("/documents/" + docId, DocumentDto.class)).thenReturn(expected);

        // Act
        DocumentDto result = documentService.getDocument(docId);

        // Assert
        assertNotNull(result);
        assertEquals(docId, result.getId());
        verify(restTemplate).getForObject("/documents/" + docId, DocumentDto.class);
    }

    /**
     * Tests that updateDocument sends a PUT request and returns the updated document.
     */
    @Test
    @DisplayName("testWhenUpdateDocumentThenPutsAndReturnsDocument")
    void testWhenUpdateDocumentThenPutsAndReturnsDocument() {
        // Arrange
        String docId = "doc1";
        DocumentDto updated = DocumentDto.builder().id(docId).name("Updated").build();
        when(restTemplate.getForObject("/documents/" + docId, DocumentDto.class)).thenReturn(updated);

        // Act
        DocumentDto result = documentService.updateDocument(docId, updated);

        // Assert
        assertNotNull(result);
        assertEquals("Updated", result.getName());
        verify(restTemplate).put("/documents/" + docId, updated);
        verify(restTemplate).getForObject("/documents/" + docId, DocumentDto.class);
    }

    /**
     * Tests that deleteDocument sends a DELETE request.
     */
    @Test
    @DisplayName("testWhenDeleteDocumentThenDeletesDocument")
    void testWhenDeleteDocumentThenDeletesDocument() {
        // Arrange
        String docId = "doc1";

        // Act
        documentService.deleteDocument(docId);

        // Assert
        verify(restTemplate).delete("/documents/" + docId);
    }

    /**
     * Tests that checkinDocument sends a POST request and returns the checked-in document.
     */
    @Test
    @DisplayName("testWhenCheckinDocumentThenReturnsCheckedInDocument")
    void testWhenCheckinDocumentThenReturnsCheckedInDocument() {
        // Arrange
        String docId = "doc1";
        DocumentDto checkedIn = DocumentDto.builder().id(docId).checkedOut(false).build();
        when(restTemplate.postForObject("/documents/" + docId + "/checkin", null, DocumentDto.class)).thenReturn(checkedIn);

        // Act
        DocumentDto result = documentService.checkinDocument(docId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isCheckedOut());
        verify(restTemplate).postForObject("/documents/" + docId + "/checkin", null, DocumentDto.class);
    }

    /**
     * Tests that checkoutDocument sends a POST request and returns the checked-out document.
     */
    @Test
    @DisplayName("testWhenCheckoutDocumentThenReturnsCheckedOutDocument")
    void testWhenCheckoutDocumentThenReturnsCheckedOutDocument() {
        // Arrange
        String docId = "doc1";
        DocumentDto checkedOut = DocumentDto.builder().id(docId).checkedOut(true).build();
        when(restTemplate.postForObject("/documents/" + docId + "/checkout", null, DocumentDto.class)).thenReturn(checkedOut);

        // Act
        DocumentDto result = documentService.checkoutDocument(docId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isCheckedOut());
        verify(restTemplate).postForObject("/documents/" + docId + "/checkout", null, DocumentDto.class);
    }

    /**
     * Tests that getDocumentVersions sends a GET request and returns the version list.
     */
    @Test
    @DisplayName("testWhenGetDocumentVersionsThenReturnsVersionList")
    void testWhenGetDocumentVersionsThenReturnsVersionList() {
        // Arrange
        String docId = "doc1";
        VersionDto v1 = VersionDto.builder().label("1.0").build();
        VersionDto v2 = VersionDto.builder().label("2.0").build();
        VersionDto[] versions = new VersionDto[]{v1, v2};
        ResponseEntity<VersionDto[]> response = new ResponseEntity<>(versions, HttpStatus.OK);
        when(restTemplate.getForEntity("/documents/" + docId + "/versions", VersionDto[].class)).thenReturn(response);

        // Act
        List<VersionDto> result = documentService.getDocumentVersions(docId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restTemplate).getForEntity("/documents/" + docId + "/versions", VersionDto[].class);
    }

    /**
     * Tests that getDocumentContent sends a GET request and returns the byte array.
     */
    @Test
    @DisplayName("testWhenGetDocumentContentThenReturnsContent")
    void testWhenGetDocumentContentThenReturnsContent() {
        // Arrange
        String docId = "doc1";
        byte[] content = new byte[]{1, 2, 3};
        ResponseEntity<byte[]> response = new ResponseEntity<>(content, HttpStatus.OK);
        when(restTemplate.getForEntity("/documents/" + docId + "/content", byte[].class)).thenReturn(response);

        // Act
        byte[] result = documentService.getDocumentContent(docId);

        // Assert
        assertNotNull(result);
        assertArrayEquals(content, result);
        verify(restTemplate).getForEntity("/documents/" + docId + "/content", byte[].class);
    }

    /**
     * Tests that updateDocumentContent sends a PUT request with the correct content and MIME type.
     */
    @Test
    @DisplayName("testWhenUpdateDocumentContentThenPutsContentWithMimeType")
    void testWhenUpdateDocumentContentThenPutsContentWithMimeType() {
        // Arrange
        String docId = "doc1";
        byte[] content = new byte[]{1, 2, 3};
        String mimeType = "application/pdf";

        // Act
        documentService.updateDocumentContent(docId, content, mimeType);

        // Assert
        verify(restTemplate).put(eq("/documents/" + docId + "/content"), any(HttpEntity.class));
    }
}
