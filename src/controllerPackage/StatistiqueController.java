package controllerPackage;

import businessPackage.StatistiqueManager;
import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Section;

import java.util.ArrayList;

public class StatistiqueController {
    private StatistiqueManager manager;

    public void setManager(StatistiqueManager manager) {
        this.manager = manager;
    }

    public StatistiqueController() {
        setManager(new StatistiqueManager());
    }

    public Integer getSectionStudentPercentage(Section section) throws SectionCountException {
        return manager.getSectionStudentPercentage(section);
    }

    public Integer getCityStudentPercentage(City city) throws CityCountException {
        return manager.getCityStudentPercentage(city);
    }

    public Integer[] getSectionGenderCount(Section section) throws SectionCountException, SectionGenderException {
        return manager.getSectionGenderCount(section);
    }

    public ArrayList<Section> getAllSection() throws AllSectionException {
        return manager.getAllSection();
    }

    public ArrayList<City> getAllCities() throws AllCityException {
        return manager.getAllCities();
    }
}
