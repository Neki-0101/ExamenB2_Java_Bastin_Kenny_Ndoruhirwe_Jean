package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Section;

import java.util.ArrayList;

public interface IStatDBAccess {
    ArrayList<Section> getAllSection() throws AllSectionException;

    ArrayList<City> getAllCities() throws AllCityException;

    Integer getSectionStudentCount(Section section) throws SectionCountException;

    Integer[] getSectionGenderCount(Section section) throws SectionGenderException;

    Integer getCityStudentCount(City city) throws CityCountException;

    Integer getTotalStudentCount();
}
