package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import ch.elbernito.cmis.adapter.dto.RelationshipDto;

import java.util.List;

/**
 * Service interface for CMIS object operations.
 */
public interface ObjectService {

    /**
     * Gets a CMIS object by its ID.
     * @param objectId object ID
     * @return the object
     */
    ObjectDto getObject(String objectId);

    /**
     * Gets a CMIS object by its path.
     * @param path object path
     * @return the object
     */
    ObjectDto getObjectByPath(String path);

    /**
     * Moves a CMIS object to a target folder.
     * @param objectId object ID
     * @param targetFolderId target folder ID
     * @return the moved object
     */
    ObjectDto moveObject(String objectId, String targetFolderId);

    /**
     * Copies a CMIS object to a target folder.
     * @param objectId object ID
     * @param targetFolderId target folder ID
     * @return the copied object
     */
    ObjectDto copyObject(String objectId, String targetFolderId);

    /**
     * Gets allowable actions for an object.
     * @param objectId object ID
     * @return allowable actions DTO
     */
    AllowableActionsDto getAllowableActions(String objectId);

    /**
     * Gets all relationships for an object.
     * @param objectId object ID
     * @return list of relationships
     */
    List<RelationshipDto> getObjectRelationships(String objectId);
}
