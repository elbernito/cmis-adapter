package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.RetentionDto;
import ch.elbernito.cmis.adapter.service.RetentionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for RetentionApi.
 * Tests all API operations for CMIS retention using a mocked RetentionService.
 */
class RetentionApiTest {

    private RetentionService retentionService;
    private RetentionApi retentionApi;

    @BeforeEach
    void setUp() {
        retentionService = mock(RetentionService.class);
        retentionApi = new RetentionApi(retentionService);
    }

    /**
     * Tests that creating a retention entry returns the created entry and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCreateRetentionThenReturnsRetention")
    void testWhenCreateRetentionThenReturnsRetention() {
        // Arrange
        RetentionDto input = RetentionDto.builder().id("ret1").objectId("doc1").build();
        when(retentionService.createRetention(input)).thenReturn(input);

        // Act
        RetentionDto result = retentionApi.createRetention(input);

        // Assert
        assertNotNull(result);
        assertEquals("ret1", result.getId());
        verify(retentionService).createRetention(input);
    }

    /**
     * Tests that fetching a retention entry by ID returns the correct entry and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetRetentionThenReturnsRetention")
    void testWhenGetRetentionThenReturnsRetention() {
        // Arrange
        String retentionId = "ret1";
        RetentionDto expected = RetentionDto.builder().id(retentionId).build();
        when(retentionService.getRetention(retentionId)).thenReturn(expected);

        // Act
        RetentionDto result = retentionApi.getRetention(retentionId);

        // Assert
        assertNotNull(result);
        assertEquals(retentionId, result.getId());
        verify(retentionService).getRetention(retentionId);
    }

    /**
     * Tests that deleting a retention entry delegates to the service.
     */
    @Test
    @DisplayName("testWhenDeleteRetentionThenRetentionServiceIsCalled")
    void testWhenDeleteRetentionThenRetentionServiceIsCalled() {
        // Arrange
        String retentionId = "ret1";

        // Act
        retentionApi.deleteRetention(retentionId);

        // Assert
        verify(retentionService).deleteRetention(retentionId);
    }

    /**
     * Tests that getting retentions by object ID returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetRetentionsByObjectIdThenReturnsRetentionList")
    void testWhenGetRetentionsByObjectIdThenReturnsRetentionList() {
        // Arrange
        String objectId = "doc1";
        RetentionDto r1 = RetentionDto.builder().id("ret1").build();
        RetentionDto r2 = RetentionDto.builder().id("ret2").build();
        List<RetentionDto> list = Arrays.asList(r1, r2);
        when(retentionService.getRetentionsByObjectId(objectId)).thenReturn(list);

        // Act
        List<RetentionDto> result = retentionApi.getRetentionsByObjectId(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(retentionService).getRetentionsByObjectId(objectId);
    }
}
