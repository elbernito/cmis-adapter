package ch.elbernito.cmis.adapter.config;

import ch.elbernito.cmis.adapter.service.*;
import ch.elbernito.cmis.adapter.service.impl.dev.*;
import ch.elbernito.cmis.adapter.service.impl.prod.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * Configuration class for switching between DEV and PROD service implementations
 * using application properties.
 */
@Slf4j
@Configuration
public class ServiceAdapterConfig {

    @Value("${cmis.stack:dev}")
    private String stack;

    @Value("${cmis.dev.environment-tag:DEV}")
    private String environment_dev;

    @Value("${cmis.prod.environment-tag:PROD}")
    private String environment_prod;

    // RestTemplates für beide Umgebungen
    @Bean
    public RestTemplate devRestTemplate(@Value("${cmis.dev.base-url}") String baseUrl) {
        RestTemplate rt = new RestTemplate();
        rt.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        log.info("DEV RestTemplate initialized with base URL: {}", baseUrl);
        return rt;
    }

    @Bean
    public RestTemplate prodRestTemplate(@Value("${cmis.prod.base-url}") String baseUrl) {
        RestTemplate rt = new RestTemplate();
        rt.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        log.info("PROD RestTemplate initialized with base URL: {}", baseUrl);
        return rt;
    }

    // ---- Umschaltbare Services ----

    @Bean
    @Primary
    public RepositoryService repositoryService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using RepositoryService for environment: {} ", environment_prod);
            return new RepositoryServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using RepositoryService for environment: {} ", environment_dev);
            return new RepositoryServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public ObjectService objectService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using ObjectService for environment: {} ", environment_prod);
            return new ObjectServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using ObjectService for environment: {} ", environment_dev);
            return new ObjectServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public DocumentService documentService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using DocumentService for environment: {} ", environment_prod);
            return new DocumentServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using DocumentService for environment: {} ", environment_dev);
            return new DocumentServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public FolderService folderService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using FolderService for environment: {} ", environment_prod);
            return new FolderServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using FolderService for environment: {} ", environment_dev);
            return new FolderServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public VersionService versionService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using VersionService for environment: {} ", environment_prod);
            return new VersionServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using VersionService for environment: {} ", environment_dev);
            return new VersionServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public MetadataService metadataService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using MetadataService for environment: {} ", environment_prod);
            return new MetadataServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using MetadataService for environment: {} ", environment_dev);
            return new MetadataServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public RelationshipService relationshipService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using RelationshipService for environment: {} ", environment_prod);
            return new RelationshipServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using RelationshipService for environment: {} ", environment_dev);
            return new RelationshipServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public PolicyService policyService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using PolicyService for environment: {} ", environment_prod);
            return new PolicyServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using PolicyService for environment: {} ", environment_dev);
            return new PolicyServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public AclService aclService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using AclService for environment: {} ", environment_prod);
            return new AclServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using AclService for environment: {} ", environment_dev);
            return new AclServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public RetentionService retentionService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using RetentionService for environment: {} ", environment_prod);
            return new RetentionServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using RetentionService for environment: {} ", environment_dev);
            return new RetentionServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public ChangeLogService changeLogService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using ChangeLogService for environment: {} ", environment_prod);
            return new ChangeLogServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using ChangeLogService for environment: {} ", environment_dev);
            return new ChangeLogServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public TypeDefinitionService typeDefinitionService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using TypeDefinitionService for environment: {} ", environment_prod);
            return new TypeDefinitionServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using TypeDefinitionService for environment: {} ", environment_dev);
            return new TypeDefinitionServiceDevImpl(devRestTemplate);
        }
    }

    @Bean
    @Primary
    public AllowableActionsService allowableActionsService(RestTemplate devRestTemplate, RestTemplate prodRestTemplate) {
        if ("prod".equalsIgnoreCase(stack)) {
            log.info("Using AllowableActionsService for environment: {} ", environment_prod);
            return new AllowableActionsServiceProdImpl(prodRestTemplate);
        } else {
            log.info("Using AllowableActionsService for environment: {} ", environment_dev);
            return new AllowableActionsServiceDevImpl(devRestTemplate);
        }
    }
}
