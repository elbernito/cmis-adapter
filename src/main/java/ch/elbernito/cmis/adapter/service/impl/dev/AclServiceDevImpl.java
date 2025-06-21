package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.AclDto;
import ch.elbernito.cmis.adapter.service.AclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * DEV RestTemplate implementation of AclService.
 * Forwards all ACL-related requests to the configured DEV CMIS endpoint.
 */
@Slf4j
@Service
public class AclServiceDevImpl implements AclService {

    private final RestTemplate restTemplate;

    @Value("${cmis.dev.environment-tag:DEV}")
    public String environmentTag;

    public AclServiceDevImpl(@Qualifier("devRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public List<AclDto> getAclForObject(String objectId) {
        log.info("[{}] Fetching ACL for object via REST (GET /objects/{}/acl)", environmentTag, objectId);
        ResponseEntity<AclDto[]> response = restTemplate.getForEntity("/objects/" + objectId + "/acl", AclDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public AclDto setAclForObject(String objectId, AclDto aclDto) {
        log.info("[{}] Setting ACL for object via REST (PUT /objects/{}/acl)", environmentTag, objectId);
        restTemplate.put("/objects/" + objectId + "/acl", aclDto);
        return aclDto;
    }
}
