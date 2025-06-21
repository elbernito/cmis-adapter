package ch.elbernito.cmis.adapter.service.impl.prod;

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
 * PROD RestTemplate implementation of TypeDefinitionService.
 * Forwards all type definition-related requests to the configured PROD CMIS endpoint.
 */
@Slf4j
@Service
public class TypeDefinitionServiceProdImpl implements TypeDefinitionService {

    private final RestTemplate restTemplate;

    @Value("${cmis.prod.environment-tag:PROD}")
    public String environmentTag;

    public TypeDefinitionServiceProdImpl(@Qualifier("prodRestTemplate") RestTemplate restTemplate) {
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
