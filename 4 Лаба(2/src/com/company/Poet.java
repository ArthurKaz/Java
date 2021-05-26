package com.company;

public class Poet {
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

    public int getLengthOfSurname(){
        return Surname.length();
    }



}
