package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.FolderDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;

import java.util.List;

/**
 * Service interface for CMIS folder operations.
 */
public interface FolderService {

    /**
     * Creates a new folder.
     * @param folderDto folder data
     * @return the created folder
     */
    FolderDto createFolder(FolderDto folderDto);

    /**
     * Gets a folder by ID.
     * @param folderId folder ID
     * @return the folder
     */
    FolderDto getFolder(String folderId);

    /**
     * Updates a folder.
     * @param folderId folder ID
     * @param folderDto new folder data
     * @return the updated folder
     */
    FolderDto updateFolder(String folderId, FolderDto folderDto);

    /**
     * Deletes a folder.
     * @param folderId folder ID
     */
    void deleteFolder(String folderId);

    /**
     * Gets all children objects of a folder.
     * @param folderId folder ID
     * @return list of child objects
     */
    List<ObjectDto> getFolderChildren(String folderId);

    /**
     * Gets all descendant objects of a folder.
     * @param folderId folder ID
     * @return list of descendant objects
     */
    List<ObjectDto> getFolderDescendants(String folderId);

    /**
     * Gets the parent folder of a folder.
     * @param folderId folder ID
     * @return parent folder
     */
    FolderDto getFolderParent(String folderId);

    /**
     * Deletes an entire folder tree.
     * @param folderId folder ID
     */
    void deleteFolderTree(String folderId);

    /**
     * Gets all checked out documents in a folder.
     * @param folderId folder ID
     * @return list of checked out documents
     */
    List<DocumentDto> getCheckedOutDocs(String folderId);
}
