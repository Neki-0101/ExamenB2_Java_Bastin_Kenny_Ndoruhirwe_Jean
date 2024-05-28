package modelPackage;

import exceptionPackage.*;

import java.util.Date;

public class Student extends UserAccount {
    private Integer registrationYear;

    public Student(Integer loginID, String login, Character gender, String firstName, String secondFirstName, String lastName, String boxNumber, String password, Date birthDate, City city, boolean isActive, Integer registrationYear) throws UserAccountLoginNullException, UserAccountLoginLenghtException, UserAccountGenderException, UserAccountFirstNameLenghtException, UserAccountFirstNameNullException, UserAccountsecondFirstNameLenghtException, UserAccountLastNameNullException, UserAccountLastNameLenghtException, UserAccountBoxLenghtException, UserAccountPasswordNullException, UserAccountBirthDateNullException, UserAccountCityNullException, UserAccountActiveNullException, UserAccountGenderNullException, UserAccountLoginIDNullException, UserAccountPasswordLenghtException, UserAccountBirthDateTimeException, StudentRegistrationYearNullException, StudentRegistrationYearValueException {
        super(loginID, login, gender, firstName, secondFirstName, lastName, boxNumber, password, birthDate, city, isActive);
        setRegistrationYear(registrationYear);
    }

    public Integer getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(Integer registrationYear) throws StudentRegistrationYearNullException, StudentRegistrationYearValueException {
        if (registrationYear == null || registrationYear < 1950){
            if (registrationYear == null){
                throw new StudentRegistrationYearNullException("Student registration year can't be null");
            }
            throw new StudentRegistrationYearValueException("Student registration year must be greater than 1950");
        }
        this.registrationYear = registrationYear;
    }
}
