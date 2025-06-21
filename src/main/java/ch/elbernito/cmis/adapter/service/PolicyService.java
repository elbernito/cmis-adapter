package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.PolicyDto;

/**
 * Service interface for CMIS policy operations.
 */
public interface PolicyService {

    /**
     * Creates a new policy.
     * @param policyDto the policy to create
     * @return the created policy
     */
    PolicyDto createPolicy(PolicyDto policyDto);

    /**
     * Gets a policy by ID.
     * @param policyId policy ID
     * @return the policy
     */
    PolicyDto getPolicy(String policyId);

    /**
     * Deletes a policy by ID.
     * @param policyId policy ID
     */
    void deletePolicy(String policyId);

    /**
     * Applies a policy to an object.
     * @param objectId object ID
     * @param policyId policy ID
     */
    void applyPolicyToObject(String objectId, String policyId);

    /**
     * Removes a policy from an object.
     * @param objectId object ID
     * @param policyId policy ID
     */
    void removePolicyFromObject(String objectId, String policyId);
}
