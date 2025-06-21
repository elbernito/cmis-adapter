package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS Policy.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a CMIS Policy.")
public class PolicyDto {

    @Schema(description = "Unique identifier of the policy", example = "pol123")
    private String id;

    @Schema(description = "Policy name", example = "Confidentiality")
    private String name;

    @Schema(description = "Policy description", example = "Documents covered by this policy must not be shared externally.")
    private String description;

    @Schema(description = "Type of the policy", example = "confidentiality")
    private String type;
}
