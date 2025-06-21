package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS folder.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a CMIS Folder.")
public class FolderDto {

    @Schema(description = "Unique identifier of the folder", example = "folder123")
    private String id;

    @Schema(description = "Name of the folder", example = "MyFolder")
    private String name;

    @Schema(description = "Parent folder ID", example = "root")
    private String parentId;

    @Schema(description = "Path of the folder", example = "/docs/archive")
    private String path;

    @Schema(description = "List of child object IDs", example = "[\"doc123\",\"folder456\"]")
    private java.util.List<String> childrenIds;

}
