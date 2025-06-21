package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.RetentionDto;
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
 * Unit tests for RetentionServiceDevImpl.
 * Ensures correct REST calls are made for retention operations in the DEV stack.
 */
class RetentionServiceDevImplTest {

    private RestTemplate restTemplate;
    private RetentionServiceDevImpl retentionService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        retentionService = new RetentionServiceDevImpl(restTemplate);
        retentionService.environmentTag = "DEV";
    }

    @Test
    @DisplayName("testWhenCreateRetentionThenPostsAndReturnsRetention")
    void testWhenCreateRetentionThenPostsAndReturnsRetention() {
        // Arrange
        RetentionDto input = RetentionDto.builder().id("ret1").build();
        when(restTemplate.postForObject("/retentions", input, RetentionDto.class)).thenReturn(input);

        // Act
        RetentionDto result = retentionService.createRetention(input);

        // Assert
        assertNotNull(result);
        assertEquals("ret1", result.getId());
        verify(restTemplate).postForObject("/retentions", input, RetentionDto.class);
    }

    @Test
    @DisplayName("testWhenGetRetentionThenReturnsRetention")
    void testWhenGetRetentionThenReturnsRetention() {
        // Arrange
        String retentionId = "ret1";
        RetentionDto expected = RetentionDto.builder().id(retentionId).build();
        when(restTemplate.getForObject("/retentions/" + retentionId, RetentionDto.class)).thenReturn(expected);

        // Act
        RetentionDto result = retentionService.getRetention(retentionId);

        // Assert
        assertNotNull(result);
        assertEquals(retentionId, result.getId());
        verify(restTemplate).getForObject("/retentions/" + retentionId, RetentionDto.class);
    }

    @Test
    @DisplayName("testWhenDeleteRetentionThenDeletesRetention")
    void testWhenDeleteRetentionThenDeletesRetention() {
        // Arrange
        String retentionId = "ret1";

        // Act
        retentionService.deleteRetention(retentionId);

        // Assert
        verify(restTemplate).delete("/retentions/" + retentionId);
    }

    @Test
    @DisplayName("testWhenGetRetentionsByObjectIdThenReturnsRetentionList")
    void testWhenGetRetentionsByObjectIdThenReturnsRetentionList() {
        // Arrange
        String objectId = "doc1";
        RetentionDto[] arr = new RetentionDto[]{RetentionDto.builder().id("ret1").build()};
        ResponseEntity<RetentionDto[]> response = ResponseEntity.ok(arr);
        when(restTemplate.getForEntity("/objects/" + objectId + "/retentions", RetentionDto[].class)).thenReturn(response);

        // Act
        List<RetentionDto> result = retentionService.getRetentionsByObjectId(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/objects/" + objectId + "/retentions", RetentionDto[].class);
    }
}
