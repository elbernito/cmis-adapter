package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.MetadataDto;
import ch.elbernito.cmis.adapter.service.MetadataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * DEV RestTemplate implementation of MetadataService.
 * Forwards all metadata-related requests to the configured DEV CMIS endpoint.
 */
@Slf4j
@Service
public class MetadataServiceDevImpl implements MetadataService {

    private final RestTemplate restTemplate;

    @Value("${cmis.dev.environment-tag:DEV}")
    public String environmentTag;

    private final String basePath = "/metadata";

    public MetadataServiceDevImpl(@Qualifier("devRestTemplate")  RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public MetadataDto createMetadata(MetadataDto metadataDto) {
        log.info("[{}] Creating metadata via REST (POST {})", environmentTag, basePath);
        return restTemplate.postForObject(basePath, metadataDto, MetadataDto.class);
    }

    @Override
    public MetadataDto getMetadata(String metadataId) {
        log.info("[{}] Fetching metadata via REST (GET {}/{})", environmentTag, basePath, metadataId);
        return restTemplate.getForObject(basePath + "/" + metadataId, MetadataDto.class);
    }

    @Override
    public MetadataDto updateMetadata(String metadataId, MetadataDto metadataDto) {
        log.info("[{}] Updating metadata via REST (PUT {}/{})", environmentTag, basePath, metadataId);
        restTemplate.put(basePath + "/" + metadataId, metadataDto);
        return getMetadata(metadataId);
    }

    @Override
    public void deleteMetadata(String metadataId) {
        log.info("[{}] Deleting metadata via REST (DELETE {}/{})", environmentTag, basePath, metadataId);
        restTemplate.delete(basePath + "/" + metadataId);
    }

    @Override
    public List<MetadataDto> getMetadataByDocumentId(String documentId) {
        log.info("[{}] Fetching all metadata for document via REST (GET /documents/{}/metadata)", environmentTag, documentId);
        ResponseEntity<MetadataDto[]> response = restTemplate.getForEntity("/documents/" + documentId + "/metadata", MetadataDto[].class);
        return Arrays.asList(response.getBody());
    }
}
