package com.company;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Car implements Vehicle{
    private String Brand;
    private String Color;
    private String PhoneNumber;
    private int Year;
    private String Data;


    public Car(String brand, String color, String phoneNumber, int year, String data) {
        Brand = brand;
        Color = color;
        PhoneNumber = phoneNumber;
        Year = year;
        Data = data;
    }

    public Car(String str) {
        String[] data =str.split(" ");
        Brand = data[0];
        Color = data[1];
        PhoneNumber = data[2];
        Year = Integer.parseInt(data[3]);
        Data = data[4];
    }

    @Override
    public String toSave() {
        return Brand + " " + Color + " " + PhoneNumber + " " + Year +" " +Data + "\n";
    }

    @Override
    public void output() {
        System.out.println("Марка - "+Brand);
        System.out.println("Колір - "+Color);
        System.out.println("Номре телефону - "+PhoneNumber);
        System.out.println("Рік випуску - "+Year);
        System.out.println("Дані про власника - "+Data+"\n");
    }

    @Override
    public char getBrand() {
        return Brand.toCharArray()[0];
    }

    @Override
    public String getColor() {
        return Color;
    }

    @Override
    public void edit() throws IOException {
        Scanner obj = new Scanner(System.in);
        System.out.println("Що саме ви бажаєте змінити?");
        System.out.println("1. Марку");
        System.out.println("2. Колір");
        System.out.println("3. Номер телефону");
        System.out.println("4. Рік випуску");
        System.out.println("5. Дані про власника");

        int select = obj.nextInt();

        switch (select){
            case 1:
                System.out.println("Введіть марку");
                Brand = obj.next();
                break;
            case 2:
                System.out.println("Введіть колір");
                Color =  obj.next();
                break;
            case 3:
                System.out.println("Введіть номер телефону");

                String phoneNumber =  obj.next();
                char phone[] = phoneNumber.toCharArray();
                if (phone.length < 10) throw new IOException("Номер телефону введено некоректно: кількість символів повинна бути не менша за 10");
                if (phone.length > 10 && phone.length != 13) throw new IOException("Номер телефону введено некоректно: кількість символів повинна бути не більшою за 13");
                PhoneNumber = phoneNumber;
                break;
            case 4:
                System.out.println("Введіть рік випуску");
                int year = obj.nextInt();
                if(year < 1800) throw new IOException("Рік введено некоректно");
                if (year > (new Date()).getYear()) throw new IOException("Рік введено некоректно");
                Year = year;
                break;
            case 5:
                System.out.println("Введіть дані про власника");
                Data =  obj.nextLine();
                break;
            default:
                System.out.println(select + ": Немає токої опції");
        }

    }

    @Override
    public String getStringBrand() {
        return Brand;
    }
}
