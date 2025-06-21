//package ch.elbernito.cmis.adapter.api;
//
//import ch.elbernito.cmis.adapter.service.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
///**
// * Unit tests for ApiServiceProdImpl.
// * Verifies correct aggregation and wiring of PROD service beans.
// */
//class ApiServiceProdImplTest {
//
//    private RepositoryService repositoryService;
//    private ObjectService objectService;
//    private DocumentService documentService;
//    private FolderService folderService;
//    private VersionService versionService;
//    private MetadataService metadataService;
//    private RelationshipService relationshipService;
//    private PolicyService policyService;
//    private AclService aclService;
//    private RetentionService retentionService;
//    private ChangeLogService changeLogService;
//    private TypeDefinitionService typeDefinitionService;
//    private AllowableActionsService allowableActionsService;
//
//    private ApiServiceProdImpl apiServiceProd;
//
//    @BeforeEach
//    void setUp() {
//        repositoryService = mock(RepositoryService.class);
//        objectService = mock(ObjectService.class);
//        documentService = mock(DocumentService.class);
//        folderService = mock(FolderService.class);
//        versionService = mock(VersionService.class);
//        metadataService = mock(MetadataService.class);
//        relationshipService = mock(RelationshipService.class);
//        policyService = mock(PolicyService.class);
//        aclService = mock(AclService.class);
//        retentionService = mock(RetentionService.class);
//        changeLogService = mock(ChangeLogService.class);
//        typeDefinitionService = mock(TypeDefinitionService.class);
//        allowableActionsService = mock(AllowableActionsService.class);
//
//        apiServiceProd = new ApiServiceProdImpl(
//                repositoryService,
//                objectService,
//                documentService,
//                folderService,
//                versionService,
//                metadataService,
//                relationshipService,
//                policyService,
//                aclService,
//                retentionService,
//                changeLogService,
//                typeDefinitionService,
//                allowableActionsService
//        );
//    }
//
//    /**
//     * Tests that all PROD service beans are set and available.
//     */
//    @Test
//    @DisplayName("testWhenConstructedThenAllServicesAreWired")
//    void testWhenConstructedThenAllServicesAreWired() {
//        assertSame(repositoryService, apiServiceProd.repositoryService);
//        assertSame(objectService, apiServiceProd.objectService);
//        assertSame(documentService, apiServiceProd.documentService);
//        assertSame(folderService, apiServiceProd.folderService);
//        assertSame(versionService, apiServiceProd.versionService);
//        assertSame(metadataService, apiServiceProd.metadataService);
//        assertSame(relationshipService, apiServiceProd.relationshipService);
//        assertSame(policyService, apiServiceProd.policyService);
//        assertSame(aclService, apiServiceProd.aclService);
//        assertSame(retentionService, apiServiceProd.retentionService);
//        assertSame(changeLogService, apiServiceProd.changeLogService);
//        assertSame(typeDefinitionService, apiServiceProd.typeDefinitionService);
//        assertSame(allowableActionsService, apiServiceProd.allowableActionsService);
//    }
//}
