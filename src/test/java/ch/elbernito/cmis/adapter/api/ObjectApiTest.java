package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import ch.elbernito.cmis.adapter.dto.RelationshipDto;
import ch.elbernito.cmis.adapter.service.ObjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ObjectApi.
 * Tests all API operations for CMIS objects using a mocked ObjectService.
 */
class ObjectApiTest {

    private ObjectService objectService;
    private ObjectApi objectApi;

    @BeforeEach
    void setUp() {
        objectService = mock(ObjectService.class);
        objectApi = new ObjectApi(objectService);
    }

    /**
     * Tests that fetching an object by ID returns the correct object and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetObjectThenReturnsObject")
    void testWhenGetObjectThenReturnsObject() {
        // Arrange
        String objectId = "obj1";
        ObjectDto expected = ObjectDto.builder().id(objectId).build();
        when(objectService.getObject(objectId)).thenReturn(expected);

        // Act
        ObjectDto result = objectApi.getObject(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(objectId, result.getId());
        verify(objectService).getObject(objectId);
    }

    /**
     * Tests that fetching an object by path returns the correct object and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetObjectByPathThenReturnsObject")
    void testWhenGetObjectByPathThenReturnsObject() {
        // Arrange
        String path = "/folder/file.txt";
        ObjectDto expected = ObjectDto.builder().path(path).build();
        when(objectService.getObjectByPath(path)).thenReturn(expected);

        // Act
        ObjectDto result = objectApi.getObjectByPath(path);

        // Assert
        assertNotNull(result);
        assertEquals(path, result.getPath());
        verify(objectService).getObjectByPath(path);
    }

    /**
     * Tests that moving an object returns the moved object and delegates to the service.
     */
    @Test
    @DisplayName("testWhenMoveObjectThenReturnsMovedObject")
    void testWhenMoveObjectThenReturnsMovedObject() {
        // Arrange
        String objectId = "obj1";
        String folderId = "folder2";
        ObjectDto expected = ObjectDto.builder().id(objectId).build();
        when(objectService.moveObject(objectId, folderId)).thenReturn(expected);

        // Act
        ObjectDto result = objectApi.moveObject(objectId, folderId);

        // Assert
        assertNotNull(result);
        verify(objectService).moveObject(objectId, folderId);
    }

    /**
     * Tests that copying an object returns the copied object and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCopyObjectThenReturnsCopiedObject")
    void testWhenCopyObjectThenReturnsCopiedObject() {
        // Arrange
        String objectId = "obj1";
        String folderId = "folder2";
        ObjectDto expected = ObjectDto.builder().id(objectId).build();
        when(objectService.copyObject(objectId, folderId)).thenReturn(expected);

        // Act
        ObjectDto result = objectApi.copyObject(objectId, folderId);

        // Assert
        assertNotNull(result);
        verify(objectService).copyObject(objectId, folderId);
    }

    /**
     * Tests that getting allowable actions returns the correct DTO and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetAllowableActionsThenReturnsAllowableActionsDto")
    void testWhenGetAllowableActionsThenReturnsAllowableActionsDto() {
        // Arrange
        String objectId = "obj1";
        AllowableActionsDto expected = AllowableActionsDto.builder().build();
        when(objectService.getAllowableActions(objectId)).thenReturn(expected);

        // Act
        AllowableActionsDto result = objectApi.getAllowableActions(objectId);

        // Assert
        assertNotNull(result);
        verify(objectService).getAllowableActions(objectId);
    }

    /**
     * Tests that getting object relationships returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetObjectRelationshipsThenReturnsRelationshipList")
    void testWhenGetObjectRelationshipsThenReturnsRelationshipList() {
        // Arrange
        String objectId = "obj1";
        RelationshipDto r1 = RelationshipDto.builder().id("rel1").build();
        RelationshipDto r2 = RelationshipDto.builder().id("rel2").build();
        List<RelationshipDto> relationships = Arrays.asList(r1, r2);
        when(objectService.getObjectRelationships(objectId)).thenReturn(relationships);

        // Act
        List<RelationshipDto> result = objectApi.getObjectRelationships(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(objectService).getObjectRelationships(objectId);
    }
}
