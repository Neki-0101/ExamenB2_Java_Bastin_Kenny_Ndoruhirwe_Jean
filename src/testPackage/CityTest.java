package testPackage;

import exceptionPackage.*;
import modelPackage.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    private City city;

    @BeforeEach
    void setUp() throws CityPostalNullException, CityPostalFormatException, CityNameLengthException, CityNameNullException, CityIDNullException, CityIDException {
        city = new City(1, "Test City", 1234);
    }

    @Test
    void testValidCityCreation() {
        assertNotNull(city);
        assertEquals(1, city.getCityID());
        assertEquals("Test City", city.getName());
        assertEquals(1234, city.getPostalCode());
    }

    @Test
    void testSetCityID_Null() {
        assertThrows(CityIDNullException.class, () -> city.setCityID(null));
    }

    @Test
    void testSetName_Null() {
        assertThrows(CityNameNullException.class, () -> city.setName(null));
    }

    @Test
    void testSetName_LengthExceeded() {
        assertThrows(CityNameLengthException.class, () -> city.setName("a".repeat(25)));
    }

    @Test
    void testSetPostalCode_Null() {
        assertThrows(CityPostalNullException.class, () -> city.setPostalCode(null));
    }

    @Test
    void testSetPostalCode_FormatInvalid() {
        assertThrows(CityPostalFormatException.class, () -> city.setPostalCode(12345)); // Postal code length > 4
    }
}