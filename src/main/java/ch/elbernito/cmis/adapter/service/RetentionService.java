package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.RetentionDto;

import java.util.List;

/**
 * Service interface for CMIS retention operations.
 */
public interface RetentionService {

    /**
     * Creates a new retention entry.
     * @param retentionDto the retention entry to create
     * @return the created retention
     */
    RetentionDto createRetention(RetentionDto retentionDto);

    /**
     * Gets a retention entry by ID.
     * @param retentionId retention entry ID
     * @return the retention entry
     */
    RetentionDto getRetention(String retentionId);

    /**
     * Deletes a retention entry by ID.
     * @param retentionId retention entry ID
     */
    void deleteRetention(String retentionId);

    /**
     * Gets all retentions for a given object.
     * @param objectId object ID
     * @return list of retention entries
     */
    List<RetentionDto> getRetentionsByObjectId(String objectId);
}
