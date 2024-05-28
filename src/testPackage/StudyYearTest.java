package testPackage;

import exceptionPackage.*;
import modelPackage.Section;
import modelPackage.StudyYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyYearTest {
    private StudyYear studyYear;
    private Section section;

    @BeforeEach
    void setUp() throws SectionLabelLengthException, SectionBuildingNumberNullException, SectionBuildingNumberValueException, SectionLabelNullException, StudyYearIDValueException, StudyYearIDNullException, StudyYearNumberValueException, StudyYearNumberNullException, StudyYearTypeLengthException, StudyYearTypeNullException, StudyYearOrganizationNullException {
        section = new Section("A", 1);
        studyYear = new StudyYear(1,"Bachelor", 1, section);
    }

    @Test
    void TestValidStudyYearCreation() {
        assertNotNull(studyYear);
        assertEquals(1, studyYear.getIdStudyYear());
        assertEquals(1, studyYear.getNumber());
        assertEquals(section, studyYear.getOrganization());
        assertEquals("Bachelor", studyYear.getType());
    }

    @Test
    void TestSetIdStudyYear_Null() {
        assertThrows(StudyYearIDNullException.class, () -> studyYear.setIdStudyYear(null));
    }

    @Test
    void TestSetIdStudyYear_Negative() {
        assertThrows(StudyYearIDValueException.class, () -> studyYear.setIdStudyYear(-1));
    }

    @Test
    void TestSetType_Null() {
        assertThrows(StudyYearTypeNullException.class, () -> studyYear.setType(null));
    }

    @Test
    void TestSetIdStudyYear_LengthExceeded() {
        assertThrows(StudyYearTypeLengthException.class, () -> studyYear.setType("a".repeat(11)));
    }

    @Test
    void TestSetOrganization_Null() {
        assertThrows(StudyYearOrganizationNullException.class, () -> studyYear.setOrganization(null));
    }

    @Test
    void TestSetNumber_Null() {
        assertThrows(StudyYearNumberNullException.class, () -> studyYear.setNumber(null));
    }

    @Test
    void TestSetNumber_Value() {
        assertThrows(StudyYearNumberValueException.class, () -> studyYear.setNumber(0));
        assertThrows(StudyYearNumberValueException.class, () -> studyYear.setNumber(6));
    }
}