package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.RepositoryInfoDto;
import ch.elbernito.cmis.adapter.dto.RepositoryMetaDto;
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
 * Unit tests for RepositoryServiceDevImpl.
 */
class RepositoryServiceDevImplTest {

    private RestTemplate restTemplate;
    private RepositoryServiceDevImpl service;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        service = new RepositoryServiceDevImpl(restTemplate);
        service.environmentTag = "DEV";
    }

    @Test
    @DisplayName("testWhenGetAllRepositoriesThenReturnsList")
    void testWhenGetAllRepositoriesThenReturnsList() {
        RepositoryMetaDto[] arr = new RepositoryMetaDto[]{RepositoryMetaDto.builder().id("r1").build()};
        ResponseEntity<RepositoryMetaDto[]> response = ResponseEntity.ok(arr);
        when(restTemplate.getForEntity("/repositories", RepositoryMetaDto[].class)).thenReturn(response);

        List<RepositoryMetaDto> result = service.getAllRepositories();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/repositories", RepositoryMetaDto[].class);
    }

    @Test
    @DisplayName("testWhenGetRepositoryThenReturnsDto")
    void testWhenGetRepositoryThenReturnsDto() {
        String id = "r1";
        RepositoryMetaDto dto = RepositoryMetaDto.builder().id(id).build();
        when(restTemplate.getForObject("/repositories/" + id, RepositoryMetaDto.class)).thenReturn(dto);

        RepositoryMetaDto result = service.getRepository(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(restTemplate).getForObject("/repositories/" + id, RepositoryMetaDto.class);
    }

    @Test
    @DisplayName("testWhenGetRepositoryInfoThenReturnsDto")
    void testWhenGetRepositoryInfoThenReturnsDto() {
        String id = "r1";
        RepositoryInfoDto dto = RepositoryInfoDto.builder().id(id).build();
        when(restTemplate.getForObject("/repositories/" + id + "/info", RepositoryInfoDto.class)).thenReturn(dto);

        RepositoryInfoDto result = service.getRepositoryInfo(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(restTemplate).getForObject("/repositories/" + id + "/info", RepositoryInfoDto.class);
    }
}
