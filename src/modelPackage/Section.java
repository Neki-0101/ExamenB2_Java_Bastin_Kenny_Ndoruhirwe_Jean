package modelPackage;

import exceptionPackage.SectionBuildingNumberNullException;
import exceptionPackage.SectionBuildingNumberValueException;
import exceptionPackage.SectionLabelLengthException;
import exceptionPackage.SectionLabelNullException;

public class Section {
    private String label;
    private Integer buildingNumber;

    public Section(String label, Integer buildingNumber) throws SectionLabelLengthException, SectionLabelNullException, SectionBuildingNumberNullException, SectionBuildingNumberValueException {
        setLabel(label);
        setBuildingNumber(buildingNumber);
    }

    public String getLabel() {
        return label;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setLabel(String label) throws SectionLabelNullException, SectionLabelLengthException {
        if (label == null || label.length() > 16) {
            if (label == null) {
                throw new SectionLabelNullException("Label can't be null.");
            }
            throw new SectionLabelLengthException("The size of Label exceed the allocated size in the database");
        }
        this.label = label;
    }

    public void setBuildingNumber(Integer buildingNumber) throws SectionBuildingNumberNullException, SectionBuildingNumberValueException {
        if (buildingNumber == null || buildingNumber < 1) {
            if (buildingNumber == null) {
                throw new SectionBuildingNumberNullException("BuildingNumber can't be null.");
            }
            throw new SectionBuildingNumberValueException("BuildingNumber must be greater or equal to 1");
        }
        this.buildingNumber = buildingNumber;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
