package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import ch.elbernito.cmis.adapter.dto.RelationshipDto;
import ch.elbernito.cmis.adapter.service.ObjectService;
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
 * API layer for object-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/objects")
@Tag(name = "Objects", description = "CMIS object API with metrics and detailed OpenAPI documentation.")
public class ObjectApi {

    private final ObjectService objectService;

    @Timed(value = "cmis.object.get.time", description = "Time spent fetching objects")
    @Counted(value = "cmis.object.get.count", description = "Count of object fetches")
    @Operation(summary = "Get object", description = "Fetches a CMIS object by its ID.")
    @GetMapping("/{objectId}")
    public ObjectDto getObject(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId) {
        log.info("API: Get object with ID {}", objectId);
        return objectService.getObject(objectId);
    }

    @Timed(value = "cmis.object.byPath.time", description = "Time spent fetching objects by path")
    @Counted(value = "cmis.object.byPath.count", description = "Count of object-by-path fetches")
    @Operation(summary = "Get object by path", description = "Fetches a CMIS object by its path.")
    @GetMapping("/by-path")
    public ObjectDto getObjectByPath(
            @Parameter(description = "The object path", required = true) @RequestParam String path) {
        log.info("API: Get object by path {}", path);
        return objectService.getObjectByPath(path);
    }

    @Timed(value = "cmis.object.move.time", description = "Time spent moving objects")
    @Counted(value = "cmis.object.move.count", description = "Count of object moves")
    @Operation(summary = "Move object", description = "Moves a CMIS object to a target folder.")
    @PostMapping("/{objectId}/move")
    public ObjectDto moveObject(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId,
            @Parameter(description = "Target folder ID", required = true) @RequestParam String targetFolderId) {
        log.info("API: Move object {} to folder {}", objectId, targetFolderId);
        return objectService.moveObject(objectId, targetFolderId);
    }

    @Timed(value = "cmis.object.copy.time", description = "Time spent copying objects")
    @Counted(value = "cmis.object.copy.count", description = "Count of object copies")
    @Operation(summary = "Copy object", description = "Copies a CMIS object to a target folder.")
    @PostMapping("/{objectId}/copy")
    public ObjectDto copyObject(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId,
            @Parameter(description = "Target folder ID", required = true) @RequestParam String targetFolderId) {
        log.info("API: Copy object {} to folder {}", objectId, targetFolderId);
        return objectService.copyObject(objectId, targetFolderId);
    }

    @Timed(value = "cmis.object.allowableActions.time", description = "Time spent fetching allowable actions")
    @Counted(value = "cmis.object.allowableActions.count", description = "Count of allowable action fetches")
    @Operation(summary = "Get allowable actions", description = "Lists allowable actions for a CMIS object.")
    @GetMapping("/{objectId}/allowable-actions")
    public AllowableActionsDto getAllowableActions(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId) {
        log.info("API: Get allowable actions for object {}", objectId);
        return objectService.getAllowableActions(objectId);
    }

    @Timed(value = "cmis.object.relationships.time", description = "Time spent fetching object relationships")
    @Counted(value = "cmis.object.relationships.count", description = "Count of object relationship fetches")
    @Operation(summary = "Get object relationships", description = "Lists relationships for a CMIS object.")
    @GetMapping("/{objectId}/relationships")
    public List<RelationshipDto> getObjectRelationships(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId) {
        log.info("API: Get relationships for object {}", objectId);
        return objectService.getObjectRelationships(objectId);
    }
}
