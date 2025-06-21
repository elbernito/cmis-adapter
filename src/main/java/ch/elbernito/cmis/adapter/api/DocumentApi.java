package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.VersionDto;
import ch.elbernito.cmis.adapter.service.DocumentService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API layer for document-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@RequestMapping("/api/cmis/1.2/documents")
@AllArgsConstructor
@Tag(name = "Documents", description = "CMIS document API with metrics and detailed OpenAPI documentation.")
public class DocumentApi {

    private final DocumentService documentService;

    @Timed(value = "cmis.document.create.time", description = "Time spent creating documents")
    @Counted(value = "cmis.document.create.count", description = "Count of document creations")
    @Operation(summary = "Create document", description = "Creates a new CMIS document and returns the result.")
    @PostMapping
    public DocumentDto createDocument(
            @Parameter(description = "The document to create", required = true) @RequestBody DocumentDto docDto) {
        log.info("API: Create document");
        return documentService.createDocument(docDto);
    }

    @Timed(value = "cmis.document.get.time", description = "Time spent fetching documents")
    @Counted(value = "cmis.document.get.count", description = "Count of document fetches")
    @Operation(summary = "Get document", description = "Fetches a CMIS document by its ID.")
    @GetMapping("/{documentId}")
    public DocumentDto getDocument(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId) {
        log.info("API: Get document with ID {}", documentId);
        return documentService.getDocument(documentId);
    }

    @Timed(value = "cmis.document.update.time", description = "Time spent updating documents")
    @Counted(value = "cmis.document.update.count", description = "Count of document updates")
    @Operation(summary = "Update document", description = "Updates an existing CMIS document by ID.")
    @PutMapping("/{documentId}")
    public DocumentDto updateDocument(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId,
            @Parameter(description = "The updated document data", required = true) @RequestBody DocumentDto docDto) {
        log.info("API: Update document with ID {}", documentId);
        return documentService.updateDocument(documentId, docDto);
    }

    @Timed(value = "cmis.document.delete.time", description = "Time spent deleting documents")
    @Counted(value = "cmis.document.delete.count", description = "Count of document deletions")
    @Operation(summary = "Delete document", description = "Deletes a CMIS document by its ID.")
    @DeleteMapping("/{documentId}")
    public void deleteDocument(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId) {
        log.info("API: Delete document with ID {}", documentId);
        documentService.deleteDocument(documentId);
    }

    @Timed(value = "cmis.document.checkin.time", description = "Time spent checking in documents")
    @Counted(value = "cmis.document.checkin.count", description = "Count of document checkins")
    @Operation(summary = "Checkin document", description = "Checks in a CMIS document.")
    @PostMapping("/{documentId}/checkin")
    public DocumentDto checkinDocument(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId) {
        log.info("API: Checkin document with ID {}", documentId);
        return documentService.checkinDocument(documentId);
    }

    @Timed(value = "cmis.document.checkout.time", description = "Time spent checking out documents")
    @Counted(value = "cmis.document.checkout.count", description = "Count of document checkouts")
    @Operation(summary = "Checkout document", description = "Checks out a CMIS document.")
    @PostMapping("/{documentId}/checkout")
    public DocumentDto checkoutDocument(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId) {
        log.info("API: Checkout document with ID {}", documentId);
        return documentService.checkoutDocument(documentId);
    }

    @Timed(value = "cmis.document.versions.time", description = "Time spent fetching document versions")
    @Counted(value = "cmis.document.versions.count", description = "Count of document version fetches")
    @Operation(summary = "Get document versions", description = "Returns all versions for a CMIS document.")
    @GetMapping("/{documentId}/versions")
    public List<VersionDto> getDocumentVersions(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId) {
        log.info("API: Get versions for document with ID {}", documentId);
        return documentService.getDocumentVersions(documentId);
    }

    @Timed(value = "cmis.document.content.get.time", description = "Time spent fetching document content")
    @Counted(value = "cmis.document.content.get.count", description = "Count of document content fetches")
    @Operation(summary = "Get document content", description = "Returns the binary content of a CMIS document.")
    @GetMapping("/{documentId}/content")
    public byte[] getDocumentContent(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId) {
        log.info("API: Get content for document with ID {}", documentId);
        return documentService.getDocumentContent(documentId);
    }

    @Timed(value = "cmis.document.content.update.time", description = "Time spent updating document content")
    @Counted(value = "cmis.document.content.update.count", description = "Count of document content updates")
    @Operation(summary = "Update document content", description = "Updates the binary content of a CMIS document.")
    @PutMapping("/{documentId}/content")
    public void updateDocumentContent(
            @Parameter(description = "The document ID", required = true) @PathVariable String documentId,
            @Parameter(description = "The new content as byte array", required = true) @RequestBody byte[] content,
            @Parameter(description = "The MIME type", required = true) @RequestParam String mimeType) {
        log.info("API: Update content for document with ID {}", documentId);
        documentService.updateDocumentContent(documentId, content, mimeType);
    }
}
