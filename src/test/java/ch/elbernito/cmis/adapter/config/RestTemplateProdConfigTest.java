//package ch.elbernito.cmis.adapter.config;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Unit test for RestTemplateProdConfig.
// * Verifies that the bean is created and uses the correct base URL.
// */
//class RestTemplateProdConfigTest {
//
//    @Test
//    @DisplayName("testWhenBeanCreatedThenUriHandlerUsesBaseUrl")
//    void testWhenBeanCreatedThenUriHandlerUsesBaseUrl() {
//        // Arrange
//        RestTemplateProdConfig config = new RestTemplateProdConfig();
//        ReflectionTestUtils.setField(config, "baseUrl", "https://prod.example.org/api");
//        ReflectionTestUtils.setField(config, "connectTimeout", 3333);
//        ReflectionTestUtils.setField(config, "readTimeout", 4444);
//
//        // Act
//        RestTemplate restTemplate = config.restTemplateProd();
//
//        // Assert
//        assertNotNull(restTemplate);
//        String uri = restTemplate.getUriTemplateHandler().expand("/prodtest").toString();
//        assertTrue(uri.startsWith("https://prod.example.org/api"));
//    }
//}
