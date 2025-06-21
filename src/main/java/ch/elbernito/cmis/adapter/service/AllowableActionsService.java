package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;

/**
 * Service interface for CMIS allowable actions operations.
 */
public interface AllowableActionsService {

    /**
     * Gets allowable actions for a CMIS object.
     * @param objectId object ID
     * @return AllowableActionsDto
     */
    AllowableActionsDto getAllowableActions(String objectId);
}
