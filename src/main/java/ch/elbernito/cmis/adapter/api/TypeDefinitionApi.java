package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.TypeDefinitionDto;
import ch.elbernito.cmis.adapter.service.TypeDefinitionService;
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
 * API layer for type definition-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/types")
@Tag(name = "TypeDefinition", description = "CMIS type definition API with metrics and detailed OpenAPI documentation.")
public class TypeDefinitionApi {

    private final TypeDefinitionService typeDefinitionService;

    @Timed(value = "cmis.typedef.getall.time", description = "Time spent fetching all type definitions")
    @Counted(value = "cmis.typedef.getall.count", description = "Count of type definition fetches")
    @Operation(summary = "Get all type definitions", description = "Fetches all CMIS type definitions (children of a given type, or all).")
    @GetMapping("/children")
    public List<TypeDefinitionDto> getAllTypeDefinitions() {
        log.info("API: Get all type definitions");
        return typeDefinitionService.getAllTypeDefinitions();
    }

    @Timed(value = "cmis.typedef.get.time", description = "Time spent fetching a type definition")
    @Counted(value = "cmis.typedef.get.count", description = "Count of type definition single fetches")
    @Operation(summary = "Get type definition", description = "Fetches a CMIS type definition by its ID.")
    @GetMapping("/{typeId}")
    public TypeDefinitionDto getTypeDefinition(
            @Parameter(description = "The type definition ID", required = true) @PathVariable String typeId) {
        log.info("API: Get type definition with ID {}", typeId);
        return typeDefinitionService.getTypeDefinition(typeId);
    }
}
