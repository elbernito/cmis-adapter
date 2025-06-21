package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import ch.elbernito.cmis.adapter.dto.RelationshipDto;
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
 * Unit tests for ObjectServiceProdImpl.
 * Ensures correct REST calls are made for object operations in the PROD stack.
 */
class ObjectServiceProdImplTest {

    private RestTemplate restTemplate;
    private ObjectServiceProdImpl objectService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        objectService = new ObjectServiceProdImpl(restTemplate);
        objectService.environmentTag = "PROD";
    }

    @Test
    @DisplayName("testWhenGetObjectThenReturnsObject")
    void testWhenGetObjectThenReturnsObject() {
        // Arrange
        String objectId = "obj1";
        ObjectDto expected = ObjectDto.builder().id(objectId).build();
        when(restTemplate.getForObject("/objects/" + objectId, ObjectDto.class)).thenReturn(expected);

        // Act
        ObjectDto result = objectService.getObject(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(objectId, result.getId());
        verify(restTemplate).getForObject("/objects/" + objectId, ObjectDto.class);
    }

    @Test
    @DisplayName("testWhenGetObjectByPathThenReturnsObject")
    void testWhenGetObjectByPathThenReturnsObject() {
        // Arrange
        String path = "/some/path";
        ObjectDto expected = ObjectDto.builder().path(path).build();
        when(restTemplate.getForObject("/objects/by-path?path=" + path, ObjectDto.class)).thenReturn(expected);

        // Act
        ObjectDto result = objectService.getObjectByPath(path);

        // Assert
        assertNotNull(result);
        assertEquals(path, result.getPath());
        verify(restTemplate).getForObject("/objects/by-path?path=" + path, ObjectDto.class);
    }

    @Test
    @DisplayName("testWhenMoveObjectThenReturnsMovedObject")
    void testWhenMoveObjectThenReturnsMovedObject() {
        // Arrange
        String objectId = "obj1";
        String targetFolderId = "folder2";
        ObjectDto expected = ObjectDto.builder().id(objectId).build();
        when(restTemplate.postForObject("/objects/" + objectId + "/move?targetFolderId=" + targetFolderId, null, ObjectDto.class)).thenReturn(expected);

        // Act
        ObjectDto result = objectService.moveObject(objectId, targetFolderId);

        // Assert
        assertNotNull(result);
        assertEquals(objectId, result.getId());
        verify(restTemplate).postForObject("/objects/" + objectId + "/move?targetFolderId=" + targetFolderId, null, ObjectDto.class);
    }

    @Test
    @DisplayName("testWhenCopyObjectThenReturnsCopiedObject")
    void testWhenCopyObjectThenReturnsCopiedObject() {
        // Arrange
        String objectId = "obj1";
        String targetFolderId = "folder2";
        ObjectDto expected = ObjectDto.builder().id(objectId).build();
        when(restTemplate.postForObject("/objects/" + objectId + "/copy?targetFolderId=" + targetFolderId, null, ObjectDto.class)).thenReturn(expected);

        // Act
        ObjectDto result = objectService.copyObject(objectId, targetFolderId);

        // Assert
        assertNotNull(result);
        assertEquals(objectId, result.getId());
        verify(restTemplate).postForObject("/objects/" + objectId + "/copy?targetFolderId=" + targetFolderId, null, ObjectDto.class);
    }

    @Test
    @DisplayName("testWhenGetAllowableActionsThenReturnsAllowableActionsDto")
    void testWhenGetAllowableActionsThenReturnsAllowableActionsDto() {
        // Arrange
        String objectId = "obj1";
        AllowableActionsDto expected = AllowableActionsDto.builder().build();
        when(restTemplate.getForObject("/objects/" + objectId + "/allowable-actions", AllowableActionsDto.class)).thenReturn(expected);

        // Act
        AllowableActionsDto result = objectService.getAllowableActions(objectId);

        // Assert
        assertNotNull(result);
        verify(restTemplate).getForObject("/objects/" + objectId + "/allowable-actions", AllowableActionsDto.class);
    }

    @Test
    @DisplayName("testWhenGetObjectRelationshipsThenReturnsRelationshipList")
    void testWhenGetObjectRelationshipsThenReturnsRelationshipList() {
        // Arrange
        String objectId = "obj1";
        RelationshipDto[] relationships = new RelationshipDto[]{
                RelationshipDto.builder().id("rel1").build(),
                RelationshipDto.builder().id("rel2").build()
        };
        ResponseEntity<RelationshipDto[]> response = ResponseEntity.ok(relationships);
        when(restTemplate.getForEntity("/objects/" + objectId + "/relationships", RelationshipDto[].class)).thenReturn(response);

        // Act
        List<RelationshipDto> result = objectService.getObjectRelationships(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restTemplate).getForEntity("/objects/" + objectId + "/relationships", RelationshipDto[].class);
    }
}
