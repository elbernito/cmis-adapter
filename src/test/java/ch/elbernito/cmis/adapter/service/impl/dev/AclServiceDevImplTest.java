package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.AclDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AclServiceDevImpl.
 * Ensures correct REST calls are made for ACL operations in the DEV stack.
 */
class AclServiceDevImplTest {

    private RestTemplate restTemplate;
    private AclServiceDevImpl aclService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        aclService = new AclServiceDevImpl(restTemplate);
        aclService.environmentTag = "DEV";
    }

    @Test
    @DisplayName("testWhenGetAclForObjectThenReturnsAclList")
    void testWhenGetAclForObjectThenReturnsAclList() {
        // Arrange
        String objectId = "obj1";
        AclDto[] arr = new AclDto[]{AclDto.builder().id("acl1").build()};
        ResponseEntity<AclDto[]> response = ResponseEntity.ok(arr);
        when(restTemplate.getForEntity("/objects/" + objectId + "/acl", AclDto[].class)).thenReturn(response);

        // Act
        List<AclDto> result = aclService.getAclForObject(objectId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/objects/" + objectId + "/acl", AclDto[].class);
    }

    @Test
    @DisplayName("testWhenSetAclForObjectThenPutsAndReturnsAcl")
    void testWhenSetAclForObjectThenPutsAndReturnsAcl() {
        // Arrange
        String objectId = "obj1";
        AclDto aclDto = AclDto.builder().id("acl1").build();

        // Act
        AclDto result = aclService.setAclForObject(objectId, aclDto);

        // Assert
        assertNotNull(result);
        assertEquals("acl1", result.getId());
        verify(restTemplate).put("/objects/" + objectId + "/acl", aclDto);
    }
}
