package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.MetadataDto;

import java.util.List;

/**
 * Service interface for CMIS metadata operations.
 */
public interface MetadataService {

    /**
     * Creates a new metadata entry.
     * @param metadataDto metadata to create
     * @return the created metadata
     */
    MetadataDto createMetadata(MetadataDto metadataDto);

    /**
     * Gets a metadata entry by ID.
     * @param metadataId metadata ID
     * @return the metadata entry
     */
    MetadataDto getMetadata(String metadataId);

    /**
     * Updates an existing metadata entry.
     * @param metadataId metadata ID
     * @param metadataDto updated metadata
     * @return the updated metadata
     */
    MetadataDto updateMetadata(String metadataId, MetadataDto metadataDto);

    /**
     * Deletes a metadata entry by ID.
     * @param metadataId metadata ID
     */
    void deleteMetadata(String metadataId);

    /**
     * Gets all metadata for a document.
     * @param documentId document ID
     * @return list of metadata entries
     */
    List<MetadataDto> getMetadataByDocumentId(String documentId);
}
