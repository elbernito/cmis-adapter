package ch.elbernito.cmis.adapter.service.impl.prod;

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
 * Unit tests for RelationshipServiceProdImpl.
 * Ensures correct REST calls are made for relationship operations in the PROD stack.
 */
class RelationshipServiceProdImplTest {

    private RestTemplate restTemplate;
    private RelationshipServiceProdImpl relationshipService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        relationshipService = new RelationshipServiceProdImpl(restTemplate);
        relationshipService.environmentTag = "PROD";
    }

    @Test
    @DisplayName("testWhenCreateRelationshipThenPostsAndReturnsRelationship")
    void testWhenCreateRelationshipThenPostsAndReturnsRelationship() {
        // Arrange
        RelationshipDto input = RelationshipDto.builder().id("rel1").build();
        when(restTemplate.postForObject("/relationships", input, RelationshipDto.class)).thenReturn(input);

        // Act
        RelationshipDto result = relationshipService.createRelationship(input);

        // Assert
        assertNotNull(result);
        assertEquals("rel1", result.getId());
        verify(restTemplate).postForObject("/relationships", input, RelationshipDto.class);
    }

    @Test
    @DisplayName("testWhenGetRelationshipThenReturnsRelationship")
    void testWhenGetRelationshipThenReturnsRelationship() {
        // Arrange
        String relationshipId = "rel1";
        RelationshipDto expected = RelationshipDto.builder().id(relationshipId).build();
        when(restTemplate.getForObject("/relationships/" + relationshipId, RelationshipDto.class)).thenReturn(expected);

        // Act
        RelationshipDto result = relationshipService.getRelationship(relationshipId);

        // Assert
        assertNotNull(result);
        assertEquals(relationshipId, result.getId());
        verify(restTemplate).getForObject("/relationships/" + relationshipId, RelationshipDto.class);
    }

    @Test
    @DisplayName("testWhenDeleteRelationshipThenDeletesRelationship")
    void testWhenDeleteRelationshipThenDeletesRelationship() {
        // Arrange
        String relationshipId = "rel1";

        // Act
        relationshipService.deleteRelationship(relationshipId);

        // Assert
        verify(restTemplate).delete("/relationships/" + relationshipId);
    }

    @Test
    @DisplayName("testWhenGetRelationshipsByObjectIdThenReturnsRelationshipList")
    void testWhenGetRelationshipsByObjectIdThenReturnsRelationshipList() {
        // Arrange
        String objectId = "obj1";
        RelationshipDto[] arr = new RelationshipDto[]{RelationshipDto.builder().id("rel1").build()};
        ResponseEntity<RelationshipDto[]> response = ResponseEntity.ok(arr);
        when(restTemplate.getForEntity("/objects/" + objectId + "/relationships/all", RelationshipDto[].class)).thenReturn(response);

        // Act
        List<RelationshipDto> result = relationshipService.getRelationshipsByObjectId(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/objects/" + objectId + "/relationships/all", RelationshipDto[].class);
    }
}
