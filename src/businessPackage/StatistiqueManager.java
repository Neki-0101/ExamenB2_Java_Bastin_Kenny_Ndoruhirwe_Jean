package businessPackage;

import dataAccessPackage.IStatDBAccess;
import dataAccessPackage.StatDBAccess;
import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Section;

import java.util.ArrayList;

public class StatistiqueManager {
    private IStatDBAccess dataAccess;

    public StatistiqueManager() {
        setDao(new StatDBAccess());
    }

    public void setDao(IStatDBAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public Integer getSectionStudentPercentage(Section section) throws SectionCountException {
        int sectionStudentCount = dataAccess.getSectionStudentCount(section);
        int totalStudentCount = dataAccess.getTotalStudentCount();

        if (totalStudentCount == 0) {
            return 0;
        }

        return (int) Math.round((sectionStudentCount / (double) totalStudentCount) * 100);
    }

    public Integer getCityStudentPercentage(City city) throws CityCountException {
        int sectionStudentCount = dataAccess.getCityStudentCount(city);
        int totalStudentCount = dataAccess.getTotalStudentCount();

        if (totalStudentCount == 0) {
            return 0;
        }

        return (int) Math.round((sectionStudentCount / (double) totalStudentCount) * 100);
    }

    public Integer[] getSectionGenderCount(Section section) throws SectionCountException, SectionGenderException {
        Integer[] sectionGenderCount = dataAccess.getSectionGenderCount(section);
        int sectionStudentCount = dataAccess.getSectionStudentCount(section);

        if (sectionStudentCount == 0) {
            return new Integer[]{0, 0};
        }

        return new Integer[]{(int) Math.round((sectionGenderCount[0] / (double) sectionStudentCount) * 100), (int) Math.round((sectionGenderCount[1] / (double) sectionStudentCount) * 100)};
    }

    public ArrayList<Section> getAllSection() throws AllSectionException {
        return dataAccess.getAllSection();
    }

    public ArrayList<City> getAllCities() throws AllCityException {
        return dataAccess.getAllCities();
    }
}
