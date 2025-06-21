package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.ChangeLogDto;
import ch.elbernito.cmis.adapter.service.ChangeLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ChangeLogApi.
 * Tests all API operations for CMIS change log using a mocked ChangeLogService.
 */
class ChangeLogApiTest {

    private ChangeLogService changeLogService;
    private ChangeLogApi changeLogApi;

    @BeforeEach
    void setUp() {
        changeLogService = mock(ChangeLogService.class);
        changeLogApi = new ChangeLogApi(changeLogService);
    }

    /**
     * Tests that getting the change log returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetChangeLogThenReturnsList")
    void testWhenGetChangeLogThenReturnsList() {
        // Arrange
        ChangeLogDto c1 = ChangeLogDto.builder().id("cl1").build();
        ChangeLogDto c2 = ChangeLogDto.builder().id("cl2").build();
        List<ChangeLogDto> list = Arrays.asList(c1, c2);
        when(changeLogService.getChangeLog()).thenReturn(list);

        // Act
        List<ChangeLogDto> result = changeLogApi.getChangeLog();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(changeLogService).getChangeLog();
    }

    /**
     * Tests that getting the content changes returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetContentChangesThenReturnsList")
    void testWhenGetContentChangesThenReturnsList() {
        // Arrange
        ChangeLogDto c1 = ChangeLogDto.builder().id("cl1").build();
        List<ChangeLogDto> list = Arrays.asList(c1);
        when(changeLogService.getContentChanges()).thenReturn(list);

        // Act
        List<ChangeLogDto> result = changeLogApi.getContentChanges();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(changeLogService).getContentChanges();
    }
}
