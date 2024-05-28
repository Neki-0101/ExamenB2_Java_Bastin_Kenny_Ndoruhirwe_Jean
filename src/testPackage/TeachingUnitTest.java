package testPackage;

import exceptionPackage.*;
import modelPackage.Section;
import modelPackage.StudyYear;
import modelPackage.TeachingUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeachingUnitTest {
    private Section section;
    private StudyYear studyYear;
    private TeachingUnit teachingUnit;

    @BeforeEach
    void setUp() throws CityPostalNullException, CityPostalFormatException, CityNameLengthException, CityNameNullException, CityIDNullException, CityIDException, UserAccountActiveNullException, UserAccountFirstNameLenghtException, UserAccountLoginNullException, UserAccountGenderNullException, UserAccountBirthDateNullException, UserAccountLastNameLenghtException, UserAccountBirthDateTimeException, UserAccountPasswordNullException, UserAccountFirstNameNullException, UserAccountCityNullException, UserAccountLoginIDNullException, UserAccountGenderException, UserAccountsecondFirstNameLenghtException, UserAccountBoxLenghtException, UserAccountPasswordLenghtException, UserAccountLoginLenghtException, UserAccountLastNameNullException, StudentRegistrationYearNullException, StudentRegistrationYearValueException, TeachingUnitStudyYearNullException, TeachingUnitTitleNullException, TeachingUnitCodeNullException, TeachingUnitTitleLenghtException, TeachingUnitCodeValueException, SectionLabelLengthException, SectionBuildingNumberNullException, SectionBuildingNumberValueException, SectionLabelNullException, StudyYearIDNullException, StudyYearNumberNullException, StudyYearNumberValueException, StudyYearIDValueException, StudyYearTypeLengthException, StudyYearTypeNullException, StudyYearOrganizationNullException {
        section = new Section("A", 1);
        studyYear = new StudyYear(1,"Bachelor", 1, section);
        teachingUnit = new TeachingUnit(1, "Mathematics", studyYear);
    }

    @Test
    void TestValidStudentCreation() {
        assertNotNull(teachingUnit);
        assertEquals("Mathematics", teachingUnit.getTitle());
        assertEquals(1, teachingUnit.getCode());
        assertEquals(studyYear, teachingUnit.getStudyYear());
    }

    @Test
    void TestSetStudyYear_Null() {
        assertThrows(TeachingUnitStudyYearNullException.class, () -> teachingUnit.setStudyYear(null));
    }

    @Test
    void TestSetTitle_Null() {
        assertThrows(TeachingUnitTitleNullException.class, () -> teachingUnit.setTitle(null));
    }
    @Test
    void TestSetTitle_Exceeded() {
        assertThrows(TeachingUnitTitleLenghtException.class, () -> teachingUnit.setTitle("a".repeat(33)));
    }

    @Test
    void TestSetCode_Null() {
        assertThrows(TeachingUnitCodeNullException.class, () -> teachingUnit.setCode(null));
    }
    @Test
    void TestSetCode_Negative() {
        assertThrows(TeachingUnitCodeValueException.class, () -> teachingUnit.setCode(-1));
    }
}