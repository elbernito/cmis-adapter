package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.VersionDto;
import ch.elbernito.cmis.adapter.service.VersionService;
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
 * API layer for version-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/versions")
@Tag(name = "Versions", description = "CMIS version API with metrics and detailed OpenAPI documentation.")
public class VersionApi {

    private final VersionService versionService;

    /**
     * Fetches a version by its ID.
     * @param versionId the version ID
     * @return the version
     */
    @Timed(value = "cmis.version.get.time", description = "Time spent fetching versions")
    @Counted(value = "cmis.version.get.count", description = "Count of version fetches")
    @Operation(
            summary = "Get version",
            description = "Fetches a CMIS version by its ID."
    )
    @GetMapping("/{versionId}")
    public VersionDto getVersion(
            @Parameter(description = "The version ID", required = true) @PathVariable String versionId) {
        log.info("API: Get version with ID {}", versionId);
        return versionService.getVersion(versionId);
    }

    /**
     * Returns all versions for a document.
     * @param documentId the document ID
     * @return list of document versions
     */
    @Timed(value = "cmis.version.document.versions.time", description = "Time spent fetching all versions for a document")
    @Counted(value = "cmis.version.document.versions.count", description = "Count of document all-version fetches")
    @Operation(
            summary = "Get all versions for document",
            description = "Returns all versions for a CMIS document."
    )
    @GetMapping("/document/{documentId}/all-versions")
    public List<VersionDto> getVersionsForDocument(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId) {
        log.info("API: Get all versions for document with ID {}", documentId);
        return versionService.getVersionsForDocument(documentId);
    }
}
