package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for CMIS ChangeLog entry.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Data Transfer Object representing a CMIS ChangeLog entry.")
public class ChangeLogDto {

    @Schema(description = "Unique identifier of the change log entry", example = "clog123")
    private String id;

    @Schema(description = "Type of change (created, updated, deleted)", example = "created")
    private String changeType;

    @Schema(description = "ID of the affected object", example = "doc42")
    private String objectId;

    @Schema(description = "User who triggered the change", example = "alice")
    private String user;

    @Schema(description = "Datetime of change", example = "2024-06-20T19:30:00")
    private LocalDateTime changeTime;

    @Schema(description = "Additional change info", example = "Version updated")
    private String info;
}
