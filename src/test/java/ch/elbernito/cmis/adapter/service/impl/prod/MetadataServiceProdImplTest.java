package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.MetadataDto;
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
 * Unit tests for MetadataServiceProdImpl.
 * Ensures correct REST calls are made for metadata operations in the PROD stack.
 */
class MetadataServiceProdImplTest {

    private RestTemplate restTemplate;
    private MetadataServiceProdImpl metadataService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        metadataService = new MetadataServiceProdImpl(restTemplate);
        metadataService.environmentTag = "PROD";
    }

    @Test
    @DisplayName("testWhenCreateMetadataThenPostsAndReturnsMetadata")
    void testWhenCreateMetadataThenPostsAndReturnsMetadata() {
        // Arrange
        MetadataDto input = MetadataDto.builder().id("m1").build();
        when(restTemplate.postForObject("/metadata", input, MetadataDto.class)).thenReturn(input);

        // Act
        MetadataDto result = metadataService.createMetadata(input);

        // Assert
        assertNotNull(result);
        assertEquals("m1", result.getId());
        verify(restTemplate).postForObject("/metadata", input, MetadataDto.class);
    }

    @Test
    @DisplayName("testWhenGetMetadataThenReturnsMetadata")
    void testWhenGetMetadataThenReturnsMetadata() {
        // Arrange
        String metadataId = "m1";
        MetadataDto expected = MetadataDto.builder().id(metadataId).build();
        when(restTemplate.getForObject("/metadata/" + metadataId, MetadataDto.class)).thenReturn(expected);

        // Act
        MetadataDto result = metadataService.getMetadata(metadataId);

        // Assert
        assertNotNull(result);
        assertEquals(metadataId, result.getId());
        verify(restTemplate).getForObject("/metadata/" + metadataId, MetadataDto.class);
    }

    @Test
    @DisplayName("testWhenUpdateMetadataThenPutsAndReturnsMetadata")
    void testWhenUpdateMetadataThenPutsAndReturnsMetadata() {
        // Arrange
        String metadataId = "m1";
        MetadataDto updated = MetadataDto.builder().id(metadataId).value("changed").build();
        when(restTemplate.getForObject("/metadata/" + metadataId, MetadataDto.class)).thenReturn(updated);

        // Act
        MetadataDto result = metadataService.updateMetadata(metadataId, updated);

        // Assert
        assertNotNull(result);
        assertEquals("changed", result.getValue());
        verify(restTemplate).put("/metadata/" + metadataId, updated);
        verify(restTemplate).getForObject("/metadata/" + metadataId, MetadataDto.class);
    }

    @Test
    @DisplayName("testWhenDeleteMetadataThenDeletesMetadata")
    void testWhenDeleteMetadataThenDeletesMetadata() {
        // Arrange
        String metadataId = "m1";

        // Act
        metadataService.deleteMetadata(metadataId);

        // Assert
        verify(restTemplate).delete("/metadata/" + metadataId);
    }

    @Test
    @DisplayName("testWhenGetMetadataByDocumentIdThenReturnsMetadataList")
    void testWhenGetMetadataByDocumentIdThenReturnsMetadataList() {
        // Arrange
        String documentId = "doc1";
        MetadataDto[] arr = new MetadataDto[]{MetadataDto.builder().id("m1").build()};
        ResponseEntity<MetadataDto[]> response = ResponseEntity.ok(arr);
        when(restTemplate.getForEntity("/documents/" + documentId + "/metadata", MetadataDto[].class)).thenReturn(response);

        // Act
        List<MetadataDto> result = metadataService.getMetadataByDocumentId(documentId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/documents/" + documentId + "/metadata", MetadataDto[].class);
    }
}
