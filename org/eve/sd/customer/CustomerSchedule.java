package org.eve.sd.customer;

import java.sql.Time;

import org.eve.model.DataType;

public class CustomerSchedule extends AbstractCustomerItem {
    private static final long serialVersionUID = 6132463280640059272L;

    public CustomerSchedule() {
        hash = 29;
        
        put("customer.ident", IS_KEY, DataType.INT, 10);
        put("schedule.per", NOT_KEY, DataType.TIME, 8);
        put("schedule.mon", NOT_KEY, DataType.TIME, 8);
        put("schedule.tue", NOT_KEY, DataType.TIME, 8);
        put("schedule.wed", NOT_KEY, DataType.TIME, 8);
        put("schedule.thu", NOT_KEY, DataType.TIME, 8);
        put("schedule.fri", NOT_KEY, DataType.TIME, 8);
        put("schedule.type", NOT_KEY, DataType.INT, 1);
    }

    /*
     * 
     * Getters
     * 
     */
    
    /**
     * @return the type
     */
    public int getType() {
        return (Integer)getValue("schedule.type");
    }

    /**
     * @return the monday
     */
    public Time getMonday() {
        return (Time)getValue("schedule.mon");
    }

    /**
     * @return the tuesday
     */
    public Time getTuesday() {
        return (Time)getValue("schedule.tue");
    }

    /**
     * @return the wednesday
     */
    public Time getWednesday() {
        return (Time)getValue("schedule.wed");
    }

    /**
     * @return the thursday
     */
    public Time getThursday() {
        return (Time)getValue("schedule.thu");
    }

    /**
     * @return the friday
     */
    public Time getFriday() {
        return (Time)getValue("schedule.fri");
    }

    /**
     * @return the saturday
     */
    public Time getSaturday() {
        return (Time)getValue("schedule.sat");
    }

    /**
     * @return the sunday
     */
    public Time getSunday() {
        return (Time)getValue("schedule.sun");
    }

    /*
     * 
     * Setters
     * 
     */
    
    /**
     * @param type the type to set
     */
    public void setType(int type) {
        setValue("schedule.type", type);
    }

    /**
     * @param monday the monday to set
     */
    public void setMonday(Time monday) {
        setValue("schedule.mon", monday);
    }

    /**
     * @param tuesday the tuesday to set
     */
    public void setTuesday(Time tuesday) {
        setValue("schedule.tue", tuesday);
    }

    /**
     * @param wednesday the wednesday to set
     */
    public void setWednesday(Time wednesday) {
        setValue("schedule.wed", wednesday);
    }

    /**
     * @param thursday the thursday to set
     */
    public void setThursday(Time thursday) {
        setValue("schedule.thu", thursday);
    }

    /**
     * @param friday the friday to set
     */
    public void setFriday(Time friday) {
        setValue("schedule.fri", friday);
    }

    /**
     * @param saturday the saturday to set
     */
    public void setSaturday(Time saturday) {
        setValue("schedule.sat", saturday);
    }

    /**
     * @param sunday the sunday to set
     */
    public void setSunday(Time sunday) {
        setValue("schedule.sun", sunday);
    }
}
