package modelPackage;

import exceptionPackage.*;

public class StudyYear {
    private Integer idStudyYear;
    private String type;
    private Integer number;
    private Section organization;

    public StudyYear(Integer idStudyYear, String type, Integer number, Section organization) throws StudyYearIDValueException, StudyYearIDNullException, StudyYearTypeLengthException, StudyYearTypeNullException, StudyYearOrganizationNullException, StudyYearNumberValueException, StudyYearNumberNullException {
        setIdStudyYear(idStudyYear);
        setType(type);
        setNumber(number);
        setOrganization(organization);
    }

    public StudyYear(int idStudyYear) throws StudyYearIDValueException, StudyYearIDNullException {
        setIdStudyYear(idStudyYear);
    }

    public int getIdStudyYear() {
        return idStudyYear;
    }

    public void setIdStudyYear(Integer idStudyYear) throws StudyYearIDValueException, StudyYearIDNullException {
        if (idStudyYear == null || idStudyYear < 0) {
            if (idStudyYear == null) {
                throw new StudyYearIDNullException("ID can't be null.");
            }
            throw new StudyYearIDValueException("ID must be positive");
        }
        this.idStudyYear = idStudyYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws StudyYearTypeLengthException, StudyYearTypeNullException {
        if (type == null || type.length() > 10) {
            if (type == null) {
                throw new StudyYearTypeNullException("The Type can't be null");
            }
            throw new StudyYearTypeLengthException("The size of Type exceed the allocated size in the database");
        }
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(Integer number) throws StudyYearNumberNullException, StudyYearNumberValueException {
        if (number == null || (number < 1 || number > 5)) {
            if (number == null) {
                throw new StudyYearNumberNullException("The number can't be null");
            }
            throw new StudyYearNumberValueException("The Study Year Number need to be between 1 and 5");
        }
        this.number = number;
    }

    public Section getOrganization() {
        return organization;
    }

    public void setOrganization(Section organization) throws StudyYearOrganizationNullException {
        if (organization == null) {
            throw new StudyYearOrganizationNullException("The Organization can't be null");
        }
        this.organization = organization;
    }
}
