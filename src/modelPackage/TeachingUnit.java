package modelPackage;

import exceptionPackage.*;

public class TeachingUnit {
    private Integer code;
    private String title;
    private StudyYear studyYear;

    public TeachingUnit(Integer code, String title, StudyYear studyYear) throws TeachingUnitCodeNullException, TeachingUnitCodeValueException, TeachingUnitStudyYearNullException, TeachingUnitTitleNullException, TeachingUnitTitleLenghtException {
        setCode(code);
        setTitle(title);
        setStudyYear(studyYear);
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) throws TeachingUnitStudyYearNullException {
        if (studyYear == null) {
            throw new TeachingUnitStudyYearNullException("StudyYear can't be null.");
        }
        this.studyYear = studyYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws TeachingUnitTitleNullException, TeachingUnitTitleLenghtException {
        if (title == null || title.length() > 32) {
            if (title == null) {
                throw new TeachingUnitTitleNullException("Code can't be null.");
            }
            throw new TeachingUnitTitleLenghtException("The size of Label exceed the allocated size in the database");
        }
        this.title = title;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) throws TeachingUnitCodeNullException, TeachingUnitCodeValueException {
        if (code == null || code < 0) {
            if (code == null) {
                throw new TeachingUnitCodeNullException("Code can't be null.");
            }
            throw new TeachingUnitCodeValueException("ID must be positive.");
        }
        this.code = code;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
