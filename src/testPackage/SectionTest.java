package testPackage;

import exceptionPackage.*;
import modelPackage.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {

    private Section section;

    @BeforeEach
    void setUp() throws SectionLabelLengthException, SectionBuildingNumberNullException, SectionLabelNullException, SectionBuildingNumberValueException {
        section = new Section("A", 1);}

    @Test
    void TestValidUserAccountCreation() {
        assertNotNull(section);
        assertEquals("A", section.getLabel());
        assertEquals(1, section.getBuildingNumber());
    }

    @Test
    void TestSetLabel_Null() {
        assertThrows(SectionLabelNullException.class, () -> section.setLabel(null));
    }

    @Test
    void TestSetLabel_LengthExceeded() {
        assertThrows(SectionLabelLengthException.class, () -> section.setLabel("a".repeat(17)));
    }

    @Test
    void TestSetBuildingNumber_Null() {
        assertThrows(SectionBuildingNumberNullException.class, () -> section.setBuildingNumber(null));
    }

    @Test
    void TestSetBuildingNumber_Negative() {
        assertThrows(SectionBuildingNumberValueException.class, () -> section.setBuildingNumber(-1));
    }
}