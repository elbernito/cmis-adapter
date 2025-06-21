package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.FolderDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import ch.elbernito.cmis.adapter.service.FolderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FolderApi.
 * Tests all API operations for CMIS folders using a mocked FolderService.
 */
class FolderApiTest {

    private FolderService folderService;
    private FolderApi folderApi;

    @BeforeEach
    void setUp() {
        folderService = mock(FolderService.class);
        folderApi = new FolderApi(folderService);
    }

    /**
     * Tests that creating a folder via the API returns the created folder and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCreateFolderThenReturnsCreatedFolder")
    void testWhenCreateFolderThenReturnsCreatedFolder() {
        // Arrange
        FolderDto input = FolderDto.builder().id("f1").name("Test Folder").build();
        when(folderService.createFolder(input)).thenReturn(input);

        // Act
        FolderDto result = folderApi.createFolder(input);

        // Assert
        assertNotNull(result);
        assertEquals("f1", result.getId());
        verify(folderService).createFolder(input);
    }

    /**
     * Tests that fetching a folder by ID returns the correct folder and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetFolderThenReturnsFolder")
    void testWhenGetFolderThenReturnsFolder() {
        // Arrange
        String folderId = "f1";
        FolderDto expected = FolderDto.builder().id(folderId).build();
        when(folderService.getFolder(folderId)).thenReturn(expected);

        // Act
        FolderDto result = folderApi.getFolder(folderId);

        // Assert
        assertNotNull(result);
        assertEquals(folderId, result.getId());
        verify(folderService).getFolder(folderId);
    }

    /**
     * Tests that updating a folder returns the updated folder and delegates to the service.
     */
    @Test
    @DisplayName("testWhenUpdateFolderThenReturnsUpdatedFolder")
    void testWhenUpdateFolderThenReturnsUpdatedFolder() {
        // Arrange
        String folderId = "f1";
        FolderDto input = FolderDto.builder().id(folderId).name("Updated Folder").build();
        when(folderService.updateFolder(folderId, input)).thenReturn(input);

        // Act
        FolderDto result = folderApi.updateFolder(folderId, input);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Folder", result.getName());
        verify(folderService).updateFolder(folderId, input);
    }

    /**
     * Tests that deleting a folder delegates to the service.
     */
    @Test
    @DisplayName("testWhenDeleteFolderThenFolderServiceIsCalled")
    void testWhenDeleteFolderThenFolderServiceIsCalled() {
        // Arrange
        String folderId = "f1";

        // Act
        folderApi.deleteFolder(folderId);

        // Assert
        verify(folderService).deleteFolder(folderId);
    }

    /**
     * Tests that getting folder children returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetFolderChildrenThenReturnsObjectList")
    void testWhenGetFolderChildrenThenReturnsObjectList() {
        // Arrange
        String folderId = "f1";
        ObjectDto o1 = ObjectDto.builder().id("obj1").build();
        ObjectDto o2 = ObjectDto.builder().id("obj2").build();
        List<ObjectDto> children = Arrays.asList(o1, o2);
        when(folderService.getFolderChildren(folderId)).thenReturn(children);

        // Act
        List<ObjectDto> result = folderApi.getFolderChildren(folderId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(folderService).getFolderChildren(folderId);
    }

    /**
     * Tests that getting folder descendants returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetFolderDescendantsThenReturnsObjectList")
    void testWhenGetFolderDescendantsThenReturnsObjectList() {
        // Arrange
        String folderId = "f1";
        ObjectDto o1 = ObjectDto.builder().id("obj1").build();
        ObjectDto o2 = ObjectDto.builder().id("obj2").build();
        List<ObjectDto> descendants = Arrays.asList(o1, o2);
        when(folderService.getFolderDescendants(folderId)).thenReturn(descendants);

        // Act
        List<ObjectDto> result = folderApi.getFolderDescendants(folderId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(folderService).getFolderDescendants(folderId);
    }

    /**
     * Tests that getting the parent folder returns the correct folder and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetFolderParentThenReturnsParentFolder")
    void testWhenGetFolderParentThenReturnsParentFolder() {
        // Arrange
        String folderId = "f1";
        FolderDto expected = FolderDto.builder().id("parent1").build();
        when(folderService.getFolderParent(folderId)).thenReturn(expected);

        // Act
        FolderDto result = folderApi.getFolderParent(folderId);

        // Assert
        assertNotNull(result);
        assertEquals("parent1", result.getId());
        verify(folderService).getFolderParent(folderId);
    }

    /**
     * Tests that deleting a folder tree delegates to the service.
     */
    @Test
    @DisplayName("testWhenDeleteFolderTreeThenFolderServiceIsCalled")
    void testWhenDeleteFolderTreeThenFolderServiceIsCalled() {
        // Arrange
        String folderId = "f1";

        // Act
        folderApi.deleteFolderTree(folderId);

        // Assert
        verify(folderService).deleteFolderTree(folderId);
    }

    /**
     * Tests that getting checked out documents returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetCheckedOutDocsThenReturnsDocumentList")
    void testWhenGetCheckedOutDocsThenReturnsDocumentList() {
        // Arrange
        String folderId = "f1";
        DocumentDto d1 = DocumentDto.builder().id("doc1").build();
        DocumentDto d2 = DocumentDto.builder().id("doc2").build();
        List<DocumentDto> docs = Arrays.asList(d1, d2);
        when(folderService.getCheckedOutDocs(folderId)).thenReturn(docs);

        // Act
        List<DocumentDto> result = folderApi.getCheckedOutDocs(folderId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(folderService).getCheckedOutDocs(folderId);
    }
}
