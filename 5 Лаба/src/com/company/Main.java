package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Main {

    public static Scanner obj = new Scanner(System.in);

    public static void main(String[] args) {

        List<Vehicle> vehicles = new LinkedList<>();
    //    Car car = new Car("ba","sdfg","123456789",2000,"sdfgd");
        vehicles.add(new Car("bmw","red","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","red","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","red","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","black","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","blue","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","white","123456789",2000,"sdfgd"));
        vehicles.add(new Car("opel","white","123456789",2000,"sdfgd"));
        vehicles.add(new Car("opel","white","123456789",2000,"sdfgd"));
        vehicles.add(new Car("opel","black","123456789",2000,"sdfgd"));

        int select;
        do {
            System.out.println("");
            select = obj.nextInt();
            switch (select){
                case 1:
                    try {
                        vehicles.add(input());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    for (Vehicle vehicle: vehicles) {
                        vehicle.output();
                    }
                    break;
                    case 3:
                        try {
                            editing(vehicles);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    break;
                case 4:
                    try {
                        remove(vehicles);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    for (Vehicle vehicle: readFile()) {
                        vehicle.output();
                    }
                    break;
                case 6:
                    sort(vehicles);
                    break;
                case 7:
                    countЕheNumberJfColorsOfEachBrand(vehicles);
                    break;
            }
        }while(true);
    }

    public static void countЕheNumberJfColorsOfEachBrand(List<Vehicle> vehicles){
        List<Vehicle> vehicles1 = new LinkedList<>();
        vehicles1.addAll(vehicles);

        for (int i = 0;i < vehicles1.size();i++) {
            List<Vehicle> vehiclesByBrand = searchBrand(vehicles1,vehicles1.get(i).getStringBrand());
            //for (Vehicle vehicle: vehiclesByBrand) {
            int amountOfColors = 0;
              for(int j = 0;j<vehiclesByBrand.size();j++){
                System.out.println(vehiclesByBrand.get(i).getStringBrand() + " "+vehiclesByBrand.get(j).getColor()+" - "+searchColor(vehiclesByBrand,vehiclesByBrand.get(j).getColor()).size());
              //  vehicles1.removeAll(searchColor(vehiclesByBrand,vehiclesByBrand.get(j).getColor()));
                j+= searchColor(vehiclesByBrand,vehiclesByBrand.get(j).getColor()).size() - 1;
               // vehiclesByBrand.removeAll(searchColor(vehiclesByBrand,vehiclesByBrand.get(j).getColor()));
                //j+= searchColor(vehiclesByBrand,vehiclesByBrand.get(j).getColor()).size();
                //vehiclesByBrand.removeAll(searchColor(vehiclesByBrand,vehicle.getColor()));
                  amountOfColors++;
            }
            System.out.println("Colors "+vehicles1.get(i).getStringBrand() +" - "+amountOfColors);
              vehicles1.removeAll(vehiclesByBrand);
        }
    }
    public static List<Vehicle> searchBrand(List<Vehicle> vehicles,String brand){
        List<Vehicle> found = new LinkedList<>();
        for (Vehicle vehicle: vehicles) {
            if(vehicle.getStringBrand() == brand) {
                found.add(vehicle);
            }
        }
        return found;
    }
    public static List<Vehicle> searchColor(List<Vehicle> vehicles,String color){
        List<Vehicle> found = new LinkedList<>();
        for (Vehicle vehicle: vehicles) {
            if(vehicle.getColor() == color) {
                found.add(vehicle);
            }
        }
        return found;
    }
    public static void sort(List<Vehicle> vehicles){
       // vehicles.sort(vehicles.com(Vehicle::getBrand);
        vehicles = (List<Vehicle>) vehicles.stream().sorted(Comparator.comparing(Vehicle::getBrand)
        ).collect(Collectors.toList());
        for (Vehicle vehicle :vehicles){
            vehicle.output();
        }
    }
    
    public static Vehicle input() throws Exception {
        System.out.println("Введіть марку");
        String brand = obj.next();
        System.out.println("Введіть колір");
        String color = obj.next();
        System.out.println("Введіть номер телефону");
        String PhoneNumber = obj.next();
        System.out.println("Введіть рік випуску");
        int year = obj.nextInt();
        System.out.println("Введіть дані про власника");
        String data = obj.next();


        char phone[] = PhoneNumber.toCharArray();

        if (phone.length < 10) throw new IOException("er");
        if (phone.length > 10 && phone.length != 13) throw new IOException("er");

        if (phone.length == 13 && phone[0] != '+') throw new IOException("er");


        System.out.println(year +"o - "+(new Date()).getYear());
        if (year < 1800) throw new IOException("Рік введено некоректно");
        if (year > 2021) throw new IOException("Рік введено некоректно");
        System.out.println("Введіть дані про власника");
        return new Car(brand, color, PhoneNumber, year, data);


    }
    public static void editing(List<Vehicle> vehicles) throws Exception {
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ":");
            vehicles.get(i).output();
        }
        System.out.println("");
        int i = obj.nextInt();
        if (i < 0) throw new Exception("");
        if (i > vehicles.size()) throw new Exception("");
        vehicles.get(i).edit();
    }
    public static void remove(List<Vehicle> vehicles) throws Exception{
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ":");
            vehicles.get(i).output();
        }
        System.out.println("");
        int index = obj.nextInt();
            if (index < 0) throw new Exception("");
            if (index > vehicles.size()) throw new Exception("");
            vehicles.remove(index);
    }
    public static List<Vehicle> readFile(){
        List<Vehicle> vehicles = new LinkedList<>();

        String str = "";
        try(BufferedReader br = new BufferedReader(new FileReader("info.txt"))){
            int c;
            while ((c = br.read()) != -1) {
                str += (char)c;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        for (String string: str.split("\n")) {
            vehicles.add(new Car(string));
        }
        return vehicles;
    }



}
