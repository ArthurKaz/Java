package com.company;

import java.util.Date;
import java.sql.Time;
public interface Doctor {

    abstract void output();

    void edit()throws Exception;

    String toSave();

    int getAmountOfPatient();

    Date getDate();

    boolean isOver(Time time);
}
