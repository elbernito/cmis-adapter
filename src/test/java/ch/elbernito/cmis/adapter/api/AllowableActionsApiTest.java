package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import ch.elbernito.cmis.adapter.service.AllowableActionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AllowableActionsApi.
 * Tests all API operations for CMIS allowable actions using a mocked AllowableActionsService.
 */
class AllowableActionsApiTest {

    private AllowableActionsService allowableActionsService;
    private AllowableActionsApi allowableActionsApi;

    @BeforeEach
    void setUp() {
        allowableActionsService = mock(AllowableActionsService.class);
        allowableActionsApi = new AllowableActionsApi(allowableActionsService);
    }

    /**
     * Tests that getting allowable actions returns the correct DTO and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetAllowableActionsThenReturnsDto")
    void testWhenGetAllowableActionsThenReturnsDto() {
        // Arrange
        String objectId = "doc1";
        AllowableActionsDto expected = AllowableActionsDto.builder().objectId(objectId).canRead(true).build();
        when(allowableActionsService.getAllowableActions(objectId)).thenReturn(expected);

        // Act
        AllowableActionsDto result = allowableActionsApi.getAllowableActions(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(objectId, result.getObjectId());
        assertTrue(result.isCanRead());
        verify(allowableActionsService).getAllowableActions(objectId);
    }
}
