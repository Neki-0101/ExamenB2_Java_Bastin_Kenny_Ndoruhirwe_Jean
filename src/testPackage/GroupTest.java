package testPackage;

import exceptionPackage.*;
import modelPackage.Group;
import modelPackage.Section;
import modelPackage.StudyYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    private Section section;
    private StudyYear studyYear;
    private Group group;

    @BeforeEach
    void setUp() throws GroupAlphaLetterException, GroupStudyYearNull, SectionLabelLengthException, SectionBuildingNumberNullException, SectionLabelNullException, StudyYearIDValueException, StudyYearIDNullException, StudyYearNumberValueException, StudyYearNumberNullException, StudyYearTypeLengthException, StudyYearTypeNullException, StudyYearOrganizationNullException, GroupIDNullException, GroupIDValueException, SectionBuildingNumberValueException {
        section = new Section("A", 1);
        studyYear = new StudyYear(1, "Bachelor", 1, section);
        group = new Group(1,'A', studyYear);
    }

    @Test
    void testValidGroupCreation() {
        assertNotNull(group);
        assertEquals(1, group.getIdGroup());
        assertEquals('A', group.getLetter());
        assertNotNull(group.getStudyYear());
    }

    @Test
    void TestSetIdGroup_Null() {
        assertThrows(GroupIDNullException.class, () -> group.setIdGroup(null));
    }

    @Test
    void TestSetIdGroup_Negative() {
        assertThrows(GroupIDValueException.class, () -> group.setIdGroup(-1));
    }

    @Test
    void TestSetLetter_Alpha() {
        assertThrows(GroupAlphaLetterException.class, () -> group.setLetter('1'));
    }

    @Test
    void TestSetStudyYear_Null() {
        assertThrows(GroupStudyYearNull.class, () -> group.setStudyYear(null));
    }
}