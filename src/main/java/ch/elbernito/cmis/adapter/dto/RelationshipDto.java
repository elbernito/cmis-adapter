package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS Relationship.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a CMIS Relationship.")
public class RelationshipDto {

    @Schema(description = "Unique identifier of the relationship", example = "rel123")
    private String id;

    @Schema(description = "Source object ID", example = "obj1")
    private String sourceId;

    @Schema(description = "Target object ID", example = "obj2")
    private String targetId;

    @Schema(description = "Type of the relationship", example = "related")
    private String type;

}
