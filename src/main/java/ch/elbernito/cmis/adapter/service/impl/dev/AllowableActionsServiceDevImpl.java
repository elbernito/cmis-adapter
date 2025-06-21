package ch.elbernito.cmis.adapter.service.impl.dev;

import ch.elbernito.cmis.adapter.dto.AllowableActionsDto;
import ch.elbernito.cmis.adapter.service.AllowableActionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * DEV RestTemplate implementation of AllowableActionsService.
 * Forwards allowable actions requests to the configured DEV CMIS endpoint.
 */
@Slf4j
@Service
public class AllowableActionsServiceDevImpl implements AllowableActionsService {

    private final RestTemplate restTemplate;

    @Value("${cmis.dev.environment-tag:DEV}")
    public String environmentTag;

    public AllowableActionsServiceDevImpl(@Qualifier("devRestTemplate")RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public AllowableActionsDto getAllowableActions(String objectId) {
        log.info("[{}] Fetching allowable actions via REST (GET /objects/{}/allowable-actions)", environmentTag, objectId);
        return restTemplate.getForObject("/objects/" + objectId + "/allowable-actions", AllowableActionsDto.class);
    }
}
