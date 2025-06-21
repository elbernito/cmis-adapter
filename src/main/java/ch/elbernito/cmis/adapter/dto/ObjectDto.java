package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for a CMIS Object.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a generic CMIS Object.")
public class ObjectDto {

    @Schema(description = "Unique identifier of the object", example = "obj123")
    private String id;

    @Schema(description = "Name of the object", example = "Example Document")
    private String name;

    @Schema(description = "Type of the object (e.g. document, folder)", example = "document")
    private String type;

    @Schema(description = "Path of the object", example = "/docs/test.txt")
    private String path;

}
