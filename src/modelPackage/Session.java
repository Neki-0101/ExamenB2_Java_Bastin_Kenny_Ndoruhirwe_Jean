package modelPackage;

import exceptionPackage.*;

import java.util.Date;

public class Session {
    private Integer idSession;
    private Date date;
    private Date timeStart;
    private Date endTime;
    private Integer localNumber;

    public Session(Integer idSession, Date date, Date timeStart, Date endTime, Integer localNumber) throws SessionIDValueException, SessionIDNullException, SessionTimeStartNullException, SessionTimeStartDayException, SessionLocalNumberValueException, SessionLocalNumberNullException, SessionEndTimeDayException, SessionEndTimeNullException, SessionDateNullException {
        this.setDate(date);
        this.setIdSession(idSession);
        this.setEndTime(endTime);
        this.setLocalNumber(localNumber);
        this.setTimeStart(timeStart);
    }

    public Integer getIdSession() {
        return idSession;
    }

    public Date getDate() {
        return date;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public Integer getLocalNumber() {
        return localNumber;
    }

    public void setIdSession(Integer idSession) throws SessionIDNullException, SessionIDValueException {
        if (idSession == null || idSession < 0){
            if (idSession == null){
                throw new SessionIDNullException("Session Id can't be null");
            }
            throw new SessionIDValueException("Session Id must be greater than 0");
        }
        this.idSession = idSession;
    }

    public void setDate(Date date) throws SessionDateNullException {
        if (date == null){
            throw new SessionDateNullException("Session date can't be null");
        }
        this.date = date;
    }

    public void setTimeStart(Date timeStart) throws SessionTimeStartNullException, SessionTimeStartDayException {
        if (timeStart == null || (timeStart.getDate() != getDate().getDate())){
            if (timeStart == null){
                throw new SessionTimeStartNullException("Session start time can't be null");
            }
            throw new SessionTimeStartDayException("Session start time must be in the same day than the session date");
        }
        this.timeStart = timeStart;
    }

    public void setEndTime(Date endTime) throws SessionEndTimeNullException, SessionEndTimeDayException {
        if (endTime == null || (endTime.getDate() != getDate().getDate())){
            if (endTime == null){
                throw new SessionEndTimeNullException("Session end time can't be null");
            }
            throw new SessionEndTimeDayException("Session end time must be in the same day than the session date");
        }
        this.endTime = endTime;
    }

    public void setLocalNumber(Integer localNumber) throws SessionLocalNumberValueException, SessionLocalNumberNullException {
        if (localNumber == null || localNumber < 0){
            if (localNumber == null){
                throw new SessionLocalNumberNullException("Session can't be null");
            }
            throw new SessionLocalNumberValueException("Session LocalNumber must be greater than 0");
        }
        this.localNumber = localNumber;
    }
}
