package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.PolicyDto;
import ch.elbernito.cmis.adapter.service.PolicyService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * API layer for policy-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/policies")
@Tag(name = "Policy", description = "CMIS policy API with metrics and detailed OpenAPI documentation.")
public class PolicyApi {

    private final PolicyService policyService;

    @Timed(value = "cmis.policy.create.time", description = "Time spent creating policies")
    @Counted(value = "cmis.policy.create.count", description = "Count of policy creations")
    @Operation(summary = "Create policy", description = "Creates a new CMIS policy and returns the result.")
    @PostMapping
    public PolicyDto createPolicy(
            @Parameter(description = "The policy to create", required = true) @RequestBody PolicyDto policyDto) {
        log.info("API: Create policy");
        return policyService.createPolicy(policyDto);
    }

    @Timed(value = "cmis.policy.get.time", description = "Time spent fetching policies")
    @Counted(value = "cmis.policy.get.count", description = "Count of policy fetches")
    @Operation(summary = "Get policy", description = "Fetches a CMIS policy by its ID.")
    @GetMapping("/{policyId}")
    public PolicyDto getPolicy(
            @Parameter(description = "The policy ID", required = true) @PathVariable String policyId) {
        log.info("API: Get policy with ID {}", policyId);
        return policyService.getPolicy(policyId);
    }

    @Timed(value = "cmis.policy.delete.time", description = "Time spent deleting policies")
    @Counted(value = "cmis.policy.delete.count", description = "Count of policy deletions")
    @Operation(summary = "Delete policy", description = "Deletes a CMIS policy by its ID.")
    @DeleteMapping("/{policyId}")
    public void deletePolicy(
            @Parameter(description = "The policy ID", required = true) @PathVariable String policyId) {
        log.info("API: Delete policy with ID {}", policyId);
        policyService.deletePolicy(policyId);
    }

    @Timed(value = "cmis.policy.apply.time", description = "Time spent applying policies")
    @Counted(value = "cmis.policy.apply.count", description = "Count of policy applies")
    @Operation(summary = "Apply policy to object", description = "Applies a policy to a CMIS object.")
    @PostMapping("/objects/{objectId}/apply/{policyId}")
    public void applyPolicyToObject(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId,
            @Parameter(description = "The policy ID", required = true) @PathVariable String policyId) {
        log.info("API: Apply policy {} to object {}", policyId, objectId);
        policyService.applyPolicyToObject(objectId, policyId);
    }

    @Timed(value = "cmis.policy.remove.time", description = "Time spent removing policies")
    @Counted(value = "cmis.policy.remove.count", description = "Count of policy removes")
    @Operation(summary = "Remove policy from object", description = "Removes a policy from a CMIS object.")
    @PostMapping("/objects/{objectId}/remove/{policyId}")
    public void removePolicyFromObject(
            @Parameter(description = "The object ID", required = true) @PathVariable String objectId,
            @Parameter(description = "The policy ID", required = true) @PathVariable String policyId) {
        log.info("API: Remove policy {} from object {}", policyId, objectId);
        policyService.removePolicyFromObject(objectId, policyId);
    }
}
