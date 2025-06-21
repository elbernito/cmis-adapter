package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS TypeDefinition.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a CMIS Type Definition.")
public class TypeDefinitionDto {

    @Schema(description = "Unique identifier of the type", example = "cmis:document")
    private String id;

    @Schema(description = "Display name of the type", example = "Document")
    private String displayName;

    @Schema(description = "Description of the type", example = "CMIS base document type")
    private String description;

    @Schema(description = "Parent type ID", example = "cmis:document")
    private String parentTypeId;

    @Schema(description = "Is this a base type?", example = "true")
    private boolean baseType;

    // Weitere Felder nach Bedarf
}
