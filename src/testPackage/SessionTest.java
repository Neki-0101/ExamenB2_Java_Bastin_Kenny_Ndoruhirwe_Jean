package testPackage;

import exceptionPackage.*;
import modelPackage.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    private Session session;
    private Date sessionDate;
    private Date startTime;
    private Date endTime;

    @BeforeEach
    void setUp() throws SessionTimeStartNullException, SessionEndTimeDayException, SessionIDValueException, SessionLocalNumberValueException, SessionDateNullException, SessionIDNullException, SessionEndTimeNullException, SessionTimeStartDayException, SessionLocalNumberNullException {
        sessionDate = new Date(10, Calendar.NOVEMBER,10,0,0);
        startTime = new Date(10, Calendar.NOVEMBER,10,5,0);
        endTime = new Date(10, Calendar.NOVEMBER,10,6,0);

        session = new Session(1, sessionDate, startTime, endTime, 1);
    }

    @Test
    void testValidUserAccountCreation() {
        assertNotNull(session);
        assertEquals(1, session.getIdSession());
        assertEquals(sessionDate, session.getDate());
        assertEquals(startTime, session.getTimeStart());
        assertEquals(endTime, session.getEndTime());
        assertEquals(1, session.getLocalNumber());
    }

    @Test
    void TestSetIdSession_Null() {
        assertThrows(SessionIDNullException.class, () -> session.setIdSession(null));
    }

    @Test
    void TestSetIdSession_Negative() {
        assertThrows(SessionIDValueException.class, () -> session.setIdSession(-1));
    }

    @Test
    void TestSetDate_Null() {
        assertThrows(SessionDateNullException.class, () -> session.setDate(null));
    }

    @Test
    void TestSetTimeStart_Null() {
        assertThrows(SessionTimeStartNullException.class, () -> session.setTimeStart(null));
    }

    @Test
    void TestSetTimeStart_Day() {
        assertThrows(SessionTimeStartDayException.class, () -> session.setTimeStart(new Date(10, Calendar.NOVEMBER,11,5,0)));
    }

    @Test
    void TestSetEndTime_Null() {
        assertThrows(SessionEndTimeNullException.class, () -> session.setEndTime(null));
    }

    @Test
    void TestSetEndTime_Day() {
        assertThrows(SessionEndTimeDayException.class, () -> session.setEndTime(new Date(10, Calendar.NOVEMBER,11,5,0)));
    }

    @Test
    void TestSetLocalNumber_Null() {
        assertThrows(SessionLocalNumberNullException.class, () -> session.setLocalNumber(null));
    }

    @Test
    void TestSetLocalNumber_Negative() {
        assertThrows(SessionLocalNumberValueException.class, () -> session.setLocalNumber(-1));
    }
}