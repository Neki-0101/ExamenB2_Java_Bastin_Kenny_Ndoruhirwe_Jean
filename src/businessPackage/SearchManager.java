package businessPackage;

import dataAccessPackage.ISearchDBAccess;
import dataAccessPackage.SearchDBAccess;
import exceptionPackage.AllCityException;
import exceptionPackage.AllSectionException;
import exceptionPackage.AllTUException;
import exceptionPackage.SearchException;
import modelPackage.City;
import modelPackage.Section;
import modelPackage.TeachingUnit;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SearchManager {
    private ISearchDBAccess dataAccess;

    public SearchManager() {
        setDao(new SearchDBAccess());
    }

    public void setDao(ISearchDBAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public ArrayList<HashMap<String, Object>> listSearch1(Date date1, Date date2, TeachingUnit tu) throws SearchException {
        java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
        java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
        return dataAccess.listSearch1(sqlDate1, sqlDate2, tu);
    }

    public ArrayList<HashMap<String, Object>> listSearch2(Section section) throws SearchException {
        return dataAccess.listSearch2(section);
    }

    public ArrayList<HashMap<String, Object>> listSearch3(City city, boolean selected) throws SearchException {
        return dataAccess.listSearch3(city, selected);
    }

    public ArrayList<TeachingUnit> getAllTU() throws AllTUException {
        return dataAccess.getAllTU();
    }

    public ArrayList<Section> getAllSection() throws AllSectionException {
        return dataAccess.getAllSection();
    }

    public ArrayList<City> getAllCities() throws AllCityException {
        return dataAccess.getAllCities();
    }
}
