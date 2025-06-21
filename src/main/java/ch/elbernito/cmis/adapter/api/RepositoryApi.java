package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.RepositoryInfoDto;
import ch.elbernito.cmis.adapter.dto.RepositoryMetaDto;
import ch.elbernito.cmis.adapter.service.RepositoryService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API layer for repository-related CMIS operations.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/repositories")
@Tag(name = "Repository", description = "CMIS repository API with metrics and OpenAPI documentation.")
public class RepositoryApi {

    private final RepositoryService repositoryService;

    @Timed(value = "cmis.repository.getall.time", description = "Time spent fetching all repositories")
    @Counted(value = "cmis.repository.getall.count", description = "Count of repository list fetches")
    @Operation(summary = "Get all repositories", description = "Fetches all repository metadata entries.")
    @GetMapping
    public List<RepositoryMetaDto> getAllRepositories() {
        log.info("API: Get all repositories");
        return repositoryService.getAllRepositories();
    }

    @Timed(value = "cmis.repository.get.time", description = "Time spent fetching a repository")
    @Counted(value = "cmis.repository.get.count", description = "Count of single repository fetches")
    @Operation(summary = "Get repository by ID", description = "Fetches a repository metadata by its ID.")
    @GetMapping("/{id}")
    public RepositoryMetaDto getRepository(
            @Parameter(description = "The repository ID", required = true) @PathVariable String id) {
        log.info("API: Get repository {}", id);
        return repositoryService.getRepository(id);
    }

    @Timed(value = "cmis.repository.info.time", description = "Time spent fetching repository info")
    @Counted(value = "cmis.repository.info.count", description = "Count of repository info fetches")
    @Operation(summary = "Get repository info", description = "Fetches repository info and capabilities by ID.")
    @GetMapping("/{id}/info")
    public RepositoryInfoDto getRepositoryInfo(
            @Parameter(description = "The repository ID", required = true) @PathVariable String id) {
        log.info("API: Get repository info {}", id);
        return repositoryService.getRepositoryInfo(id);
    }
}
