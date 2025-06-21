package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.MetadataDto;
import ch.elbernito.cmis.adapter.service.MetadataService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API layer for metadata-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/metadata")
@Tag(name = "Metadata", description = "CMIS metadata API with metrics and detailed OpenAPI documentation.")
public class MetadataApi {

    private final MetadataService metadataService;

    @Timed(value = "cmis.metadata.create.time", description = "Time spent creating metadata")
    @Counted(value = "cmis.metadata.create.count", description = "Count of metadata creations")
    @Operation(summary = "Create metadata", description = "Creates a new CMIS metadata entry and returns the result.")
    @PostMapping
    public MetadataDto createMetadata(
            @Parameter(description = "The metadata to create", required = true) @RequestBody MetadataDto metadataDto) {
        log.info("API: Create metadata");
        return metadataService.createMetadata(metadataDto);
    }

    @Timed(value = "cmis.metadata.get.time", description = "Time spent fetching metadata")
    @Counted(value = "cmis.metadata.get.count", description = "Count of metadata fetches")
    @Operation(summary = "Get metadata", description = "Fetches a CMIS metadata entry by its ID.")
    @GetMapping("/{metadataId}")
    public MetadataDto getMetadata(
            @Parameter(description = "The metadata ID", required = true) @PathVariable String metadataId) {
        log.info("API: Get metadata with ID {}", metadataId);
        return metadataService.getMetadata(metadataId);
    }

    @Timed(value = "cmis.metadata.update.time", description = "Time spent updating metadata")
    @Counted(value = "cmis.metadata.update.count", description = "Count of metadata updates")
    @Operation(summary = "Update metadata", description = "Updates an existing CMIS metadata entry by ID.")
    @PutMapping("/{metadataId}")
    public MetadataDto updateMetadata(
            @Parameter(description = "The metadata ID", required = true) @PathVariable String metadataId,
            @Parameter(description = "The updated metadata data", required = true) @RequestBody MetadataDto metadataDto) {
        log.info("API: Update metadata with ID {}", metadataId);
        return metadataService.updateMetadata(metadataId, metadataDto);
    }

    @Timed(value = "cmis.metadata.delete.time", description = "Time spent deleting metadata")
    @Counted(value = "cmis.metadata.delete.count", description = "Count of metadata deletions")
    @Operation(summary = "Delete metadata", description = "Deletes a CMIS metadata entry by its ID.")
    @DeleteMapping("/{metadataId}")
    public void deleteMetadata(
            @Parameter(description = "The metadata ID", required = true) @PathVariable String metadataId) {
        log.info("API: Delete metadata with ID {}", metadataId);
        metadataService.deleteMetadata(metadataId);
    }

    @Timed(value = "cmis.metadata.byDocument.time", description = "Time spent fetching metadata for a document")
    @Counted(value = "cmis.metadata.byDocument.count", description = "Count of document metadata fetches")
    @Operation(summary = "Get all metadata for document", description = "Fetches all metadata entries for a CMIS document.")
    @GetMapping("/document/{documentId}")
    public List<MetadataDto> getMetadataByDocumentId(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId) {
        log.info("API: Get metadata for document with ID {}", documentId);
        return metadataService.getMetadataByDocumentId(documentId);
    }
}
