package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS document version.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a CMIS Document Version.")
public class VersionDto {

    @Schema(description = "Version label", example = "1.0")
    private String label;

    @Schema(description = "Document ID", example = "doc123")
    private String documentId;

    @Schema(description = "Timestamp of the version", example = "2024-06-20T19:48:00Z")
    private String timestamp;

    @Schema(description = "Author of the version", example = "john.doe")
    private String author;

    @Schema(description = "Change comment for this version", example = "Initial version")
    private String comment;

}
