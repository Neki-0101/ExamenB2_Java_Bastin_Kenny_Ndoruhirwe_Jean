package testPackage;

import exceptionPackage.*;
import modelPackage.LearningActivity;
import modelPackage.Section;
import modelPackage.StudyYear;
import modelPackage.TeachingUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LearningActivityTest {

    private Section section;
    private StudyYear studyYear;
    private TeachingUnit teachingUnit;
    private LearningActivity learningActivity;

    @BeforeEach
    void setUp() throws SectionLabelLengthException, SectionBuildingNumberNullException, SectionLabelNullException, StudyYearIDValueException, StudyYearIDNullException, StudyYearNumberValueException, StudyYearNumberNullException, StudyYearTypeLengthException, StudyYearTypeNullException, StudyYearOrganizationNullException, TeachingUnitStudyYearNullException, TeachingUnitTitleNullException, TeachingUnitCodeNullException, TeachingUnitTitleLenghtException, TeachingUnitCodeValueException, LearningActivityCodeNullException, LearningActivityUnitNullException, LearningActivityCreditValueException, LearningActivityHourNullException, LearningActivityTitleLenghtException, LearningActivityCodeValueException, LearningActivityCreditNullException, LearningActivityHourValueException, LearningActivityTitleNullException, SectionBuildingNumberValueException {
        section = new Section("A", 1);
        studyYear = new StudyYear(1, "Bachelor", 1, section);
        teachingUnit = new TeachingUnit(1, "Mathematics", studyYear);
        learningActivity = new LearningActivity(1,"ActivityTest", 1, 1, teachingUnit);
    }

    @Test
    void testValidLearningActivityCreation() {
        assertNotNull(learningActivity);
        assertEquals(1, learningActivity.getActivityCode());
        assertEquals("ActivityTest", learningActivity.getActivityTitle());
        assertEquals(1, learningActivity.getHoursNb());
        assertEquals(1, learningActivity.getCreditsNb());
        assertEquals(teachingUnit, learningActivity.getUnit());
    }

    @Test
    void TestSetActivityCode_Null() {
        assertThrows(LearningActivityCodeNullException.class, () -> learningActivity.setActivityCode(null));
    }

    @Test
    void TestSetActivityCode_Negative() {
        assertThrows(LearningActivityCodeValueException.class, () -> learningActivity.setActivityCode(-1));
    }

    @Test
    void TestSetActivityTitle_Null() {
        assertThrows(LearningActivityTitleNullException.class, () -> learningActivity.setActivityTitle(null));
    }

    @Test
    void TestSetActivityTitle_LengthExceeded() {
        assertThrows(LearningActivityTitleLenghtException.class, () -> learningActivity.setActivityTitle("a".repeat(33)));
    }

    @Test
    void TestSetCreditsNb_Null() {
        assertThrows(LearningActivityCreditNullException.class, () -> learningActivity.setCreditsNb(null));
    }

    @Test
    void TestSetCreditsNb_Value() {
        assertThrows(LearningActivityCreditValueException.class, () -> learningActivity.setCreditsNb(-1));
        assertThrows(LearningActivityCreditValueException.class, () -> learningActivity.setCreditsNb(61));
    }

    @Test
    void TestSetHourNb_Null() {
        assertThrows(LearningActivityHourNullException.class, () -> learningActivity.setHoursNb(null));
    }

    @Test
    void TestSetHourNb_Value() {
        assertThrows(LearningActivityHourValueException.class, () -> learningActivity.setHoursNb(-1));
    }

    @Test
    void TestSetUnit_Null() {
        assertThrows(LearningActivityUnitNullException.class, () -> learningActivity.setUnit(null));
    }
}