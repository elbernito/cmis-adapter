package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.RelationshipDto;

import java.util.List;

/**
 * Service interface for CMIS relationship operations.
 */
public interface RelationshipService {

    /**
     * Creates a new relationship.
     * @param relationshipDto the relationship to create
     * @return the created relationship
     */
    RelationshipDto createRelationship(RelationshipDto relationshipDto);

    /**
     * Gets a relationship by ID.
     * @param relationshipId relationship ID
     * @return the relationship
     */
    RelationshipDto getRelationship(String relationshipId);

    /**
     * Deletes a relationship by ID.
     * @param relationshipId relationship ID
     */
    void deleteRelationship(String relationshipId);

    /**
     * Gets all relationships for an object.
     * @param objectId object ID
     * @return list of relationships
     */
    List<RelationshipDto> getRelationshipsByObjectId(String objectId);
}
