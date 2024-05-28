package modelPackage;

import exceptionPackage.*;

import java.util.Date;
import java.util.HashSet;

public class UserAccount {
    private Integer loginID;
    private String login;
    private Character gender;
    private String firstName;
    private String secondFirstName;
    private String lastName;
    private String boxNumber;
    private String password;
    private java.sql.Date birthDate;
    private City city;
    private boolean isActive;
    private HashSet<Role> roles;

    public UserAccount(Integer loginID, String login, Character gender, String firstName, String secondFirstName, String lastName, String boxNumber, String password, Date birthDate, City city, boolean isActive) throws UserAccountLoginNullException, UserAccountLoginLenghtException, UserAccountGenderException, UserAccountFirstNameLenghtException, UserAccountFirstNameNullException, UserAccountsecondFirstNameLenghtException, UserAccountLastNameNullException, UserAccountLastNameLenghtException, UserAccountBoxLenghtException, UserAccountPasswordNullException, UserAccountBirthDateNullException, UserAccountCityNullException, UserAccountActiveNullException, UserAccountGenderNullException, UserAccountLoginIDNullException, UserAccountPasswordLenghtException, UserAccountBirthDateTimeException {
        setLoginID(loginID);
        setLogin(login);
        setGender(gender);
        setFirstName(firstName);
        setSecondFirstName(secondFirstName);
        setLastName(lastName);
        setBoxNumber(boxNumber);
        setPassword(password);
        setBirthDate(birthDate);
        setCity(city);
        setActive(isActive);
        this.roles = new HashSet<>();
    }

    public UserAccount() {
    }

    public void setLoginID(Integer loginID) throws UserAccountLoginIDNullException {
        if (loginID == null) {
            throw new UserAccountLoginIDNullException("Login ID can't be null.");
        }
        this.loginID = loginID;
    }

    public Integer getLoginID() {
        return loginID;
    }

    public HashSet<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        if (role != null && !roles.contains(role)) {
            roles.add(role);
            if (!role.getUserAccounts().contains(this)) {
                role.addUser(this);
            }
        }
    }

    public void removeRole(Role role) {
        if (role != null && roles.contains(role)) {
            roles.remove(role);
            if (role.getUserAccounts().contains(this)) {
                role.removeUser(this);
            }
        }
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws UserAccountLoginNullException, UserAccountLoginLenghtException {
        if (login == null || login.length() > 20) {
            if (login == null) {
                throw new UserAccountLoginNullException("Login can't be null.");
            }
            throw new UserAccountLoginLenghtException("The size of Login exceed the allocated size in the database");
        }
        this.login = login;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) throws UserAccountGenderException, UserAccountGenderNullException {
        if (gender == null || (!gender.equals('M') && !gender.equals('F'))) {
            if (gender == null) {
                throw new UserAccountGenderNullException("Gender can't be null.");
            }
            throw new UserAccountGenderException("Invalid gender. Must be 'M' or 'F'.");
        }
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws UserAccountFirstNameLenghtException, UserAccountFirstNameNullException {
        if (firstName == null || firstName.length() > 32) {
            if (firstName == null) {
                throw new UserAccountFirstNameNullException("First name can't be null.");
            }
            throw new UserAccountFirstNameLenghtException("The size of FirstName exceed the allocated size in the database");
        }
        this.firstName = firstName;
    }

    public String getSecondFirstName() {
        return secondFirstName;
    }

    public void setSecondFirstName(String secondFirstName) throws UserAccountsecondFirstNameLenghtException {
        if (lastName != null && secondFirstName.length() > 32) {
            throw new UserAccountsecondFirstNameLenghtException("The size of SecondFirstName exceed the allocated size in the database");
        }
        this.secondFirstName = secondFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws UserAccountLastNameNullException, UserAccountLastNameLenghtException {
        if (lastName == null || lastName.length() > 32) {
            if (lastName == null) {
                throw new UserAccountLastNameNullException("Last name can't be null.");
            }
            throw new UserAccountLastNameLenghtException("The size of LastName exceed the allocated size in the database");
        }
        this.lastName = lastName;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) throws UserAccountBoxLenghtException {
        if (boxNumber != null && boxNumber.length() > 3) {
            throw new UserAccountBoxLenghtException("The size of BoxNumber exceed the allocated size in the database");
        }
        this.boxNumber = boxNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws UserAccountPasswordNullException, UserAccountPasswordLenghtException {
        if (password == null || password.length() > 166) {
            if (password == null) {
                throw new UserAccountPasswordNullException("User password can't be null.");
            }
            throw new UserAccountPasswordLenghtException("The size of the password exceed the allocated size in the database");
        }
        this.password = password;
    }

    public java.sql.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) throws UserAccountBirthDateNullException, UserAccountBirthDateTimeException {
        if (birthDate == null || birthDate.getTime() > new Date().getTime()) {
            if (birthDate == null) {
                throw new UserAccountBirthDateNullException("User BirthDate can't be null");
            }
            throw new UserAccountBirthDateTimeException("User BirthDate can't be bigger than current time");
        }
        this.birthDate = new java.sql.Date(birthDate.getTime());
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) throws UserAccountCityNullException {
        if (city == null) {
            throw new UserAccountCityNullException("User city can't be null.");
        }
        this.city = city;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) throws UserAccountActiveNullException {
        if (active == null) {
            throw new UserAccountActiveNullException("User active can't be null.");
        }
        this.isActive = active;
    }

    @Override
    public String toString() {
        return "UserAccount{" + "login='" + login + '\'' + ", gender=" + gender + ", firstName='" + firstName + '\'' + ", secondFirstName='" + secondFirstName + '\'' + ", lastName='" + lastName + '\'' + ", boxNumber='" + boxNumber + '\'' + ", password='" + password + '\'' + ", birthDate=" + birthDate + ", city=" + city + ", isActive=" + isActive + ", roles=" + roles + '}';
    }
}
