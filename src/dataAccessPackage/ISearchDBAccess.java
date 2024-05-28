package dataAccessPackage;

import exceptionPackage.AllCityException;
import exceptionPackage.AllSectionException;
import exceptionPackage.AllTUException;
import exceptionPackage.SearchException;
import modelPackage.City;
import modelPackage.Section;
import modelPackage.TeachingUnit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface ISearchDBAccess {
    ArrayList<HashMap<String, Object>> listSearch1(Date date1, Date date2, TeachingUnit tu) throws SearchException;

    ArrayList<HashMap<String, Object>> listSearch2(Section section) throws SearchException;

    ArrayList<HashMap<String, Object>> listSearch3(City city, boolean selected) throws SearchException;

    ArrayList<TeachingUnit> getAllTU() throws AllTUException;

    ArrayList<Section> getAllSection() throws AllSectionException;

    ArrayList<City> getAllCities() throws AllCityException;
}
