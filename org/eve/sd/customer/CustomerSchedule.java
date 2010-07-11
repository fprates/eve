package org.eve.sd.customer;

import java.sql.Time;

public class CustomerSchedule extends AbstractCustomerItem {
    private static final long serialVersionUID = 6132463280640059272L;
    private int type;
    private Time monday;
    private Time tuesday;
    private Time wednesday;
    private Time thursday;
    private Time friday;
    private Time saturday;
    private Time sunday;

    public CustomerSchedule() {
        hash = 29;
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
        return type;
    }

    /**
     * @return the monday
     */
    public Time getMonday() {
        return monday;
    }

    /**
     * @return the tuesday
     */
    public Time getTuesday() {
        return tuesday;
    }

    /**
     * @return the wednesday
     */
    public Time getWednesday() {
        return wednesday;
    }

    /**
     * @return the thursday
     */
    public Time getThursday() {
        return thursday;
    }

    /**
     * @return the friday
     */
    public Time getFriday() {
        return friday;
    }

    /**
     * @return the saturday
     */
    public Time getSaturday() {
        return saturday;
    }

    /**
     * @return the sunday
     */
    public Time getSunday() {
        return sunday;
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
        this.type = type;
    }

    /**
     * @param monday the monday to set
     */
    public void setMonday(Time monday) {
        this.monday = monday;
    }

    /**
     * @param tuesday the tuesday to set
     */
    public void setTuesday(Time tuesday) {
        this.tuesday = tuesday;
    }

    /**
     * @param wednesday the wednesday to set
     */
    public void setWednesday(Time wednesday) {
        this.wednesday = wednesday;
    }

    /**
     * @param thursday the thursday to set
     */
    public void setThursday(Time thursday) {
        this.thursday = thursday;
    }

    /**
     * @param friday the friday to set
     */
    public void setFriday(Time friday) {
        this.friday = friday;
    }

    /**
     * @param saturday the saturday to set
     */
    public void setSaturday(Time saturday) {
        this.saturday = saturday;
    }

    /**
     * @param sunday the sunday to set
     */
    public void setSunday(Time sunday) {
        this.sunday = sunday;
    }
    
    
    
}