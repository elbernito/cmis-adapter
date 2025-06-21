package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.TypeDefinitionDto;

import java.util.List;

/**
 * Service interface for CMIS type definition operations.
 */
public interface TypeDefinitionService {

    /**
     * Gets all type definitions (children of a given type, or all).
     * @return list of type definitions
     */
    List<TypeDefinitionDto> getAllTypeDefinitions();

    /**
     * Gets a type definition by its ID.
     * @param typeId type definition ID
     * @return the type definition
     */
    TypeDefinitionDto getTypeDefinition(String typeId);
}
