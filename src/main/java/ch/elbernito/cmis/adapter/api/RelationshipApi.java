package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.RelationshipDto;
import ch.elbernito.cmis.adapter.service.RelationshipService;
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
 * API layer for relationship-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/relationships")
@Tag(name = "Relationship", description = "CMIS relationship API with metrics and detailed OpenAPI documentation.")
public class RelationshipApi {

    private final RelationshipService relationshipService;

    @Timed(value = "cmis.relationship.create.time", description = "Time spent creating relationships")
    @Counted(value = "cmis.relationship.create.count", description = "Count of relationship creations")
    @Operation(summary = "Create relationship", description = "Creates a new CMIS relationship and returns the result.")
    @PostMapping
    public RelationshipDto createRelationship(
            @Parameter(description = "The relationship to create", required = true) @RequestBody RelationshipDto relationshipDto) {
        log.info("API: Create relationship");
        return relationshipService.createRelationship(relationshipDto);
    }

    @Timed(value = "cmis.relationship.get.time", description = "Time spent fetching relationships")
    @Counted(value = "cmis.relationship.get.count", description = "Count of relationship fetches")
    @Operation(summary = "Get relationship", description = "Fetches a CMIS relationship by its ID.")
    @GetMapping("/{relationshipId}")
    public RelationshipDto getRelationship(
            @Parameter(description = "The relationship ID", required = true) @PathVariable String relationshipId) {
        log.info("API: Get relationship with ID {}", relationshipId);
        return relationshipService.getRelationship(relationshipId);
    }

    @Timed(value = "cmis.relationship.delete.time", description = "Time spent deleting relationships")
    @Counted(value = "cmis.relationship.delete.count", description = "Count of relationship deletions")
    @Operation(summary = "Delete relationship", description = "Deletes a CMIS relationship by its ID.")
    @DeleteMapping("/{relationshipId}")
    public void deleteRelationship(
            @Parameter(description = "The relationship ID", required = true) @PathVariable String relationshipId) {
        log.info("API: Delete relationship with ID {}", relationshipId);
        relationshipService.deleteRelationship(relationshipId);
    }

    @Timed(value = "cmis.relationship.byObject.time", description = "Time spent fetching relationships by object")
    @Counted(value = "cmis.relationship.byObject.count", description = "Count of object relationship fetches")
    @Operation(summary = "Get all relationships for object", description = "Fetches all relationships for a CMIS object.")
    @GetMapping("/object/{objectId}")
    public List<RelationshipDto> getRelationshipsByObjectId(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId) {
        log.info("API: Get relationships for object with ID {}", objectId);
        return relationshipService.getRelationshipsByObjectId(objectId);
    }
}
