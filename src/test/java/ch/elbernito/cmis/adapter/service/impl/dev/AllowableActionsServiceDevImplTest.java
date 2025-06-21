package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AllowableActionsServiceDevImpl.
 * Ensures correct REST calls are made for allowable actions operations in the DEV stack.
 */
class AllowableActionsServiceDevImplTest {

    private RestTemplate restTemplate;
    private AllowableActionsServiceDevImpl allowableActionsService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        allowableActionsService = new AllowableActionsServiceDevImpl(restTemplate);
        allowableActionsService.environmentTag = "DEV";
    }

    @Test
    @DisplayName("testWhenGetAllowableActionsThenReturnsDto")
    void testWhenGetAllowableActionsThenReturnsDto() {
        // Arrange
        String objectId = "doc1";
        AllowableActionsDto expected = AllowableActionsDto.builder().objectId(objectId).canRead(true).build();
        when(restTemplate.getForObject("/objects/" + objectId + "/allowable-actions", AllowableActionsDto.class))
                .thenReturn(expected);

        // Act
        AllowableActionsDto result = allowableActionsService.getAllowableActions(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(objectId, result.getObjectId());
        assertTrue(result.isCanRead());
        verify(restTemplate).getForObject("/objects/" + objectId + "/allowable-actions", AllowableActionsDto.class);
    }
}
