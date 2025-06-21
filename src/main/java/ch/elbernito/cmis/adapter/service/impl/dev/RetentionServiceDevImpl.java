package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.RetentionDto;
import ch.elbernito.cmis.adapter.service.RetentionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * DEV RestTemplate implementation of RetentionService.
 * Forwards all retention-related requests to the configured DEV CMIS endpoint.
 */
@Slf4j
@Service
public class RetentionServiceDevImpl implements RetentionService {

    private final RestTemplate restTemplate;

    @Value("${cmis.dev.environment-tag:DEV}")
    public String environmentTag;

    private final String basePath = "/retentions";

    public RetentionServiceDevImpl(@Qualifier("devRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public RetentionDto createRetention(RetentionDto retentionDto) {
        log.info("[{}] Creating retention via REST (POST {})", environmentTag, basePath);
        return restTemplate.postForObject(basePath, retentionDto, RetentionDto.class);
    }

    @Override
    public RetentionDto getRetention(String retentionId) {
        log.info("[{}] Fetching retention via REST (GET {}/{})", environmentTag, basePath, retentionId);
        return restTemplate.getForObject(basePath + "/" + retentionId, RetentionDto.class);
    }

    @Override
    public void deleteRetention(String retentionId) {
        log.info("[{}] Deleting retention via REST (DELETE {}/{})", environmentTag, basePath, retentionId);
        restTemplate.delete(basePath + "/" + retentionId);
    }

    @Override
    public List<RetentionDto> getRetentionsByObjectId(String objectId) {
        log.info("[{}] Fetching retentions for object via REST (GET /objects/{}/retentions)", environmentTag, objectId);
        ResponseEntity<RetentionDto[]> response = restTemplate.getForEntity("/objects/" + objectId + "/retentions", RetentionDto[].class);
        return Arrays.asList(response.getBody());
    }
}
