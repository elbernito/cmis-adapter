package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.PolicyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PolicyServiceDevImpl.
 * Ensures correct REST calls are made for policy operations in the DEV stack.
 */
class PolicyServiceDevImplTest {

    private RestTemplate restTemplate;
    private PolicyServiceDevImpl policyService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        policyService = new PolicyServiceDevImpl(restTemplate);
        policyService.environmentTag = "DEV";
    }

    @Test
    @DisplayName("testWhenCreatePolicyThenPostsAndReturnsPolicy")
    void testWhenCreatePolicyThenPostsAndReturnsPolicy() {
        // Arrange
        PolicyDto input = PolicyDto.builder().id("pol1").build();
        when(restTemplate.postForObject("/policies", input, PolicyDto.class)).thenReturn(input);

        // Act
        PolicyDto result = policyService.createPolicy(input);

        // Assert
        assertNotNull(result);
        assertEquals("pol1", result.getId());
        verify(restTemplate).postForObject("/policies", input, PolicyDto.class);
    }

    @Test
    @DisplayName("testWhenGetPolicyThenReturnsPolicy")
    void testWhenGetPolicyThenReturnsPolicy() {
        // Arrange
        String policyId = "pol1";
        PolicyDto expected = PolicyDto.builder().id(policyId).build();
        when(restTemplate.getForObject("/policies/" + policyId, PolicyDto.class)).thenReturn(expected);

        // Act
        PolicyDto result = policyService.getPolicy(policyId);

        // Assert
        assertNotNull(result);
        assertEquals(policyId, result.getId());
        verify(restTemplate).getForObject("/policies/" + policyId, PolicyDto.class);
    }

    @Test
    @DisplayName("testWhenDeletePolicyThenDeletesPolicy")
    void testWhenDeletePolicyThenDeletesPolicy() {
        // Arrange
        String policyId = "pol1";

        // Act
        policyService.deletePolicy(policyId);

        // Assert
        verify(restTemplate).delete("/policies/" + policyId);
    }

    @Test
    @DisplayName("testWhenApplyPolicyToObjectThenPosts")
    void testWhenApplyPolicyToObjectThenPosts() {
        // Arrange
        String objectId = "obj1";
        String policyId = "pol1";

        // Act
        policyService.applyPolicyToObject(objectId, policyId);

        // Assert
        verify(restTemplate).postForObject("/objects/" + objectId + "/policies/" + policyId + "/apply", null, Void.class);
    }

    @Test
    @DisplayName("testWhenRemovePolicyFromObjectThenPosts")
    void testWhenRemovePolicyFromObjectThenPosts() {
        // Arrange
        String objectId = "obj1";
        String policyId = "pol1";

        // Act
        policyService.removePolicyFromObject(objectId, policyId);

        // Assert
        verify(restTemplate).postForObject("/objects/" + objectId + "/policies/" + policyId + "/remove", null, Void.class);
    }
}
