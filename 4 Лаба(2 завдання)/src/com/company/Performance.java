package com.company;

import java.util.Date;

public class Performance extends Poet{
    private Date date;
    private String place;
    private int AmountOfListener;

    public Performance(String surname, String language, int amountOfCollection,Date date, String place, int amountOfListener) {
        super(surname, language, amountOfCollection);
        this.date = date;
        this.place = place;
        AmountOfListener = amountOfListener;
    }
    public Performance(String str) {
        String[] data =str.split(" ");
        Surname = data[0];
        Language = data[1];


        AmountOfCollection = Integer.parseInt(data[2]);

        int year = Integer.parseInt(data[3]);
        int month = Integer.parseInt(data[4]);
        int date = Integer.parseInt(data[5]);

        this.date = new Date(year,month,date);
        place = data[6];
        AmountOfListener = Integer.parseInt(data[7]);
    }

    @Override
    public int getLengthOfSurname(){
        return Surname.length();
    }

    @Override
    public int getAmountOfListener(){
        return AmountOfListener;
    }

    @Override
    public Date getDate(){
        return date;
    }



    @Override
    public String toString(){
        return "\nДата - "+date+"\n Місце - "+place+"\nКлькість слухачів - "+AmountOfListener+"\nПрізвище - "+Surname+ "\nМова - "+ Language + "\nКількість збірок - "+AmountOfCollection;
    }

    @Override
    public String toSave() {
        return Surname + " " + Language + " "+AmountOfCollection+" "+ date.getYear() +" " + date.getMonth() + " "+ date.getDate() +" " + place+" "+ AmountOfListener + "\n";
    }
}
