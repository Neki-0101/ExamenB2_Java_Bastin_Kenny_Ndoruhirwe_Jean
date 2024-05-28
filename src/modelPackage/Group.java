//Model finished
package modelPackage;

import exceptionPackage.*;

public class Group {
    private Integer idGroup;
    private Character letter;
    private StudyYear studyYear;

    public Group(Integer idGroup, Character letter, StudyYear studyYear) throws GroupAlphaLetterException, GroupStudyYearNull, GroupIDNullException, GroupIDValueException {
        setIdGroup(idGroup);
        setLetter(letter);
        setStudyYear(studyYear);
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public Character getLetter() {
        return letter;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setIdGroup(Integer idGroup) throws GroupIDNullException, GroupIDValueException {
        if (idGroup == null || idGroup <= 0 ) {
            if (idGroup == null) {
                throw new GroupIDNullException("L'Id dois etre non null");
            }
            throw new GroupIDValueException("L'Id dois etre positif");
        }
        this.idGroup = idGroup;
    }

    public void setLetter(Character letter) throws GroupAlphaLetterException {
        if (Character.isAlphabetic(letter)) {
            this.letter = letter;
        } else {
            throw new GroupAlphaLetterException("La lettre dois etre un chacarter alphabetique");
        }
    }

    public void setStudyYear(StudyYear studyYear) throws GroupStudyYearNull {
        if (studyYear != null) {
            this.studyYear = studyYear;
        } else {
            throw new GroupStudyYearNull("Les année d'etude ne peux pas etre null");
        }
    }
}
