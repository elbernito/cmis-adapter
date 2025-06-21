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
// * Unit tests for ApiServiceDevImpl.
// * Verifies correct aggregation and wiring of DEV service beans.
// */
//class ApiServiceDevImplTest {
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
//    private ApiServiceDevImpl apiServiceDev;
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
//        apiServiceDev = new ApiServiceDevImpl(
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
//     * Tests that all DEV service beans are set and available.
//     */
//    @Test
//    @DisplayName("testWhenConstructedThenAllServicesAreWired")
//    void testWhenConstructedThenAllServicesAreWired() {
//        assertSame(repositoryService, apiServiceDev.repositoryService);
//        assertSame(objectService, apiServiceDev.objectService);
//        assertSame(documentService, apiServiceDev.documentService);
//        assertSame(folderService, apiServiceDev.folderService);
//        assertSame(versionService, apiServiceDev.versionService);
//        assertSame(metadataService, apiServiceDev.metadataService);
//        assertSame(relationshipService, apiServiceDev.relationshipService);
//        assertSame(policyService, apiServiceDev.policyService);
//        assertSame(aclService, apiServiceDev.aclService);
//        assertSame(retentionService, apiServiceDev.retentionService);
//        assertSame(changeLogService, apiServiceDev.changeLogService);
//        assertSame(typeDefinitionService, apiServiceDev.typeDefinitionService);
//        assertSame(allowableActionsService, apiServiceDev.allowableActionsService);
//    }
//}
