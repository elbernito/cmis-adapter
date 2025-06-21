package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.RelationshipDto;
import ch.elbernito.cmis.adapter.service.RelationshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for RelationshipApi.
 * Tests all API operations for CMIS relationships using a mocked RelationshipService.
 */
class RelationshipApiTest {

    private RelationshipService relationshipService;
    private RelationshipApi relationshipApi;

    @BeforeEach
    void setUp() {
        relationshipService = mock(RelationshipService.class);
        relationshipApi = new RelationshipApi(relationshipService);
    }

    /**
     * Tests that creating a relationship returns the created relationship and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCreateRelationshipThenReturnsRelationship")
    void testWhenCreateRelationshipThenReturnsRelationship() {
        // Arrange
        RelationshipDto input = RelationshipDto.builder().id("rel1").sourceId("obj1").targetId("obj2").build();
        when(relationshipService.createRelationship(input)).thenReturn(input);

        // Act
        RelationshipDto result = relationshipApi.createRelationship(input);

        // Assert
        assertNotNull(result);
        assertEquals("rel1", result.getId());
        verify(relationshipService).createRelationship(input);
    }

    /**
     * Tests that fetching a relationship by ID returns the correct relationship and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetRelationshipThenReturnsRelationship")
    void testWhenGetRelationshipThenReturnsRelationship() {
        // Arrange
        String relationshipId = "rel1";
        RelationshipDto expected = RelationshipDto.builder().id(relationshipId).build();
        when(relationshipService.getRelationship(relationshipId)).thenReturn(expected);

        // Act
        RelationshipDto result = relationshipApi.getRelationship(relationshipId);

        // Assert
        assertNotNull(result);
        assertEquals(relationshipId, result.getId());
        verify(relationshipService).getRelationship(relationshipId);
    }

    /**
     * Tests that deleting a relationship delegates to the service.
     */
    @Test
    @DisplayName("testWhenDeleteRelationshipThenRelationshipServiceIsCalled")
    void testWhenDeleteRelationshipThenRelationshipServiceIsCalled() {
        // Arrange
        String relationshipId = "rel1";

        // Act
        relationshipApi.deleteRelationship(relationshipId);

        // Assert
        verify(relationshipService).deleteRelationship(relationshipId);
    }

    /**
     * Tests that getting relationships by object ID returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetRelationshipsByObjectIdThenReturnsRelationshipList")
    void testWhenGetRelationshipsByObjectIdThenReturnsRelationshipList() {
        // Arrange
        String objectId = "obj1";
        RelationshipDto r1 = RelationshipDto.builder().id("rel1").build();
        RelationshipDto r2 = RelationshipDto.builder().id("rel2").build();
        List<RelationshipDto> list = Arrays.asList(r1, r2);
        when(relationshipService.getRelationshipsByObjectId(objectId)).thenReturn(list);

        // Act
        List<RelationshipDto> result = relationshipApi.getRelationshipsByObjectId(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(relationshipService).getRelationshipsByObjectId(objectId);
    }
}
