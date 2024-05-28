package testPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;
    private City city;

    @BeforeEach
    void setUp() throws CityPostalNullException, CityPostalFormatException, CityNameLengthException, CityNameNullException, CityIDNullException, CityIDException, UserAccountActiveNullException, UserAccountFirstNameLenghtException, UserAccountLoginNullException, UserAccountGenderNullException, UserAccountBirthDateNullException, UserAccountLastNameLenghtException, UserAccountBirthDateTimeException, UserAccountPasswordNullException, UserAccountFirstNameNullException, UserAccountCityNullException, UserAccountLoginIDNullException, UserAccountGenderException, UserAccountsecondFirstNameLenghtException, UserAccountBoxLenghtException, UserAccountPasswordLenghtException, UserAccountLoginLenghtException, UserAccountLastNameNullException, StudentRegistrationYearNullException, StudentRegistrationYearValueException {
        city = new City(1, "Test City", 1234);
        student = new Student(1, "testUser", 'M', "John", "Doe", "Smith", "123", "password", new Date(System.currentTimeMillis() - 1000), city, true, 2021);
    }

    @Test
    void TestValidStudentCreation() {
        assertNotNull(student);
        assertEquals("testUser", student.getLogin());
        assertEquals('M', student.getGender());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getSecondFirstName());
        assertEquals("Smith", student.getLastName());
        assertEquals("123", student.getBoxNumber());
        assertEquals("password", student.getPassword());
        assertEquals(city, student.getCity());
        assertTrue(student.isActive());
        assertEquals(2021, student.getRegistrationYear());
        assertNotNull(student.getRoles());
    }

    @Test
    void TestSetRegistrationYear_Null() {
        assertThrows(StudentRegistrationYearNullException.class, () -> student.setRegistrationYear(null));
    }

    @Test
    void TestSetRegistrationYear_InvalidDate() {
        assertThrows(StudentRegistrationYearValueException.class, () -> student.setRegistrationYear(1949));
    }
}