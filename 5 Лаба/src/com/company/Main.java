package com.company;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner obj = new Scanner(System.in);

    public static void main(String[] args) {

        List<Vehicle> vehicles = new LinkedList<>();



        int select;
        do {
            System.out.println("");
            select = obj.nextInt();
            switch (select){
                case 1:

                    System.out.println("Введіть марку");
                    String brand = obj.nextLine();
                    System.out.println("Введіть колір");
                    String color = obj.nextLine();
                    System.out.println("Введіть номер телефону");
                    String PhoneNumber = obj.nextLine();
                    System.out.println("Введіть рік випуску");
                    int year = obj.nextInt();

                    try {

                        char phone[] = PhoneNumber.toCharArray();

                        if(phone.length < 10) throw new IOException("");
                        if(phone.length > 10 && phone.length != 13  ) throw new IOException("");

                        if(phone.length == 13 && phone[0] != '+') throw new IOException("");

                        if (year < 1800) throw new IOException("Рік введено некоректно");
                        if (year > (new Date()).getYear()) throw new IOException("Рік введено некоректно");
                        System.out.println("Введіть дані про власника");
                    }
                    catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    case 2:
                    break;
            }
        }while(true);
    }
}
