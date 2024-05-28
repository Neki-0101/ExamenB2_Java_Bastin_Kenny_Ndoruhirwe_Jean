//Model finished
package modelPackage;

import exceptionPackage.*;

public class City {
    private Integer cityID;
    private String name;
    private Integer postalCode;

    public City(Integer cityID, String name, Integer postalCode) throws CityIDException, CityNameLengthException, CityPostalFormatException, CityIDNullException, CityNameNullException, CityPostalNullException {
        setCityID(cityID);
        setName(name);
        setPostalCode(postalCode);
    }

    public void setCityID(Integer cityID) throws CityIDException, CityIDNullException {
        if (cityID == null || cityID < 0) {
            if (cityID == null) {
                throw new CityIDNullException("ID can't be null.");
            }
            throw new CityIDException("ID must be positive");
        }
        this.cityID = cityID;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setName(String name) throws CityNameLengthException, CityNameNullException {
        if (name == null || name.length() > 24) {
            if (name == null) {
                throw new CityNameNullException("The name can't be null");
            }
            throw new CityNameLengthException("The size of Name exceed the allocated size in the database");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPostalCode(Integer postalCode) throws CityPostalFormatException, CityPostalNullException {
        if (postalCode == null || (postalCode < 1000 || postalCode > 9999)) {
            if (postalCode == null) {
                throw new CityPostalNullException("The postal code can't be null");
            }
            throw new CityPostalFormatException("The postal code need to be between 1000 and 9999");
        }
        this.postalCode = postalCode;
    }

    public int getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return getName() + " - " + getPostalCode();
    }
}
