package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.RetentionDto;
import ch.elbernito.cmis.adapter.service.RetentionService;
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
 * API layer for retention-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/retentions")
@Tag(name = "Retention", description = "CMIS retention API with metrics and detailed OpenAPI documentation.")
public class RetentionApi {

    private final RetentionService retentionService;

    @Timed(value = "cmis.retention.create.time", description = "Time spent creating retention entries")
    @Counted(value = "cmis.retention.create.count", description = "Count of retention creations")
    @Operation(summary = "Create retention", description = "Creates a new CMIS retention entry and returns the result.")
    @PostMapping
    public RetentionDto createRetention(
            @Parameter(description = "The retention entry to create", required = true) @RequestBody RetentionDto retentionDto) {
        log.info("API: Create retention");
        return retentionService.createRetention(retentionDto);
    }

    @Timed(value = "cmis.retention.get.time", description = "Time spent fetching retentions")
    @Counted(value = "cmis.retention.get.count", description = "Count of retention fetches")
    @Operation(summary = "Get retention", description = "Fetches a CMIS retention entry by its ID.")
    @GetMapping("/{retentionId}")
    public RetentionDto getRetention(
            @Parameter(description = "The retention ID", required = true) @PathVariable String retentionId) {
        log.info("API: Get retention with ID {}", retentionId);
        return retentionService.getRetention(retentionId);
    }

    @Timed(value = "cmis.retention.delete.time", description = "Time spent deleting retentions")
    @Counted(value = "cmis.retention.delete.count", description = "Count of retention deletions")
    @Operation(summary = "Delete retention", description = "Deletes a CMIS retention entry by its ID.")
    @DeleteMapping("/{retentionId}")
    public void deleteRetention(
            @Parameter(description = "The retention ID", required = true) @PathVariable String retentionId) {
        log.info("API: Delete retention with ID {}", retentionId);
        retentionService.deleteRetention(retentionId);
    }

    @Timed(value = "cmis.retention.byObject.time", description = "Time spent fetching retentions by object")
    @Counted(value = "cmis.retention.byObject.count", description = "Count of retention fetches by object")
    @Operation(summary = "Get all retentions for object", description = "Fetches all retentions for a CMIS object.")
    @GetMapping("/object/{objectId}")
    public List<RetentionDto> getRetentionsByObjectId(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId) {
        log.info("API: Get retentions for object with ID {}", objectId);
        return retentionService.getRetentionsByObjectId(objectId);
    }
}
