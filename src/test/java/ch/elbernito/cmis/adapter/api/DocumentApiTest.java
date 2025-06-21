package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.VersionDto;
import ch.elbernito.cmis.adapter.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DocumentApi.
 * Tests all API operations for CMIS documents using a mocked DocumentService.
 */
class DocumentApiTest {

    private DocumentService documentService;
    private DocumentApi documentApi;

    @BeforeEach
    void setUp() {
        documentService = mock(DocumentService.class);
        documentApi = new DocumentApi(documentService);
    }

    /**
     * Tests that creating a document via the API returns the created document and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCreateDocumentThenReturnsCreatedDocument")
    void testWhenCreateDocumentThenReturnsCreatedDocument() {
        // Arrange
        DocumentDto input = DocumentDto.builder().id("doc1").name("My Doc").build();
        when(documentService.createDocument(input)).thenReturn(input);

        // Act
        DocumentDto result = documentApi.createDocument(input);

        // Assert
        assertNotNull(result);
        assertEquals("doc1", result.getId());
        verify(documentService).createDocument(input);
    }

    /**
     * Tests that fetching a document by ID returns the correct document and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetDocumentThenReturnsDocument")
    void testWhenGetDocumentThenReturnsDocument() {
        // Arrange
        String docId = "doc1";
        DocumentDto expected = DocumentDto.builder().id(docId).name("My Doc").build();
        when(documentService.getDocument(docId)).thenReturn(expected);

        // Act
        DocumentDto result = documentApi.getDocument(docId);

        // Assert
        assertNotNull(result);
        assertEquals(docId, result.getId());
        verify(documentService).getDocument(docId);
    }

    /**
     * Tests that updating a document returns the updated document and delegates to the service.
     */
    @Test
    @DisplayName("testWhenUpdateDocumentThenReturnsUpdatedDocument")
    void testWhenUpdateDocumentThenReturnsUpdatedDocument() {
        // Arrange
        String docId = "doc1";
        DocumentDto input = DocumentDto.builder().id(docId).name("Updated").build();
        when(documentService.updateDocument(docId, input)).thenReturn(input);

        // Act
        DocumentDto result = documentApi.updateDocument(docId, input);

        // Assert
        assertNotNull(result);
        assertEquals("Updated", result.getName());
        verify(documentService).updateDocument(docId, input);
    }

    /**
     * Tests that deleting a document delegates to the service.
     */
    @Test
    @DisplayName("testWhenDeleteDocumentThenDocumentServiceIsCalled")
    void testWhenDeleteDocumentThenDocumentServiceIsCalled() {
        // Arrange
        String docId = "doc1";

        // Act
        documentApi.deleteDocument(docId);

        // Assert
        verify(documentService).deleteDocument(docId);
    }

    /**
     * Tests that checking in a document returns the checked-in document and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCheckinDocumentThenReturnsCheckedInDocument")
    void testWhenCheckinDocumentThenReturnsCheckedInDocument() {
        // Arrange
        String docId = "doc1";
        DocumentDto expected = DocumentDto.builder().id(docId).checkedOut(false).build();
        when(documentService.checkinDocument(docId)).thenReturn(expected);

        // Act
        DocumentDto result = documentApi.checkinDocument(docId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isCheckedOut());
        verify(documentService).checkinDocument(docId);
    }

    /**
     * Tests that checking out a document returns the checked-out document and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCheckoutDocumentThenReturnsCheckedOutDocument")
    void testWhenCheckoutDocumentThenReturnsCheckedOutDocument() {
        // Arrange
        String docId = "doc1";
        DocumentDto expected = DocumentDto.builder().id(docId).checkedOut(true).build();
        when(documentService.checkoutDocument(docId)).thenReturn(expected);

        // Act
        DocumentDto result = documentApi.checkoutDocument(docId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isCheckedOut());
        verify(documentService).checkoutDocument(docId);
    }

    /**
     * Tests that getting document versions returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetDocumentVersionsThenReturnsVersionList")
    void testWhenGetDocumentVersionsThenReturnsVersionList() {
        // Arrange
        String docId = "doc1";
        VersionDto v1 = VersionDto.builder().label("1.0").build();
        VersionDto v2 = VersionDto.builder().label("2.0").build();
        List<VersionDto> versions = Arrays.asList(v1, v2);
        when(documentService.getDocumentVersions(docId)).thenReturn(versions);

        // Act
        List<VersionDto> result = documentApi.getDocumentVersions(docId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(documentService).getDocumentVersions(docId);
    }

    /**
     * Tests that getting document content returns the correct byte array and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetDocumentContentThenReturnsContent")
    void testWhenGetDocumentContentThenReturnsContent() {
        // Arrange
        String docId = "doc1";
        byte[] content = new byte[]{1, 2, 3};
        when(documentService.getDocumentContent(docId)).thenReturn(content);

        // Act
        byte[] result = documentApi.getDocumentContent(docId);

        // Assert
        assertNotNull(result);
        assertArrayEquals(content, result);
        verify(documentService).getDocumentContent(docId);
    }

    /**
     * Tests that updating document content delegates to the service.
     */
    @Test
    @DisplayName("testWhenUpdateDocumentContentThenDocumentServiceIsCalled")
    void testWhenUpdateDocumentContentThenDocumentServiceIsCalled() {
        // Arrange
        String docId = "doc1";
        byte[] content = new byte[]{1, 2, 3};
        String mimeType = "application/pdf";

        // Act
        documentApi.updateDocumentContent(docId, content, mimeType);

        // Assert
        verify(documentService).updateDocumentContent(docId, content, mimeType);
    }
}
