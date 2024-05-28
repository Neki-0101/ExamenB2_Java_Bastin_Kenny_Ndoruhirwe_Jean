//Model finished
package modelPackage;

import exceptionPackage.*;

public class LearningActivity {
    private Integer activityCode;
    private String activityTitle;
    private Integer hoursNb;
    private Integer creditsNb;
    private TeachingUnit unit;

    public LearningActivity(Integer activityCode, String activityTitle, Integer hoursNb, Integer creditsNb, TeachingUnit unit) throws LearningActivityCodeNullException, LearningActivityCodeValueException, LearningActivityTitleLenghtException, LearningActivityTitleNullException, LearningActivityCreditValueException, LearningActivityCreditNullException, LearningActivityHourNullException, LearningActivityHourValueException, LearningActivityUnitNullException {
        this.setActivityCode(activityCode);
        this.setActivityTitle(activityTitle);
        this.setCreditsNb(creditsNb);
        this.setHoursNb(hoursNb);
        this.setUnit(unit);
    }

    public Integer getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(Integer activityCode) throws LearningActivityCodeNullException, LearningActivityCodeValueException {
        if (activityCode == null || activityCode < 0) {
            if (activityCode == null) {
                throw new LearningActivityCodeNullException("LearningActivity code can't be null");
            }
            throw new LearningActivityCodeValueException("LearningActivity code must be greater than 0");
        }
        this.activityCode = activityCode;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) throws LearningActivityTitleNullException, LearningActivityTitleLenghtException {
        if (activityTitle == null || activityTitle.length() > 32) {
            if (activityTitle == null) {
                throw new LearningActivityTitleNullException("");
            }
            throw new LearningActivityTitleLenghtException("");
        }
        this.activityTitle = activityTitle;
    }

    public Integer getCreditsNb() {
        return creditsNb;
    }

    public void setCreditsNb(Integer creditsNb) throws LearningActivityCreditNullException, LearningActivityCreditValueException {
        if (creditsNb == null || (creditsNb < 0 || creditsNb > 60)) {
            if (creditsNb == null) {
                throw new LearningActivityCreditNullException("The number of credit can't be null");
            }
            throw new LearningActivityCreditValueException("The number of credit must be between 0 and 60");
        }
        this.creditsNb = creditsNb;
    }

    public Integer getHoursNb() {
        return hoursNb;
    }

    public void setHoursNb(Integer hoursNb) throws LearningActivityHourNullException, LearningActivityHourValueException {
        if (hoursNb == null || hoursNb < 0) {
            if (hoursNb == null) {
                throw new LearningActivityHourNullException("HoursNb can't be null");
            }
            throw new LearningActivityHourValueException("HoursNb must be greater than 0");
        }
        this.hoursNb = hoursNb;
    }

    public TeachingUnit getUnit() {
        return unit;
    }

    public void setUnit(TeachingUnit unit) throws LearningActivityUnitNullException {
        if (unit == null) {
            throw new LearningActivityUnitNullException("LearningActivity Unit can't be null");
        }
        this.unit = unit;
    }
}
