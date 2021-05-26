package com.company;

import java.util.Date;

public abstract class Poet {
    protected String Surname;
    protected String Language;
    protected int AmountOfCollection;

    public Poet (String surname,String language,int amountOfCollection ){
        Surname = surname;
        Language = language;
        AmountOfCollection = amountOfCollection;
    }

    public Poet() {
    }

    public abstract int getLengthOfSurname();
    public abstract int getAmountOfListener();
    public abstract Date getDate();
    public abstract String toSave();



}
