package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.TypeDefinitionDto;
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
 * Unit tests for TypeDefinitionServiceDevImpl.
 * Ensures correct REST calls are made for type definition operations in the DEV stack.
 */
class TypeDefinitionServiceDevImplTest {

    private RestTemplate restTemplate;
    private TypeDefinitionServiceDevImpl typeDefinitionService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        typeDefinitionService = new TypeDefinitionServiceDevImpl(restTemplate);
        typeDefinitionService.environmentTag = "DEV";
    }

    @Test
    @DisplayName("testWhenGetAllTypeDefinitionsThenReturnsList")
    void testWhenGetAllTypeDefinitionsThenReturnsList() {
        // Arrange
        TypeDefinitionDto[] arr = new TypeDefinitionDto[]{TypeDefinitionDto.builder().id("t1").build()};
        ResponseEntity<TypeDefinitionDto[]> response = ResponseEntity.ok(arr);
        when(restTemplate.getForEntity("/types/children", TypeDefinitionDto[].class)).thenReturn(response);

        // Act
        List<TypeDefinitionDto> result = typeDefinitionService.getAllTypeDefinitions();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity("/types/children", TypeDefinitionDto[].class);
    }

    @Test
    @DisplayName("testWhenGetTypeDefinitionThenReturnsType")
    void testWhenGetTypeDefinitionThenReturnsType() {
        // Arrange
        String typeId = "t1";
        TypeDefinitionDto expected = TypeDefinitionDto.builder().id(typeId).build();
        when(restTemplate.getForObject("/types/" + typeId, TypeDefinitionDto.class)).thenReturn(expected);

        // Act
        TypeDefinitionDto result = typeDefinitionService.getTypeDefinition(typeId);

        // Assert
        assertNotNull(result);
        assertEquals(typeId, result.getId());
        verify(restTemplate).getForObject("/types/" + typeId, TypeDefinitionDto.class);
    }
}
