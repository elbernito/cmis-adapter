package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.RepositoryInfoDto;
import ch.elbernito.cmis.adapter.dto.RepositoryMetaDto;
import ch.elbernito.cmis.adapter.service.RepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for RepositoryApi.
 */
class RepositoryApiTest {

    private RepositoryService repositoryService;
    private RepositoryApi repositoryApi;

    @BeforeEach
    void setUp() {
        repositoryService = mock(RepositoryService.class);
        repositoryApi = new RepositoryApi(repositoryService);
    }

    @Test
    @DisplayName("testWhenGetAllRepositoriesThenReturnsList")
    void testWhenGetAllRepositoriesThenReturnsList() {
        List<RepositoryMetaDto> list = Arrays.asList(
                RepositoryMetaDto.builder().id("repo1").build(),
                RepositoryMetaDto.builder().id("repo2").build()
        );
        when(repositoryService.getAllRepositories()).thenReturn(list);

        List<RepositoryMetaDto> result = repositoryApi.getAllRepositories();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repositoryService).getAllRepositories();
    }

    @Test
    @DisplayName("testWhenGetRepositoryThenReturnsDto")
    void testWhenGetRepositoryThenReturnsDto() {
        String id = "repo1";
        RepositoryMetaDto dto = RepositoryMetaDto.builder().id(id).build();
        when(repositoryService.getRepository(id)).thenReturn(dto);

        RepositoryMetaDto result = repositoryApi.getRepository(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(repositoryService).getRepository(id);
    }

    @Test
    @DisplayName("testWhenGetRepositoryInfoThenReturnsDto")
    void testWhenGetRepositoryInfoThenReturnsDto() {
        String id = "repo1";
        RepositoryInfoDto infoDto = RepositoryInfoDto.builder().id(id).build();
        when(repositoryService.getRepositoryInfo(id)).thenReturn(infoDto);

        RepositoryInfoDto result = repositoryApi.getRepositoryInfo(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(repositoryService).getRepositoryInfo(id);
    }
}
