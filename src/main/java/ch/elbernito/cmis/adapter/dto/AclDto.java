package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS ACL (Access Control List) entry.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a CMIS ACL entry.")
public class AclDto {

    @Schema(description = "Unique identifier of the ACL entry", example = "acl123")
    private String id;

    @Schema(description = "Object ID to which the ACL applies", example = "doc123")
    private String objectId;

    @Schema(description = "Principal (user or group)", example = "jane.doe")
    private String principal;

    @Schema(description = "Permissions granted (e.g., read, write, all)", example = "read")
    private String permission;

    @Schema(description = "Whether the permission is direct or indirect", example = "true")
    private boolean direct;
}
