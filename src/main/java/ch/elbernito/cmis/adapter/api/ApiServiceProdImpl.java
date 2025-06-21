//// PROD analog zu DEV, einfach alle xxxServiceDev durch xxxServiceProd ersetzen
//package ch.elbernito.cmis.adapter.api;
//
//import ch.elbernito.cmis.adapter.service.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
///**
// * Central API service implementation for PROD environment.
// * Aggregates all PROD service implementations for controller wiring.
// */
//@Service
//@RequiredArgsConstructor
//public class ApiServiceProdImpl {
//
//    public final RepositoryService repositoryService;
//    public final ObjectService objectService;
//    public final DocumentService documentService;
//    public final FolderService folderService;
//    public final VersionService versionService;
//    public final MetadataService metadataService;
//    public final RelationshipService relationshipService;
//    public final PolicyService policyService;
//    public final AclService aclService;
//    public final RetentionService retentionService;
//    public final ChangeLogService changeLogService;
//    public final TypeDefinitionService typeDefinitionService;
//    public final AllowableActionsService allowableActionsService;
//}
