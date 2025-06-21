package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.PolicyDto;
import ch.elbernito.cmis.adapter.service.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PolicyApi.
 * Tests all API operations for CMIS policies using a mocked PolicyService.
 */
class PolicyApiTest {

    private PolicyService policyService;
    private PolicyApi policyApi;

    @BeforeEach
    void setUp() {
        policyService = mock(PolicyService.class);
        policyApi = new PolicyApi(policyService);
    }

    /**
     * Tests that creating a policy returns the created policy and delegates to the service.
     */
    @Test
    @DisplayName("testWhenCreatePolicyThenReturnsPolicy")
    void testWhenCreatePolicyThenReturnsPolicy() {
        // Arrange
        PolicyDto input = PolicyDto.builder().id("pol1").name("Sec").build();
        when(policyService.createPolicy(input)).thenReturn(input);

        // Act
        PolicyDto result = policyApi.createPolicy(input);

        // Assert
        assertNotNull(result);
        assertEquals("pol1", result.getId());
        verify(policyService).createPolicy(input);
    }

    /**
     * Tests that fetching a policy by ID returns the correct policy and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetPolicyThenReturnsPolicy")
    void testWhenGetPolicyThenReturnsPolicy() {
        // Arrange
        String policyId = "pol1";
        PolicyDto expected = PolicyDto.builder().id(policyId).build();
        when(policyService.getPolicy(policyId)).thenReturn(expected);

        // Act
        PolicyDto result = policyApi.getPolicy(policyId);

        // Assert
        assertNotNull(result);
        assertEquals(policyId, result.getId());
        verify(policyService).getPolicy(policyId);
    }

    /**
     * Tests that deleting a policy delegates to the service.
     */
    @Test
    @DisplayName("testWhenDeletePolicyThenPolicyServiceIsCalled")
    void testWhenDeletePolicyThenPolicyServiceIsCalled() {
        // Arrange
        String policyId = "pol1";

        // Act
        policyApi.deletePolicy(policyId);

        // Assert
        verify(policyService).deletePolicy(policyId);
    }

    /**
     * Tests that applying a policy to an object delegates to the service.
     */
    @Test
    @DisplayName("testWhenApplyPolicyToObjectThenPolicyServiceIsCalled")
    void testWhenApplyPolicyToObjectThenPolicyServiceIsCalled() {
        // Arrange
        String objectId = "obj1";
        String policyId = "pol1";

        // Act
        policyApi.applyPolicyToObject(objectId, policyId);

        // Assert
        verify(policyService).applyPolicyToObject(objectId, policyId);
    }

    /**
     * Tests that removing a policy from an object delegates to the service.
     */
    @Test
    @DisplayName("testWhenRemovePolicyFromObjectThenPolicyServiceIsCalled")
    void testWhenRemovePolicyFromObjectThenPolicyServiceIsCalled() {
        // Arrange
        String objectId = "obj1";
        String policyId = "pol1";

        // Act
        policyApi.removePolicyFromObject(objectId, policyId);

        // Assert
        verify(policyService).removePolicyFromObject(objectId, policyId);
    }
}
