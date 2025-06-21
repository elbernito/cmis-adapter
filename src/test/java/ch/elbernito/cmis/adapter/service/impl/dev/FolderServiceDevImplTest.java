package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.FolderDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FolderServiceDevImpl.
 * Verifies correct forwarding of requests and response mapping using a mocked RestTemplate.
 */
class FolderServiceDevImplTest {

    private RestTemplate restTemplate;
    private FolderServiceDevImpl folderService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        folderService = new FolderServiceDevImpl(restTemplate);
        folderService.environmentTag = "DEV";
    }

    /**
     * Tests that createFolder sends a POST request and returns the created folder.
     */
    @Test
    @DisplayName("testWhenCreateFolderThenPostsAndReturnsFolder")
    void testWhenCreateFolderThenPostsAndReturnsFolder() {
        // Arrange
        FolderDto input = FolderDto.builder().id("f1").name("Test Folder").build();
        when(restTemplate.postForObject("/folders", input, FolderDto.class)).thenReturn(input);

        // Act
        FolderDto result = folderService.createFolder(input);

        // Assert
        assertNotNull(result);
        assertEquals("f1", result.getId());
        verify(restTemplate).postForObject("/folders", input, FolderDto.class);
    }

    /**
     * Tests that getFolder sends a GET request and returns the folder.
     */
    @Test
    @DisplayName("testWhenGetFolderThenReturnsFolder")
    void testWhenGetFolderThenReturnsFolder() {
        // Arrange
        String folderId = "f1";
        FolderDto expected = FolderDto.builder().id(folderId).build();
        when(restTemplate.getForObject("/folders/" + folderId, FolderDto.class)).thenReturn(expected);

        // Act
        FolderDto result = folderService.getFolder(folderId);

        // Assert
        assertNotNull(result);
        assertEquals(folderId, result.getId());
        verify(restTemplate).getForObject("/folders/" + folderId, FolderDto.class);
    }

    /**
     * Tests that updateFolder sends a PUT request and returns the updated folder.
     */
    @Test
    @DisplayName("testWhenUpdateFolderThenPutsAndReturnsFolder")
    void testWhenUpdateFolderThenPutsAndReturnsFolder() {
        // Arrange
        String folderId = "f1";
        FolderDto updated = FolderDto.builder().id(folderId).name("Updated").build();
        when(restTemplate.getForObject("/folders/" + folderId, FolderDto.class)).thenReturn(updated);

        // Act
        FolderDto result = folderService.updateFolder(folderId, updated);

        // Assert
        assertNotNull(result);
        assertEquals("Updated", result.getName());
        verify(restTemplate).put("/folders/" + folderId, updated);
        verify(restTemplate).getForObject("/folders/" + folderId, FolderDto.class);
    }

    /**
     * Tests that deleteFolder sends a DELETE request.
     */
    @Test
    @DisplayName("testWhenDeleteFolderThenDeletesFolder")
    void testWhenDeleteFolderThenDeletesFolder() {
        // Arrange
        String folderId = "f1";

        // Act
        folderService.deleteFolder(folderId);

        // Assert
        verify(restTemplate).delete("/folders/" + folderId);
    }

    /**
     * Tests that getFolderChildren sends a GET request and returns the child objects.
     */
    @Test
    @DisplayName("testWhenGetFolderChildrenThenReturnsObjectList")
    void testWhenGetFolderChildrenThenReturnsObjectList() {
        // Arrange
        String folderId = "f1";
        ObjectDto[] children = new ObjectDto[]{ObjectDto.builder().id("o1").build(), ObjectDto.builder().id("o2").build()};
        ResponseEntity<ObjectDto[]> response = ResponseEntity.ok(children);
        when(restTemplate.getForEntity("/folders/" + folderId + "/children", ObjectDto[].class)).thenReturn(response);

        // Act
        List<ObjectDto> result = folderService.getFolderChildren(folderId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restTemplate).getForEntity("/folders/" + folderId + "/children", ObjectDto[].class);
    }

    /**
     * Tests that getFolderDescendants sends a GET request and returns the descendant objects.
     */
    @Test
    @DisplayName("testWhenGetFolderDescendantsThenReturnsObjectList")
    void testWhenGetFolderDescendantsThenReturnsObjectList() {
        // Arrange
        String folderId = "f1";
        ObjectDto[] descendants = new ObjectDto[]{ObjectDto.builder().id("o1").build()};
        ResponseEntity<ObjectDto[]> response = ResponseEntity.ok(descendants);
        when(restTemplate.getForEntity("/folders/" + folderId + "/descendants", ObjectDto[].class)).thenReturn(response);

        // Act
        List<ObjectDto> result = folderService.getFolderDescendants(folderId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/folders/" + folderId + "/descendants", ObjectDto[].class);
    }

    /**
     * Tests that getFolderParent sends a GET request and returns the parent folder.
     */
    @Test
    @DisplayName("testWhenGetFolderParentThenReturnsParentFolder")
    void testWhenGetFolderParentThenReturnsParentFolder() {
        // Arrange
        String folderId = "f1";
        FolderDto parent = FolderDto.builder().id("parent1").build();
        when(restTemplate.getForObject("/folders/" + folderId + "/parent", FolderDto.class)).thenReturn(parent);

        // Act
        FolderDto result = folderService.getFolderParent(folderId);

        // Assert
        assertNotNull(result);
        assertEquals("parent1", result.getId());
        verify(restTemplate).getForObject("/folders/" + folderId + "/parent", FolderDto.class);
    }

    /**
     * Tests that deleteFolderTree sends a DELETE request.
     */
    @Test
    @DisplayName("testWhenDeleteFolderTreeThenDeletesTree")
    void testWhenDeleteFolderTreeThenDeletesTree() {
        // Arrange
        String folderId = "f1";

        // Act
        folderService.deleteFolderTree(folderId);

        // Assert
        verify(restTemplate).delete("/folders/" + folderId + "/tree");
    }

    /**
     * Tests that getCheckedOutDocs sends a GET request and returns the checked out documents.
     */
    @Test
    @DisplayName("testWhenGetCheckedOutDocsThenReturnsDocumentList")
    void testWhenGetCheckedOutDocsThenReturnsDocumentList() {
        // Arrange
        String folderId = "f1";
        DocumentDto[] docs = new DocumentDto[]{DocumentDto.builder().id("d1").build(), DocumentDto.builder().id("d2").build()};
        ResponseEntity<DocumentDto[]> response = ResponseEntity.ok(docs);
        when(restTemplate.getForEntity("/folders/" + folderId + "/checkedout", DocumentDto[].class)).thenReturn(response);

        // Act
        List<DocumentDto> result = folderService.getCheckedOutDocs(folderId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restTemplate).getForEntity("/folders/" + folderId + "/checkedout", DocumentDto[].class);
    }
}
