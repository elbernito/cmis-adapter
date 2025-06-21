package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.AclDto;

import java.util.List;

/**
 * Service interface for CMIS ACL operations.
 */
public interface AclService {

    /**
     * Gets the ACL for a given object.
     * @param objectId object ID
     * @return list of ACL entries
     */
    List<AclDto> getAclForObject(String objectId);

    /**
     * Sets the ACL for a given object.
     * @param objectId object ID
     * @param aclDto ACL entry
     * @return updated ACL entry
     */
    AclDto setAclForObject(String objectId, AclDto aclDto);
}
