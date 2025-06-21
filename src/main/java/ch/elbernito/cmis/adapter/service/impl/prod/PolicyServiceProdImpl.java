package ch.elbernito.cmis.adapter.service.impl.prod;

import ch.elbernito.cmis.adapter.dto.PolicyDto;
import ch.elbernito.cmis.adapter.service.PolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * PROD RestTemplate implementation of PolicyService.
 * Forwards all policy-related requests to the configured PROD CMIS endpoint.
 */
@Slf4j
@Service
public class PolicyServiceProdImpl implements PolicyService {

    private final RestTemplate restTemplate;

    @Value("${cmis.prod.environment-tag:PROD}")
    public String environmentTag;

    private final String basePath = "/policies";

    public PolicyServiceProdImpl(@Qualifier("prodRestTemplate") RestTemplate restTemplate) {
        log.info("{} initialized...", this.getClass().getSimpleName());
        this.restTemplate = restTemplate;
    }

    @Override
    public PolicyDto createPolicy(PolicyDto policyDto) {
        log.info("[{}] Creating policy via REST (POST {})", environmentTag, basePath);
        return restTemplate.postForObject(basePath, policyDto, PolicyDto.class);
    }

    @Override
    public PolicyDto getPolicy(String policyId) {
        log.info("[{}] Fetching policy via REST (GET {}/{})", environmentTag, basePath, policyId);
        return restTemplate.getForObject(basePath + "/" + policyId, PolicyDto.class);
    }

    @Override
    public void deletePolicy(String policyId) {
        log.info("[{}] Deleting policy via REST (DELETE {}/{})", environmentTag, basePath, policyId);
        restTemplate.delete(basePath + "/" + policyId);
    }

    @Override
    public void applyPolicyToObject(String objectId, String policyId) {
        log.info("[{}] Applying policy to object via REST (POST /objects/{}/policies/{}/apply)", environmentTag, objectId, policyId);
        restTemplate.postForObject("/objects/" + objectId + "/policies/" + policyId + "/apply", null, Void.class);
    }

    @Override
    public void removePolicyFromObject(String objectId, String policyId) {
        log.info("[{}] Removing policy from object via REST (POST /objects/{}/policies/{}/remove)", environmentTag, objectId, policyId);
        restTemplate.postForObject("/objects/" + objectId + "/policies/" + policyId + "/remove", null, Void.class);
    }
}
