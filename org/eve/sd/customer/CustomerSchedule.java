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
    public final int getType() {
        return type;
    }

    /**
     * @return the monday
     */
    public final Time getMonday() {
        return monday;
    }

    /**
     * @return the tuesday
     */
    public final Time getTuesday() {
        return tuesday;
    }

    /**
     * @return the wednesday
     */
    public final Time getWednesday() {
        return wednesday;
    }

    /**
     * @return the thursday
     */
    public final Time getThursday() {
        return thursday;
    }

    /**
     * @return the friday
     */
    public final Time getFriday() {
        return friday;
    }

    /**
     * @return the saturday
     */
    public final Time getSaturday() {
        return saturday;
    }

    /**
     * @return the sunday
     */
    public final Time getSunday() {
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
    public final void setType(int type) {
        this.type = type;
    }

    /**
     * @param monday the monday to set
     */
    public final void setMonday(Time monday) {
        this.monday = monday;
    }

    /**
     * @param tuesday the tuesday to set
     */
    public final void setTuesday(Time tuesday) {
        this.tuesday = tuesday;
    }

    /**
     * @param wednesday the wednesday to set
     */
    public final void setWednesday(Time wednesday) {
        this.wednesday = wednesday;
    }

    /**
     * @param thursday the thursday to set
     */
    public final void setThursday(Time thursday) {
        this.thursday = thursday;
    }

    /**
     * @param friday the friday to set
     */
    public final void setFriday(Time friday) {
        this.friday = friday;
    }

    /**
     * @param saturday the saturday to set
     */
    public final void setSaturday(Time saturday) {
        this.saturday = saturday;
    }

    /**
     * @param sunday the sunday to set
     */
    public final void setSunday(Time sunday) {
        this.sunday = sunday;
    }
    
    
    
}
