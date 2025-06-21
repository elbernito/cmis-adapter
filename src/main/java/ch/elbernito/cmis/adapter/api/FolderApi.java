package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.FolderDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import ch.elbernito.cmis.adapter.service.FolderService;
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
 * API layer for folder-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/cmis/1.2/folders")
@Tag(name = "Folders", description = "CMIS folder API with metrics and detailed OpenAPI documentation.")
public class FolderApi {

    private final FolderService folderService;

    @Timed(value = "cmis.folder.create.time", description = "Time spent creating folders")
    @Counted(value = "cmis.folder.create.count", description = "Count of folder creations")
    @Operation(summary = "Create folder", description = "Creates a new CMIS folder and returns the result.")
    @PostMapping
    public FolderDto createFolder(
            @Parameter(description = "The folder to create", required = true) @RequestBody FolderDto folderDto) {
        log.info("API: Create folder");
        return folderService.createFolder(folderDto);
    }

    @Timed(value = "cmis.folder.get.time", description = "Time spent fetching folders")
    @Counted(value = "cmis.folder.get.count", description = "Count of folder fetches")
    @Operation(summary = "Get folder", description = "Fetches a CMIS folder by its ID.")
    @GetMapping("/{folderId}")
    public FolderDto getFolder(
            @Parameter(description = "The folder ID", required = true) @PathVariable String folderId) {
        log.info("API: Get folder with ID {}", folderId);
        return folderService.getFolder(folderId);
    }

    @Timed(value = "cmis.folder.update.time", description = "Time spent updating folders")
    @Counted(value = "cmis.folder.update.count", description = "Count of folder updates")
    @Operation(summary = "Update folder", description = "Updates an existing CMIS folder by ID.")
    @PutMapping("/{folderId}")
    public FolderDto updateFolder(
            @Parameter(description = "The folder ID", required = true) @PathVariable String folderId,
            @Parameter(description = "The updated folder data", required = true) @RequestBody FolderDto folderDto) {
        log.info("API: Update folder with ID {}", folderId);
        return folderService.updateFolder(folderId, folderDto);
    }

    @Timed(value = "cmis.folder.delete.time", description = "Time spent deleting folders")
    @Counted(value = "cmis.folder.delete.count", description = "Count of folder deletions")
    @Operation(summary = "Delete folder", description = "Deletes a CMIS folder by its ID.")
    @DeleteMapping("/{folderId}")
    public void deleteFolder(
            @Parameter(description = "The folder ID", required = true) @PathVariable String folderId) {
        log.info("API: Delete folder with ID {}", folderId);
        folderService.deleteFolder(folderId);
    }

    @Timed(value = "cmis.folder.children.time", description = "Time spent fetching folder children")
    @Counted(value = "cmis.folder.children.count", description = "Count of folder children fetches")
    @Operation(summary = "Get folder children", description = "Fetches all children of a CMIS folder.")
    @GetMapping("/{folderId}/children")
    public List<ObjectDto> getFolderChildren(
            @Parameter(description = "The folder ID", required = true) @PathVariable String folderId) {
        log.info("API: Get children for folder with ID {}", folderId);
        return folderService.getFolderChildren(folderId);
    }

    @Timed(value = "cmis.folder.descendants.time", description = "Time spent fetching folder descendants")
    @Counted(value = "cmis.folder.descendants.count", description = "Count of folder descendant fetches")
    @Operation(summary = "Get folder descendants", description = "Fetches all descendants of a CMIS folder.")
    @GetMapping("/{folderId}/descendants")
    public List<ObjectDto> getFolderDescendants(
            @Parameter(description = "The folder ID", required = true) @PathVariable String folderId) {
        log.info("API: Get descendants for folder with ID {}", folderId);
        return folderService.getFolderDescendants(folderId);
    }

    @Timed(value = "cmis.folder.parent.time", description = "Time spent fetching folder parent")
    @Counted(value = "cmis.folder.parent.count", description = "Count of folder parent fetches")
    @Operation(summary = "Get folder parent", description = "Fetches the parent of a CMIS folder.")
    @GetMapping("/{folderId}/parent")
    public FolderDto getFolderParent(
            @Parameter(description = "The folder ID", required = true) @PathVariable String folderId) {
        log.info("API: Get parent for folder with ID {}", folderId);
        return folderService.getFolderParent(folderId);
    }

    @Timed(value = "cmis.folder.deleteTree.time", description = "Time spent deleting folder tree")
    @Counted(value = "cmis.folder.deleteTree.count", description = "Count of folder tree deletions")
    @Operation(summary = "Delete folder tree", description = "Deletes a CMIS folder tree by its ID.")
    @DeleteMapping("/{folderId}/tree")
    public void deleteFolderTree(
            @Parameter(description = "The folder ID", required = true) @PathVariable String folderId) {
        log.info("API: Delete folder tree with ID {}", folderId);
        folderService.deleteFolderTree(folderId);
    }

    @Timed(value = "cmis.folder.checkedout.time", description = "Time spent fetching checked out documents")
    @Counted(value = "cmis.folder.checkedout.count", description = "Count of checked out document fetches")
    @Operation(summary = "Get checked out documents", description = "Fetches all checked out documents in a CMIS folder.")
    @GetMapping("/{folderId}/checkedout")
    public List<DocumentDto> getCheckedOutDocs(
            @Parameter(description = "The folder ID", required = true) @PathVariable String folderId) {
        log.info("API: Get checked out documents for folder with ID {}", folderId);
        return folderService.getCheckedOutDocs(folderId);
    }
}
