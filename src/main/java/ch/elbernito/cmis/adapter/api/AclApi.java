package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.AclDto;
import ch.elbernito.cmis.adapter.service.AclService;
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
 * API layer for ACL-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/acl")
@Tag(name = "ACL", description = "CMIS ACL API with metrics and detailed OpenAPI documentation.")
public class AclApi {

    private final AclService aclService;

    @Timed(value = "cmis.acl.get.time", description = "Time spent fetching ACL")
    @Counted(value = "cmis.acl.get.count", description = "Count of ACL fetches")
    @Operation(summary = "Get ACL", description = "Fetches the ACL for a CMIS object by its ID.")
    @GetMapping("/{objectId}")
    public List<AclDto> getAclForObject(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId) {
        log.info("API: Get ACL for object with ID {}", objectId);
        return aclService.getAclForObject(objectId);
    }

    @Timed(value = "cmis.acl.set.time", description = "Time spent setting ACL")
    @Counted(value = "cmis.acl.set.count", description = "Count of ACL sets")
    @Operation(summary = "Set ACL", description = "Sets the ACL for a CMIS object by its ID.")
    @PutMapping("/{objectId}")
    public AclDto setAclForObject(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId,
            @Parameter(description = "The ACL entry", required = true) @RequestBody AclDto aclDto) {
        log.info("API: Set ACL for object with ID {}", objectId);
        return aclService.setAclForObject(objectId, aclDto);
    }
}
