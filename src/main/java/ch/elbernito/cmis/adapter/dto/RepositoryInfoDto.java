package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for repository info and capabilities.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Repository detailed info and capabilities.")
public class RepositoryInfoDto {

    @Schema(description = "Repository ID", example = "repo1")
    private String id;

    @Schema(description = "Vendor name", example = "Acme Inc.")
    private String vendorName;

    @Schema(description = "Product name", example = "CMIS Adapter")
    private String productName;

    @Schema(description = "Product version", example = "1.0.0")
    private String productVersion;

    @Schema(description = "CMIS version supported", example = "1.2")
    private String cmisVersionSupported;

    @Schema(description = "Repository description", example = "CMIS Test Repo")
    private String description;
}
