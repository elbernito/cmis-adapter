package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.VersionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for VersionServiceDevImpl.
 * Ensures correct REST calls are made for version operations in the DEV stack.
 */
class VersionServiceDevImplTest {

    private RestTemplate restTemplate;
    private VersionServiceDevImpl versionService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        versionService = new VersionServiceDevImpl(restTemplate);
        versionService.environmentTag = "DEV";
    }

    /**
     * Tests that getVersion sends a GET request and returns the version.
     */
    @Test
    @DisplayName("testWhenGetVersionThenReturnsVersion")
    void testWhenGetVersionThenReturnsVersion() {
        // Arrange
        String versionId = "v1";
        VersionDto expected = VersionDto.builder().label("1.0").build();
        when(restTemplate.getForObject("/versions/v1", VersionDto.class)).thenReturn(expected);

        // Act
        VersionDto result = versionService.getVersion(versionId);

        // Assert
        assertNotNull(result);
        assertEquals("1.0", result.getLabel());
        verify(restTemplate).getForObject("/versions/v1", VersionDto.class);
    }

    /**
     * Tests that getVersionsForDocument sends a GET request and returns the version list.
     */
    @Test
    @DisplayName("testWhenGetVersionsForDocumentThenReturnsVersionList")
    void testWhenGetVersionsForDocumentThenReturnsVersionList() {
        // Arrange
        String docId = "doc1";
        VersionDto[] versions = new VersionDto[]{VersionDto.builder().label("1.0").build(), VersionDto.builder().label("2.0").build()};
        ResponseEntity<VersionDto[]> response = new ResponseEntity<>(versions, HttpStatus.OK);
        when(restTemplate.getForEntity("/documents/" + docId + "/all-versions", VersionDto[].class)).thenReturn(response);

        // Act
        List<VersionDto> result = versionService.getVersionsForDocument(docId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restTemplate).getForEntity("/documents/" + docId + "/all-versions", VersionDto[].class);
    }
}
