package modelPackage;

import exceptionPackage.*;

import java.util.Date;

public class Professor extends UserAccount {
    private String grade;

    public Professor(Integer loginID, String login, Character gender, String firstName, String secondFirstName, String lastName, String boxNumber, String password, Date birthDate, City city, boolean isActive, String grade) throws UserAccountActiveNullException, UserAccountFirstNameLenghtException, UserAccountLoginNullException, UserAccountBirthDateNullException, UserAccountLastNameLenghtException, UserAccountPasswordNullException, UserAccountFirstNameNullException, UserAccountCityNullException, UserAccountGenderException, UserAccountsecondFirstNameLenghtException, UserAccountBoxLenghtException, UserAccountLoginLenghtException, UserAccountLastNameNullException, UserAccountGenderNullException, UserAccountLoginIDNullException, UserAccountPasswordLenghtException, UserAccountBirthDateTimeException, ProfessorGradeLenghtException, ProfessorGradeNullException {
        super(loginID, login, gender, firstName, secondFirstName, lastName, boxNumber, password, birthDate, city, isActive);
        setGrade(grade);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) throws ProfessorGradeNullException, ProfessorGradeLenghtException {
        if (grade == null || grade.length() > 20){
            if (grade == null){
                throw new ProfessorGradeNullException("Grade can't be null.");
            }
            throw new ProfessorGradeLenghtException("The size of Grade exceed the allocated size in the database");
        }
        this.grade = grade;
    }
}
