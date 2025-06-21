//package ch.elbernito.cmis.adapter.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.hc.client5.http.config.RequestConfig;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.core5.util.Timeout;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//@Slf4j
//@Configuration
//public class RestTemplateDevConfig {
//
//    @Value("${cmis.dev.base-url}")
//    private String baseUrl;
//
//    @Value("${cmis.dev.connect-timeout-ms:2000}")
//    private int connectTimeout;
//
//    @Value("${cmis.dev.read-timeout-ms:5000}")
//    private int readTimeout;
//
//    @Bean(name = "restTemplateDev")
//    public RestTemplate restTemplateDev() {
//
//        log.info("Creating rest template dev");
//
//        /**
//         * Creates and configures the RestTemplate for the PROD environment
//         * using Apache HttpClient for timeout support.
//         *
//         * TODO (Juni 2025): Die Timeout-Konfiguration ist in HttpClient5 ab 5.2 als deprecated markiert,
//         * weil mittelfristig ein neues API kommen wird. Bislang gibt es KEINE Alternative für RestTemplate+HttpClient5+Timeouts.
//         * Sobald Apache/Spring ein neues Pattern veröffentlichen, bitte auf das neue umstellen.
//         * Siehe: https://github.com/spring-projects/spring-framework/issues/32047
//         *
//         * @return a configured RestTemplate bean for DEV
//         */
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(Timeout.ofMilliseconds(connectTimeout))    // deprecated, aber nötig!
//                .setResponseTimeout(Timeout.ofMilliseconds(readTimeout))
//                .build();
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setDefaultRequestConfig(requestConfig)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        RestTemplate restTemplate = new RestTemplate(factory);
//        restTemplate.setUriTemplateHandler(new org.springframework.web.util.DefaultUriBuilderFactory(baseUrl));
//        return restTemplate;
//    }
//}
