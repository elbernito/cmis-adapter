//package ch.elbernito.cmis.adapter.config;
//
//import ch.elbernito.cmis.adapter.service.DocumentService;
//import ch.elbernito.cmis.adapter.service.impl.dev.DocumentServiceDevImpl;
//import ch.elbernito.cmis.adapter.service.impl.prod.DocumentServiceProdImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Integration tests for ServiceAdapterConfig.
// * Ensures correct Bean wiring for DEV and PROD stacks based on configuration.
// */
//class ServiceAdapterConfigTest {
//
//    @Nested
//    @SpringBootTest(classes = ServiceAdapterConfig.class,
//            properties = {
//                    "cmis.adapter.mode=DEV",
//                    "cmis.dev.base-url=http://localhost:8081/dev",
//                    "cmis.prod.base-url=http://localhost:8082/prod"
//            })
//    @DisplayName("When DEV Mode is configured")
//    class DevStack {
//
//        @Autowired
//        ApplicationContext ctx;
//
//        @Test
//        @DisplayName("then DocumentServiceDevImpl is loaded")
//        void testDevBeanLoaded() {
//            DocumentService service = ctx.getBean(DocumentService.class);
//            assertNotNull(service);
//            assertInstanceOf(DocumentServiceDevImpl.class, service, "Should be DEV implementation");
//        }
//    }
//
//    @Nested
//    @SpringBootTest(classes = ServiceAdapterConfig.class,
//            properties = {
//                    "cmis.adapter.mode=PROD",
//                    "cmis.dev.base-url=http://localhost:8081/dev",
//                    "cmis.prod.base-url=http://localhost:8082/prod"
//            })
//    @DisplayName("When PROD Mode is configured")
//    class ProdStack {
//
//        @Autowired
//        ApplicationContext ctx;
//
//        @Test
//        @DisplayName("then DocumentServiceProdImpl is loaded")
//        void testProdBeanLoaded() {
//            DocumentService service = ctx.getBean(DocumentService.class);
//            assertNotNull(service);
//            assertInstanceOf(DocumentServiceProdImpl.class, service, "Should be PROD implementation");
//        }
//    }
//}
