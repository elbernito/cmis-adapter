package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS Retention.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a CMIS Retention entry.")
public class RetentionDto {

    @Schema(description = "Unique identifier of the retention entry", example = "ret123")
    private String id;

    @Schema(description = "ID of the object/document", example = "doc1")
    private String objectId;

    @Schema(description = "Retention policy name", example = "Legal Hold")
    private String name;

    @Schema(description = "Description or reason for retention", example = "Tax compliance until 2030")
    private String description;

    @Schema(description = "Expiration date of the retention in ISO format", example = "2030-12-31T23:59:59Z")
    private String expirationDate;

}
