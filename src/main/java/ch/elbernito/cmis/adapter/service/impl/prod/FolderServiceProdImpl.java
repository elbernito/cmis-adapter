package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.FolderDto;
import ch.elbernito.cmis.adapter.dto.ObjectDto;
import ch.elbernito.cmis.adapter.service.FolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * PROD RestTemplate implementation of FolderService.
 * Forwards all folder-related requests to the configured PROD CMIS endpoint.
 */
@Slf4j
@Service
public class FolderServiceProdImpl implements FolderService {

    private final RestTemplate restTemplate;

    @Value("${cmis.prod.environment-tag:PROD}")
    public String environmentTag;

    private final String basePath = "/folders";

    public FolderServiceProdImpl(@Qualifier("prodRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public FolderDto createFolder(FolderDto folderDto) {
        log.info("[{}] Creating folder via REST (POST {})", environmentTag, basePath);
        return restTemplate.postForObject(basePath, folderDto, FolderDto.class);
    }

    @Override
    public FolderDto getFolder(String folderId) {
        log.info("[{}] Fetching folder via REST (GET {}/{})", environmentTag, basePath, folderId);
        return restTemplate.getForObject(basePath + "/" + folderId, FolderDto.class);
    }

    @Override
    public FolderDto updateFolder(String folderId, FolderDto folderDto) {
        log.info("[{}] Updating folder via REST (PUT {}/{})", environmentTag, basePath, folderId);
        restTemplate.put(basePath + "/" + folderId, folderDto);
        return getFolder(folderId);
    }

    @Override
    public void deleteFolder(String folderId) {
        log.info("[{}] Deleting folder via REST (DELETE {}/{})", environmentTag, basePath, folderId);
        restTemplate.delete(basePath + "/" + folderId);
    }

    @Override
    public List<ObjectDto> getFolderChildren(String folderId) {
        log.info("[{}] Fetching folder children via REST (GET {}/{}/children)", environmentTag, basePath, folderId);
        ResponseEntity<ObjectDto[]> response = restTemplate.getForEntity(basePath + "/" + folderId + "/children", ObjectDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<ObjectDto> getFolderDescendants(String folderId) {
        log.info("[{}] Fetching folder descendants via REST (GET {}/{}/descendants)", environmentTag, basePath, folderId);
        ResponseEntity<ObjectDto[]> response = restTemplate.getForEntity(basePath + "/" + folderId + "/descendants", ObjectDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public FolderDto getFolderParent(String folderId) {
        log.info("[{}] Fetching parent folder via REST (GET {}/{}/parent)", environmentTag, basePath, folderId);
        return restTemplate.getForObject(basePath + "/" + folderId + "/parent", FolderDto.class);
    }

    @Override
    public void deleteFolderTree(String folderId) {
        log.info("[{}] Deleting folder tree via REST (DELETE {}/{}/tree)", environmentTag, basePath, folderId);
        restTemplate.delete(basePath + "/" + folderId + "/tree");
    }

    @Override
    public List<DocumentDto> getCheckedOutDocs(String folderId) {
        log.info("[{}] Fetching checked out docs via REST (GET {}/{}/checkedout)", environmentTag, basePath, folderId);
        ResponseEntity<DocumentDto[]> response = restTemplate.getForEntity(basePath + "/" + folderId + "/checkedout", DocumentDto[].class);
        return Arrays.asList(response.getBody());
    }
}
