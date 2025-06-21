package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for repository meta info.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Repository metadata information.")
public class RepositoryMetaDto {

    @Schema(description = "Unique identifier of the repository", example = "repo1")
    private String id;

    @Schema(description = "Display name", example = "My CMIS Repo")
    private String name;

    @Schema(description = "Description", example = "A test repository")
    private String description;
}
