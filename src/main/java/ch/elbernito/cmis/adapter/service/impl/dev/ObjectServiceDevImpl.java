package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import ch.elbernito.cmis.adapter.dto.RelationshipDto;
import ch.elbernito.cmis.adapter.service.ObjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * DEV RestTemplate implementation of ObjectService.
 * Forwards all object-related requests to the configured DEV CMIS endpoint.
 */
@Slf4j
@Service
public class ObjectServiceDevImpl implements ObjectService {

    private final RestTemplate restTemplate;

    @Value("${cmis.dev.environment-tag:DEV}")
    public String environmentTag;

    private final String basePath = "/objects";

    public ObjectServiceDevImpl(@Qualifier("devRestTemplate")  RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public ObjectDto getObject(String objectId) {
        log.info("[{}] Fetching object via REST (GET {}/{})", environmentTag, basePath, objectId);
        return restTemplate.getForObject(basePath + "/" + objectId, ObjectDto.class);
    }

    @Override
    public ObjectDto getObjectByPath(String path) {
        log.info("[{}] Fetching object by path via REST (GET {}/by-path?path={})", environmentTag, basePath, path);
        return restTemplate.getForObject(basePath + "/by-path?path=" + path, ObjectDto.class);
    }

    @Override
    public ObjectDto moveObject(String objectId, String targetFolderId) {
        log.info("[{}] Moving object via REST (POST {}/{}/move?targetFolderId={})", environmentTag, basePath, objectId, targetFolderId);
        return restTemplate.postForObject(basePath + "/" + objectId + "/move?targetFolderId=" + targetFolderId, null, ObjectDto.class);
    }

    @Override
    public ObjectDto copyObject(String objectId, String targetFolderId) {
        log.info("[{}] Copying object via REST (POST {}/{}/copy?targetFolderId={})", environmentTag, basePath, objectId, targetFolderId);
        return restTemplate.postForObject(basePath + "/" + objectId + "/copy?targetFolderId=" + targetFolderId, null, ObjectDto.class);
    }

    @Override
    public AllowableActionsDto getAllowableActions(String objectId) {
        log.info("[{}] Getting allowable actions via REST (GET {}/{}/allowable-actions)", environmentTag, basePath, objectId);
        return restTemplate.getForObject(basePath + "/" + objectId + "/allowable-actions", AllowableActionsDto.class);
    }

    @Override
    public List<RelationshipDto> getObjectRelationships(String objectId) {
        log.info("[{}] Getting object relationships via REST (GET {}/{}/relationships)", environmentTag, basePath, objectId);
        ResponseEntity<RelationshipDto[]> response = restTemplate.getForEntity(basePath + "/" + objectId + "/relationships", RelationshipDto[].class);
        return Arrays.asList(response.getBody());
    }
}
