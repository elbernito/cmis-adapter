package ch.elbernito.cmis.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for CMIS document.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing a CMIS Document.")
@ToString
public class DocumentDto {

    @Schema(description = "Unique identifier of the document", example = "doc123")
    private String id;

    @Schema(description = "Name of the document", example = "MyDocument.pdf")
    private String name;

    @Schema(description = "MIME type of the document", example = "application/pdf")
    private String mimeType;

    @Schema(description = "Content of the document as byte array", type = "string", format = "byte")
    private byte[] content;

    @Schema(description = "Folder ID the document belongs to", example = "folder1")
    private String folderId;

    @Schema(description = "Version label of the document", example = "1.0")
    private String versionLabel;

    @Schema(description = "Indicates if the document is checked out", example = "false")
    private boolean checkedOut;

}
