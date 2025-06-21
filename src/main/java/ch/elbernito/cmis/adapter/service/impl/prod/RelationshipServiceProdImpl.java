package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.RelationshipDto;
import ch.elbernito.cmis.adapter.service.RelationshipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * PROD RestTemplate implementation of RelationshipService.
 * Forwards all relationship-related requests to the configured PROD CMIS endpoint.
 */
@Slf4j
@Service
public class RelationshipServiceProdImpl implements RelationshipService {

    private final RestTemplate restTemplate;

    @Value("${cmis.prod.environment-tag:PROD}")
    public String environmentTag;

    private final String basePath = "/relationships";

    public RelationshipServiceProdImpl(@Qualifier("prodRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public RelationshipDto createRelationship(RelationshipDto relationshipDto) {
        log.info("[{}] Creating relationship via REST (POST {})", environmentTag, basePath);
        return restTemplate.postForObject(basePath, relationshipDto, RelationshipDto.class);
    }

    @Override
    public RelationshipDto getRelationship(String relationshipId) {
        log.info("[{}] Fetching relationship via REST (GET {}/{})", environmentTag, basePath, relationshipId);
        return restTemplate.getForObject(basePath + "/" + relationshipId, RelationshipDto.class);
    }

    @Override
    public void deleteRelationship(String relationshipId) {
        log.info("[{}] Deleting relationship via REST (DELETE {}/{})", environmentTag, basePath, relationshipId);
        restTemplate.delete(basePath + "/" + relationshipId);
    }

    @Override
    public List<RelationshipDto> getRelationshipsByObjectId(String objectId) {
        log.info("[{}] Fetching all relationships for object via REST (GET /objects/{}/relationships/all)", environmentTag, objectId);
        ResponseEntity<RelationshipDto[]> response = restTemplate.getForEntity("/objects/" + objectId + "/relationships/all", RelationshipDto[].class);
        return Arrays.asList(response.getBody());
    }
}
