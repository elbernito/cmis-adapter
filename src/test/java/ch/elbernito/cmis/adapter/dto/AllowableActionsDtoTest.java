package ch.elbernito.cmis.adapter.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AllowableActionsDto.
 */
class AllowableActionsDtoTest {

    @Test
    @DisplayName("testWhenUsingBuilderThenValuesAreSetCorrectly")
    void testWhenUsingBuilderThenValuesAreSetCorrectly() {
        AllowableActionsDto dto = AllowableActionsDto.builder()
                .objectId("obj1")
                .canRead(true)
                .canUpdate(false)
                .canDelete(true)
                .canCheckOut(true)
                .canMove(false)
                .canCopy(true)
                .build();

        assertEquals("obj1", dto.getObjectId());
        assertTrue(dto.isCanRead());
        assertFalse(dto.isCanUpdate());
        assertTrue(dto.isCanDelete());
        assertTrue(dto.isCanCheckOut());
        assertFalse(dto.isCanMove());
        assertTrue(dto.isCanCopy());
    }

    @Test
    @DisplayName("testWhenUsingSettersThenValuesAreSetCorrectly")
    void testWhenUsingSettersThenValuesAreSetCorrectly() {
        AllowableActionsDto dto = new AllowableActionsDto();
        dto.setObjectId("obj2");
        dto.setCanRead(false);
        dto.setCanUpdate(true);
        dto.setCanDelete(false);
        dto.setCanCheckOut(false);
        dto.setCanMove(true);
        dto.setCanCopy(false);

        assertEquals("obj2", dto.getObjectId());
        assertFalse(dto.isCanRead());
        assertTrue(dto.isCanUpdate());
        assertFalse(dto.isCanDelete());
        assertFalse(dto.isCanCheckOut());
        assertTrue(dto.isCanMove());
        assertFalse(dto.isCanCopy());
    }

    @Test
    @DisplayName("testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly")
    void testWhenUsingAllArgsConstructorThenValuesAreSetCorrectly() {
        AllowableActionsDto dto = new AllowableActionsDto(
                "obj3", true, false, false, true, true, false
        );
        assertEquals("obj3", dto.getObjectId());
        assertTrue(dto.isCanRead());
        assertFalse(dto.isCanUpdate());
        assertFalse(dto.isCanDelete());
        assertTrue(dto.isCanCheckOut());
        assertTrue(dto.isCanMove());
        assertFalse(dto.isCanCopy());
    }

    @Test
    @DisplayName("testWhenToStringThenContainsFields")
    void testWhenToStringThenContainsFields() {
        AllowableActionsDto dto = AllowableActionsDto.builder()
                .objectId("X")
                .canRead(true)
                .canDelete(true)
                .build();
        String result = dto.toString();
        assertTrue(result.contains("X"));
        assertTrue(result.contains("true"));
    }
}
