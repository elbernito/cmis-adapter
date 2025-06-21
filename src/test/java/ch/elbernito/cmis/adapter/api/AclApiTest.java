package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.AclDto;
import ch.elbernito.cmis.adapter.service.AclService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AclApi.
 * Tests all API operations for CMIS ACL using a mocked AclService.
 */
class AclApiTest {

    private AclService aclService;
    private AclApi aclApi;

    @BeforeEach
    void setUp() {
        aclService = mock(AclService.class);
        aclApi = new AclApi(aclService);
    }

    /**
     * Tests that getting ACL for an object returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetAclForObjectThenReturnsAclList")
    void testWhenGetAclForObjectThenReturnsAclList() {
        // Arrange
        String objectId = "doc1";
        AclDto a1 = AclDto.builder().id("acl1").objectId(objectId).build();
        AclDto a2 = AclDto.builder().id("acl2").objectId(objectId).build();
        List<AclDto> list = Arrays.asList(a1, a2);
        when(aclService.getAclForObject(objectId)).thenReturn(list);

        // Act
        List<AclDto> result = aclApi.getAclForObject(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(aclService).getAclForObject(objectId);
    }

    /**
     * Tests that setting ACL for an object returns the updated ACL and delegates to the service.
     */
    @Test
    @DisplayName("testWhenSetAclForObjectThenReturnsUpdatedAcl")
    void testWhenSetAclForObjectThenReturnsUpdatedAcl() {
        // Arrange
        String objectId = "doc1";
        AclDto input = AclDto.builder().id("acl1").objectId(objectId).build();
        when(aclService.setAclForObject(objectId, input)).thenReturn(input);

        // Act
        AclDto result = aclApi.setAclForObject(objectId, input);

        // Assert
        assertNotNull(result);
        assertEquals("acl1", result.getId());
        verify(aclService).setAclForObject(objectId, input);
    }
}
