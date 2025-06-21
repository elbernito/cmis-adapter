package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.TypeDefinitionDto;
import ch.elbernito.cmis.adapter.service.TypeDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * DEV RestTemplate implementation of TypeDefinitionService.
 * Forwards all type definition-related requests to the configured DEV CMIS endpoint.
 */
@Slf4j
@Service
public class TypeDefinitionServiceDevImpl implements TypeDefinitionService {

    private final RestTemplate restTemplate;

    @Value("${cmis.dev.environment-tag:DEV}")
    public String environmentTag;

    public TypeDefinitionServiceDevImpl(@Qualifier("devRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public List<TypeDefinitionDto> getAllTypeDefinitions() {
        log.info("[{}] Fetching all type definitions via REST (GET /types/children)", environmentTag);
        ResponseEntity<TypeDefinitionDto[]> response = restTemplate.getForEntity("/types/children", TypeDefinitionDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public TypeDefinitionDto getTypeDefinition(String typeId) {
        log.info("[{}] Fetching type definition via REST (GET /types/{})", environmentTag, typeId);
        return restTemplate.getForObject("/types/" + typeId, TypeDefinitionDto.class);
    }
}
