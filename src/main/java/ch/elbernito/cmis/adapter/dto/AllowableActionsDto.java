package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS AllowableActions.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing allowable actions on a CMIS object.")
public class AllowableActionsDto {

    @Schema(description = "Object ID", example = "doc123")
    private String objectId;

    @Schema(description = "Whether the object can be read", example = "true")
    private boolean canRead;

    @Schema(description = "Whether the object can be updated", example = "false")
    private boolean canUpdate;

    @Schema(description = "Whether the object can be deleted", example = "true")
    private boolean canDelete;

    @Schema(description = "Whether the object can be checked out", example = "false")
    private boolean canCheckOut;

    @Schema(description = "Whether the object can be moved", example = "true")
    private boolean canMove;

    @Schema(description = "Whether the object can be copied", example = "true")
    private boolean canCopy;

}