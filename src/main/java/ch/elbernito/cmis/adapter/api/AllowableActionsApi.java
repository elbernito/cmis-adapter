package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import ch.elbernito.cmis.adapter.service.AllowableActionsService;
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

/**
 * API layer for allowable actions-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/allowable-actions")
@Tag(name = "AllowableActions", description = "CMIS allowable actions API with metrics and detailed OpenAPI documentation.")
public class AllowableActionsApi {

    private final AllowableActionsService allowableActionsService;

    @Timed(value = "cmis.allowableactions.get.time", description = "Time spent fetching allowable actions")
    @Counted(value = "cmis.allowableactions.get.count", description = "Count of allowable actions fetches")
    @Operation(summary = "Get allowable actions", description = "Fetches the allowable actions for a CMIS object by its ID.")
    @GetMapping("/{objectId}")
    public AllowableActionsDto getAllowableActions(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId) {
        log.info("API: Get allowable actions for object with ID {}", objectId);
        return allowableActionsService.getAllowableActions(objectId);
    }
}
