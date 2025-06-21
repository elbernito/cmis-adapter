package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.MetadataDto;
import ch.elbernito.cmis.adapter.service.MetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for MetadataApi.
 * Tests all API operations for CMIS metadata using a mocked MetadataService.
 */
class MetadataApiTest {

    private MetadataService metadataService;
    private MetadataApi metadataApi;

    @BeforeEach
    void setUp() {
        metadataService = mock(MetadataService.class);
        metadataApi = new MetadataApi(metadataService);
    }

    /**
     * Tests that creating metadata returns the created metadata and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCreateMetadataThenReturnsMetadata")
    void testWhenCreateMetadataThenReturnsMetadata() {
        // Arrange
        MetadataDto input = MetadataDto.builder().id("m1").key("foo").value("bar").build();
        when(metadataService.createMetadata(input)).thenReturn(input);

        // Act
        MetadataDto result = metadataApi.createMetadata(input);

        // Assert
        assertNotNull(result);
        assertEquals("m1", result.getId());
        verify(metadataService).createMetadata(input);
    }

    /**
     * Tests that fetching metadata by ID returns the correct metadata and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetMetadataThenReturnsMetadata")
    void testWhenGetMetadataThenReturnsMetadata() {
        // Arrange
        String metadataId = "m1";
        MetadataDto expected = MetadataDto.builder().id(metadataId).build();
        when(metadataService.getMetadata(metadataId)).thenReturn(expected);

        // Act
        MetadataDto result = metadataApi.getMetadata(metadataId);

        // Assert
        assertNotNull(result);
        assertEquals(metadataId, result.getId());
        verify(metadataService).getMetadata(metadataId);
    }

    /**
     * Tests that updating metadata returns the updated metadata and delegates to the service.
     */
    @Test
    @DisplayName("testWhenUpdateMetadataThenReturnsUpdatedMetadata")
    void testWhenUpdateMetadataThenReturnsUpdatedMetadata() {
        // Arrange
        String metadataId = "m1";
        MetadataDto input = MetadataDto.builder().id(metadataId).value("new").build();
        when(metadataService.updateMetadata(metadataId, input)).thenReturn(input);

        // Act
        MetadataDto result = metadataApi.updateMetadata(metadataId, input);

        // Assert
        assertNotNull(result);
        assertEquals("new", result.getValue());
        verify(metadataService).updateMetadata(metadataId, input);
    }

    /**
     * Tests that deleting metadata delegates to the service.
     */
    @Test
    @DisplayName("testWhenDeleteMetadataThenMetadataServiceIsCalled")
    void testWhenDeleteMetadataThenMetadataServiceIsCalled() {
        // Arrange
        String metadataId = "m1";

        // Act
        metadataApi.deleteMetadata(metadataId);

        // Assert
        verify(metadataService).deleteMetadata(metadataId);
    }

    /**
     * Tests that getting metadata by document ID returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetMetadataByDocumentIdThenReturnsMetadataList")
    void testWhenGetMetadataByDocumentIdThenReturnsMetadataList() {
        // Arrange
        String documentId = "doc1";
        MetadataDto m1 = MetadataDto.builder().id("m1").build();
        MetadataDto m2 = MetadataDto.builder().id("m2").build();
        List<MetadataDto> list = Arrays.asList(m1, m2);
        when(metadataService.getMetadataByDocumentId(documentId)).thenReturn(list);

        // Act
        List<MetadataDto> result = metadataApi.getMetadataByDocumentId(documentId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(metadataService).getMetadataByDocumentId(documentId);
    }
}
