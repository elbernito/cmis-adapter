package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.VersionDto;
import ch.elbernito.cmis.adapter.service.VersionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for VersionApi.
 * Tests all API operations for CMIS versions using a mocked VersionService.
 */
class VersionApiTest {

    private VersionService versionService;
    private VersionApi versionApi;

    @BeforeEach
    void setUp() {
        versionService = mock(VersionService.class);
        versionApi = new VersionApi(versionService);
    }

    /**
     * Tests that fetching a version by ID returns the correct version and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetVersionThenReturnsVersion")
    void testWhenGetVersionThenReturnsVersion() {
        // Arrange
        String versionId = "v1";
        VersionDto expected = VersionDto.builder().label("1.0").build();
        when(versionService.getVersion(versionId)).thenReturn(expected);

        // Act
        VersionDto result = versionApi.getVersion(versionId);

        // Assert
        assertNotNull(result);
        assertEquals("1.0", result.getLabel());
        verify(versionService).getVersion(versionId);
    }

    /**
     * Tests that getting all versions for a document returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetVersionsForDocumentThenReturnsVersionList")
    void testWhenGetVersionsForDocumentThenReturnsVersionList() {
        // Arrange
        String docId = "doc1";
        VersionDto v1 = VersionDto.builder().label("1.0").build();
        VersionDto v2 = VersionDto.builder().label("2.0").build();
        List<VersionDto> versions = Arrays.asList(v1, v2);
        when(versionService.getVersionsForDocument(docId)).thenReturn(versions);

        // Act
        List<VersionDto> result = versionApi.getVersionsForDocument(docId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(versionService).getVersionsForDocument(docId);
    }
}
