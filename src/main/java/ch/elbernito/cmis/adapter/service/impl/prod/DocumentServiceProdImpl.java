package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.VersionDto;
import ch.elbernito.cmis.adapter.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * PROD RestTemplate implementation of DocumentService.
 * Forwards all document-related requests to the configured PROD CMIS endpoint.
 */
@Slf4j
@Service
public class DocumentServiceProdImpl implements DocumentService {

    private final RestTemplate restTemplate;

    @Value("${cmis.prod.environment-tag:PROD}")
    String environmentTag;

    private final String basePath = "/documents";

    public DocumentServiceProdImpl(@Qualifier("prodRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public DocumentDto createDocument(DocumentDto docDto) {
        log.info("[{}] Creating document via REST (POST {})", environmentTag, basePath);
        return restTemplate.postForObject(basePath, docDto, DocumentDto.class);
    }

    @Override
    public DocumentDto getDocument(String documentId) {
        log.info("[{}] Fetching document via REST (GET {}/{})", environmentTag, basePath, documentId);
        return restTemplate.getForObject(basePath + "/" + documentId, DocumentDto.class);
    }

    @Override
    public DocumentDto updateDocument(String documentId, DocumentDto docDto) {
        log.info("[{}] Updating document via REST (PUT {}/{})", environmentTag, basePath, documentId);
        restTemplate.put(basePath + "/" + documentId, docDto);
        return getDocument(documentId);
    }

    @Override
    public void deleteDocument(String documentId) {
        log.info("[{}] Deleting document via REST (DELETE {}/{})", environmentTag, basePath, documentId);
        restTemplate.delete(basePath + "/" + documentId);
    }

    @Override
    public DocumentDto checkinDocument(String documentId) {
        log.info("[{}] Checking in document via REST (POST {}/{}/checkin)", environmentTag, basePath, documentId);
        return restTemplate.postForObject(basePath + "/" + documentId + "/checkin", null, DocumentDto.class);
    }

    @Override
    public DocumentDto checkoutDocument(String documentId) {
        log.info("[{}] Checking out document via REST (POST {}/{}/checkout)", environmentTag, basePath, documentId);
        return restTemplate.postForObject(basePath + "/" + documentId + "/checkout", null, DocumentDto.class);
    }

    @Override
    public List<VersionDto> getDocumentVersions(String documentId) {
        log.info("[{}] Fetching document versions via REST (GET {}/{}/versions)", environmentTag, basePath, documentId);
        ResponseEntity<VersionDto[]> response = restTemplate.getForEntity(
                basePath + "/" + documentId + "/versions", VersionDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public byte[] getDocumentContent(String documentId) {
        log.info("[{}] Fetching document content via REST (GET {}/{}/content)", environmentTag, basePath, documentId);
        ResponseEntity<byte[]> response = restTemplate.getForEntity(
                basePath + "/" + documentId + "/content", byte[].class);
        return response.getBody();
    }

    @Override
    public void updateDocumentContent(String documentId, byte[] content, String mimeType) {
        log.info("[{}] Updating document content via REST (PUT {}/{}/content)", environmentTag, basePath, documentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType));
        HttpEntity<byte[]> entity = new HttpEntity<>(content, headers);
        restTemplate.put(basePath + "/" + documentId + "/content", entity);
    }
}
