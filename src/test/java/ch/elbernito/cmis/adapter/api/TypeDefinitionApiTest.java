package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.TypeDefinitionDto;
import ch.elbernito.cmis.adapter.service.TypeDefinitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for TypeDefinitionApi.
 * Tests all API operations for CMIS type definitions using a mocked TypeDefinitionService.
 */
class TypeDefinitionApiTest {

    private TypeDefinitionService typeDefinitionService;
    private TypeDefinitionApi typeDefinitionApi;

    @BeforeEach
    void setUp() {
        typeDefinitionService = mock(TypeDefinitionService.class);
        typeDefinitionApi = new TypeDefinitionApi(typeDefinitionService);
    }

    /**
     * Tests that getting all type definitions returns the correct list and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetAllTypeDefinitionsThenReturnsList")
    void testWhenGetAllTypeDefinitionsThenReturnsList() {
        // Arrange
        TypeDefinitionDto t1 = TypeDefinitionDto.builder().id("cmis:doc").build();
        TypeDefinitionDto t2 = TypeDefinitionDto.builder().id("cmis:folder").build();
        List<TypeDefinitionDto> list = Arrays.asList(t1, t2);
        when(typeDefinitionService.getAllTypeDefinitions()).thenReturn(list);

        // Act
        List<TypeDefinitionDto> result = typeDefinitionApi.getAllTypeDefinitions();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(typeDefinitionService).getAllTypeDefinitions();
    }

    /**
     * Tests that getting a type definition by ID returns the correct entry and delegates to the service.
     */
    @Test
    @DisplayName("testWhenGetTypeDefinitionThenReturnsType")
    void testWhenGetTypeDefinitionThenReturnsType() {
        // Arrange
        String typeId = "cmis:doc";
        TypeDefinitionDto expected = TypeDefinitionDto.builder().id(typeId).build();
        when(typeDefinitionService.getTypeDefinition(typeId)).thenReturn(expected);

        // Act
        TypeDefinitionDto result = typeDefinitionApi.getTypeDefinition(typeId);

        // Assert
        assertNotNull(result);
        assertEquals(typeId, result.getId());
        verify(typeDefinitionService).getTypeDefinition(typeId);
    }
}
