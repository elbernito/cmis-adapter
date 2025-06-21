package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for RepositoryInfoDto.
 * Verifies builder, constructors, getters, setters and toString.
 */
class RepositoryInfoDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        RepositoryInfoDto dto = RepositoryInfoDto.builder()
                .id("repo1")
                .vendorName("Acme Inc.")
                .productName("CMIS Adapter")
                .productVersion("1.0.0")
                .cmisVersionSupported("1.2")
                .description("CMIS Test Repo")
                .build();

        assertEquals("repo1", dto.getId());
        assertEquals("Acme Inc.", dto.getVendorName());
        assertEquals("CMIS Adapter", dto.getProductName());
        assertEquals("1.0.0", dto.getProductVersion());
        assertEquals("1.2", dto.getCmisVersionSupported());
        assertEquals("CMIS Test Repo", dto.getDescription());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        RepositoryInfoDto dto = new RepositoryInfoDto();
        dto.setId("id2");
        dto.setVendorName("Vendor2");
        dto.setProductName("Prod2");
        dto.setProductVersion("2.0.0");
        dto.setCmisVersionSupported("1.1");
        dto.setDescription("Desc2");

        assertEquals("id2", dto.getId());
        assertEquals("Vendor2", dto.getVendorName());
        assertEquals("Prod2", dto.getProductName());
        assertEquals("2.0.0", dto.getProductVersion());
        assertEquals("1.1", dto.getCmisVersionSupported());
        assertEquals("Desc2", dto.getDescription());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        RepositoryInfoDto dto = new RepositoryInfoDto(
                "id3", "Vendor3", "Prod3", "3.3.3", "1.0", "Desc3");

        assertEquals("id3", dto.getId());
        assertEquals("Vendor3", dto.getVendorName());
        assertEquals("Prod3", dto.getProductName());
        assertEquals("3.3.3", dto.getProductVersion());
        assertEquals("1.0", dto.getCmisVersionSupported());
        assertEquals("Desc3", dto.getDescription());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        RepositoryInfoDto dto = RepositoryInfoDto.builder()
                .id("abc")
                .vendorName("Ven")
                .description("DescText")
                .build();

        String str = dto.toString();
        assertTrue(str.contains("abc"));
        assertTrue(str.contains("Ven"));
        assertTrue(str.contains("DescText"));
    }
}
