package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.ChangeLogDto;
import ch.elbernito.cmis.adapter.service.ChangeLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * DEV RestTemplate implementation of ChangeLogService.
 * Forwards all change log-related requests to the configured DEV CMIS endpoint.
 */
@Slf4j
@Service
public class ChangeLogServiceDevImpl implements ChangeLogService {

    private final RestTemplate restTemplate;

    @Value("${cmis.dev.environment-tag:DEV}")
    public String environmentTag;

    public ChangeLogServiceDevImpl(@Qualifier("devRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ChangeLogDto> getChangeLog() {
        log.info("[{}] Fetching change log via REST (GET /changelog)", environmentTag);
        ResponseEntity<ChangeLogDto[]> response = restTemplate.getForEntity("/changelog", ChangeLogDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<ChangeLogDto> getContentChanges() {
        log.info("[{}] Fetching content changes via REST (GET /changelog/changes)", environmentTag);
        ResponseEntity<ChangeLogDto[]> response = restTemplate.getForEntity("/changelog/changes", ChangeLogDto[].class);
        return Arrays.asList(response.getBody());
    }
}
