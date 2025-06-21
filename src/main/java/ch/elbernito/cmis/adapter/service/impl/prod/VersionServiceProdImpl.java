package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.VersionDto;
import ch.elbernito.cmis.adapter.service.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * PROD RestTemplate implementation of VersionService.
 * Forwards all version-related requests to the configured PROD CMIS endpoint.
 */
@Slf4j
@Service
public class VersionServiceProdImpl implements VersionService {

    private final RestTemplate restTemplate;

    @Value("${cmis.prod.environment-tag:PROD}")
    public String environmentTag;

    private final String basePath = "/versions";

    public VersionServiceProdImpl(@Qualifier("prodRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public VersionDto getVersion(String versionId) {
        log.info("[{}] Fetching version via REST (GET {}/{})", environmentTag, basePath, versionId);
        return restTemplate.getForObject(basePath + "/" + versionId, VersionDto.class);
    }

    @Override
    public List<VersionDto> getVersionsForDocument(String documentId) {
        log.info("[{}] Fetching all versions for document via REST (GET /documents/{}/all-versions)", environmentTag, documentId);
        ResponseEntity<VersionDto[]> response = restTemplate.getForEntity("/documents/" + documentId + "/all-versions", VersionDto[].class);
        return Arrays.asList(response.getBody());
    }
}
