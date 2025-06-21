package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.VersionDto;

import java.util.List;

/**
 * Service interface for CMIS version operations.
 */
public interface VersionService {

    /**
     * Returns a version by its ID.
     * @param versionId the version ID
     * @return the version
     */
    VersionDto getVersion(String versionId);

    /**
     * Returns all versions for a document.
     * @param documentId the document ID
     * @return list of versions
     */
    List<VersionDto> getVersionsForDocument(String documentId);
}
