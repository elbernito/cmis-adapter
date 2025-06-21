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
// * Unit test for RestTemplateDevConfig.
// * Verifies that the bean is created and uses the correct base URL.
// */
//class RestTemplateDevConfigTest {
//
//    @Test
//    @DisplayName("testWhenBeanCreatedThenUriHandlerUsesBaseUrl")
//    void testWhenBeanCreatedThenUriHandlerUsesBaseUrl() {
//        // Arrange
//        RestTemplateDevConfig config = new RestTemplateDevConfig();
//        ReflectionTestUtils.setField(config, "baseUrl", "http://localhost:1234/api");
//        ReflectionTestUtils.setField(config, "connectTimeout", 1111);
//        ReflectionTestUtils.setField(config, "readTimeout", 2222);
//
//        // Act
//        RestTemplate restTemplate = config.restTemplateDev();
//
//        // Assert
//        assertNotNull(restTemplate);
//        String uri = restTemplate.getUriTemplateHandler().expand("/abc").toString();
//        assertTrue(uri.startsWith("http://localhost:1234/api"));
//    }
//}
