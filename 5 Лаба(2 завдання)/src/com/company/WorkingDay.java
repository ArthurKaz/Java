package com.company;

import java.io.IOException;
import java.util.Date;
import java.sql.Time;
import java.util.Scanner;

public class WorkingDay implements Doctor{
    private  String PIB;
    private String Specialty;
    private Date Date;
    private int AmountOfPatient;
    private Time StartTime;

    public WorkingDay(String PIB, String specialty,  Date date,int amountOfPatient, Time startTime) {
        this.PIB = PIB;
        Specialty = specialty;
        Date = date;
        AmountOfPatient = amountOfPatient;
        StartTime = startTime;
    }

    @Override
    public boolean isOver(Time time) {
        return (time.getHours()* 60 * 60 + time.getMinutes() * 60 +time.getSeconds() ) < (StartTime.getHours()* 60 * 60 + StartTime.getMinutes() * 60 +StartTime.getSeconds() );
    }

    public WorkingDay(String str) {
        String[] data = str.split(" ");
        PIB = data[0];
        Specialty = data[1];

        int year = Integer.parseInt(data[2]);
        int month = Integer.parseInt( data[3]);
        int day = Integer.parseInt( data[4]);
        Date = new Date(year,month,day);
        AmountOfPatient = Integer.parseInt(data[5]);

        String[] time = data[6].split(":");
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int seconds = Integer.parseInt(time[2]);
        StartTime = new Time(hours,minutes,seconds);
    }

    @Override
    public java.util.Date getDate() {
        return Date;
    }

    @Override
    public int getAmountOfPatient() {
        return AmountOfPatient;
    }

    @Override
    public String toSave() {
        return PIB + " " + Specialty+ " " + Date.getYear() + " " + Date.getMonth() + " " + Date.getDate()+ " " +AmountOfPatient +" "+ StartTime+ "\n";
    }

    @Override
    public void output() {
        System.out.println("\nПІБ лікаря - " + PIB+"\nСпеціальність - "+Specialty+"\nДата - "+Date.getDate() + "."+Date.getMonth()+"."+Date.getYear()+"\nКількість пацієнтів - "+AmountOfPatient+"\nЧас початку - "+StartTime+"\n");
    }

    @Override
    public void edit() throws Exception {
        Scanner obj = new Scanner(System.in);
        System.out.println("Що саме ви бажаєте змінити?");
        System.out.println("1. ПІБ Лікаря");
        System.out.println("2. Спеціальність");
        System.out.println("3. Дату");
        System.out.println("4. Кількість пацієнтів");
        System.out.println("5. Час початку");
        int select = obj.nextInt();
        switch (select) {
            case 1:
                System.out.println("Введіть ПІБ");
                PIB = obj.next();
                break;
            case 2:
                System.out.println("Введіть спеціальність");
                Specialty = obj.next();
                break;
            case 3:
                System.out.println("Введіть дату");

                System.out.println("Введіть дату");
                System.out.println("Введіть день");
                int date = obj.nextInt();
                if (date < 1 || date > 31) throw new IOException("Некоректно введено день");
                System.out.println("Введіть місяць");
                int month = obj.nextInt();
                if (month < 1 || month > 12) throw new IOException("Некоректно введено місяць");
                System.out.println("Введіть рік");
                int year = obj.nextInt();
                if (year < 1900 || year > 2030) throw new IOException("Некоректно введено рік");

                Date = new Date(year, month, date);
                break;
            case 4:
                System.out.println("Введіть кіькість пацієнтів");

                AmountOfPatient = obj.nextInt();
                break;
            case 5:
                System.out.println("Введення часу початку роботи");
                System.out.println("Введіть годину");
                int hours = obj.nextInt();
                System.out.println("Введіть хвилину");
                int minutes = obj.nextInt();

                if (minutes > 59 || minutes < 0) throw new Exception("Некорекно введена хвилина");
                if (hours > 24 || hours < 0) throw new Exception("Некорекно введена година");
                StartTime = new Time(hours, minutes, 0);
                break;
            default:
                System.out.println(select + ": Немає токої опції");
        }
    }


}
