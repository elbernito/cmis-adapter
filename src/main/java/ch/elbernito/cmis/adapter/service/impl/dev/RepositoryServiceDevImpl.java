package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.RepositoryInfoDto;
import ch.elbernito.cmis.adapter.dto.RepositoryMetaDto;
import ch.elbernito.cmis.adapter.service.RepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * DEV RestTemplate implementation of RepositoryService.
 */
@Slf4j
@Service
public class RepositoryServiceDevImpl implements RepositoryService {

    private final RestTemplate restTemplate;

    @Value("${cmis.dev.environment-tag:DEV}")
    public String environmentTag;

    public RepositoryServiceDevImpl(@Qualifier("devRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public List<RepositoryMetaDto> getAllRepositories() {
        log.info("[{}] Fetching all repositories (GET /repositories)", environmentTag);
        ResponseEntity<RepositoryMetaDto[]> response = restTemplate.getForEntity("/repositories", RepositoryMetaDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public RepositoryMetaDto getRepository(String id) {
        log.info("[{}] Fetching repository {} (GET /repositories/{})", environmentTag, id, id);
        return restTemplate.getForObject("/repositories/" + id, RepositoryMetaDto.class);
    }

    @Override
    public RepositoryInfoDto getRepositoryInfo(String id) {
        log.info("[{}] Fetching repository info {} (GET /repositories/{}/info)", environmentTag, id, id);
        return restTemplate.getForObject("/repositories/" + id + "/info", RepositoryInfoDto.class);
    }
}
