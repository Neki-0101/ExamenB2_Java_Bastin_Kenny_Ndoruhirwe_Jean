package testPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Role;
import modelPackage.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountTest {
    private UserAccount user;
    private City city;

    @BeforeEach
    void setUp() throws CityNameLengthException, CityNameNullException, CityPostalFormatException, CityPostalNullException, CityIDException, CityIDNullException, UserAccountActiveNullException, UserAccountFirstNameLenghtException, UserAccountLoginNullException, UserAccountGenderNullException, UserAccountBirthDateNullException, UserAccountLastNameLenghtException, UserAccountBirthDateTimeException, UserAccountPasswordNullException, UserAccountFirstNameNullException, UserAccountCityNullException, UserAccountLoginIDNullException, UserAccountGenderException, UserAccountsecondFirstNameLenghtException, UserAccountBoxLenghtException, UserAccountPasswordLenghtException, UserAccountLoginLenghtException, UserAccountLastNameNullException {
        city = new City(1, "Test City", 1234);
        user = new UserAccount(1, "testUser", 'M', "John", "Doe", "Smith", "123", "password", new Date(System.currentTimeMillis() - 1000), city, true);
    }

    @Test
    void testValidUserAccountCreation() {
        assertNotNull(user);
        assertEquals("testUser", user.getLogin());
        assertEquals('M', user.getGender());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getSecondFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("123", user.getBoxNumber());
        assertEquals("password", user.getPassword());
        assertEquals(city, user.getCity());
        assertTrue(user.isActive());
        assertNotNull(user.getRoles());
    }

    @Test
    void testSetLogin_Null() {
        assertThrows(UserAccountLoginNullException.class, () -> user.setLogin(null));
    }

    @Test
    void testSetLogin_LengthExceeded() {
        assertThrows(UserAccountLoginLenghtException.class, () -> user.setLogin("aVeryLongLoginNameThatExceedsTheLimit"));
    }

    @Test
    void testSetGender_Invalid() {
        assertThrows(UserAccountGenderException.class, () -> user.setGender('X'));
    }

    @Test
    void testSetFirstName_Null() {
        assertThrows(UserAccountFirstNameNullException.class, () -> user.setFirstName(null));
    }

    @Test
    void testSetFirstName_LengthExceeded() {
        assertThrows(UserAccountFirstNameLenghtException.class, () -> user.setFirstName("aVeryLongFirstNameThatExceedsTheLimit"));
    }

    @Test
    void testSetSecondFirstName_LengthExceeded() {
        assertThrows(UserAccountsecondFirstNameLenghtException.class, () -> user.setSecondFirstName("aVeryLongSecondFirstNameThatExceedsTheLimit"));
    }

    @Test
    void testSetLastName_Null() {
        assertThrows(UserAccountLastNameNullException.class, () -> user.setLastName(null));
    }

    @Test
    void testSetLastName_LengthExceeded() {
        assertThrows(UserAccountLastNameLenghtException.class, () -> user.setLastName("aVeryLongLastNameThatExceedsTheLimit"));
    }

    @Test
    void testSetPassword_Null() {
        assertThrows(UserAccountPasswordNullException.class, () -> user.setPassword(null));
    }

    @Test
    void testSetPassword_LengthExceeded() {
        String longPassword = "a".repeat(167);
        assertThrows(UserAccountPasswordLenghtException.class, () -> user.setPassword(longPassword));
    }

    @Test
    void testSetBirthDate_Null() {
        assertThrows(UserAccountBirthDateNullException.class, () -> user.setBirthDate(null));
    }

    @Test
    void testSetBirthDate_FutureDate() {
        assertThrows(UserAccountBirthDateTimeException.class, () -> user.setBirthDate(new Date(System.currentTimeMillis() + 10000)));
    }

    @Test
    void testSetCity_Null() {
        assertThrows(UserAccountCityNullException.class, () -> user.setCity(null));
    }

    @Test
    void testSetActive_Null() {
        assertThrows(UserAccountActiveNullException.class, () -> user.setActive(null));
    }

    @Test
    void testAddRole() throws RoleIDValueException, RoleNameNullException, RoleNameLengthException, RoleIDNullException {
        Role role = new Role(1, "ADMIN");
        user.addRole(role);
        assertTrue(user.getRoles().contains(role));
    }

    @Test
    void testRemoveRole() throws RoleIDValueException, RoleNameNullException, RoleNameLengthException, RoleIDNullException {
        Role role = new Role(1, "ADMIN");
        user.addRole(role);
        user.removeRole(role);
        assertFalse(user.getRoles().contains(role));
    }
}
