package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS Metadata.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing metadata for a CMIS object.")
public class MetadataDto {

    @Schema(description = "Unique identifier for the metadata entry", example = "meta123")
    private String id;

    @Schema(description = "Document or object ID to which the metadata belongs", example = "doc42")
    private String documentId;

    @Schema(description = "Metadata key", example = "department")
    private String key;

    @Schema(description = "Metadata value", example = "Finance")
    private String value;

}
