package controllerPackage;

import businessPackage.SearchManager;
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

public class SearchController {
    private SearchManager manager;

    public void setManager(SearchManager manager) {
        this.manager = manager;
    }

    public SearchController() {
        setManager(new SearchManager());
    }

    public ArrayList<HashMap<String, Object>> listSearch1(Date date1, Date date2, TeachingUnit tu) throws SearchException {
        return manager.listSearch1(date1, date2, tu);
    }

    public ArrayList<HashMap<String, Object>> listSearch2(Section section) throws SearchException {
        return manager.listSearch2(section);
    }

    public ArrayList<HashMap<String, Object>> listSearch3(City city, boolean selected) throws SearchException {
        return manager.listSearch3(city, selected);
    }

    public ArrayList<TeachingUnit> getAllTU() throws AllTUException {
        return manager.getAllTU();
    }

    public ArrayList<City> getAllCities() throws AllCityException {
        return manager.getAllCities();
    }

    public ArrayList<Section> getAllSection() throws AllSectionException {
        return manager.getAllSection();
    }
}
