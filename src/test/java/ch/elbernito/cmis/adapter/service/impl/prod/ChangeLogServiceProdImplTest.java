package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.ChangeLogDto;
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
 * Unit tests for ChangeLogServiceProdImpl.
 * Ensures correct REST calls are made for change log operations in the PROD stack.
 */
class ChangeLogServiceProdImplTest {

    private RestTemplate restTemplate;
    private ChangeLogServiceProdImpl changeLogService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        changeLogService = new ChangeLogServiceProdImpl(restTemplate);
        changeLogService.environmentTag = "PROD";
    }

    @Test
    @DisplayName("testWhenGetChangeLogThenReturnsList")
    void testWhenGetChangeLogThenReturnsList() {
        // Arrange
        ChangeLogDto[] arr = new ChangeLogDto[]{ChangeLogDto.builder().id("cl1").build()};
        ResponseEntity<ChangeLogDto[]> response = ResponseEntity.ok(arr);
        when(restTemplate.getForEntity("/changelog", ChangeLogDto[].class)).thenReturn(response);

        // Act
        List<ChangeLogDto> result = changeLogService.getChangeLog();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/changelog", ChangeLogDto[].class);
    }

    @Test
    @DisplayName("testWhenGetContentChangesThenReturnsList")
    void testWhenGetContentChangesThenReturnsList() {
        // Arrange
        ChangeLogDto[] arr = new ChangeLogDto[]{ChangeLogDto.builder().id("cl2").build()};
        ResponseEntity<ChangeLogDto[]> response = ResponseEntity.ok(arr);
        when(restTemplate.getForEntity("/changelog/changes", ChangeLogDto[].class)).thenReturn(response);

        // Act
        List<ChangeLogDto> result = changeLogService.getContentChanges();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/changelog/changes", ChangeLogDto[].class);
    }
}
