package com.company;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class Main {
    public static Scanner obj = new Scanner(System.in);
    public static void main(String[] args) {

        List<Vehicle> vehicles = new LinkedList<>();
        vehicles.add(new Car("opel","white","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","red","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","red","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","blue","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","white","123456789",2000,"sdfgd"));
        vehicles.add(new Car("opel","white","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","red","123456789",2000,"sdfgd"));
        vehicles.add(new Car("opel","black","123456789",2000,"sdfgd"));
        vehicles.add(new Car("bmw","black","123456789",2000,"sdfgd"));
        int select;
        do {
            System.out.println("1. Додати дані");
            System.out.println("2. Вивести всі дані");
            System.out.println("3. Редагувати дані");
            System.out.println("4. Знищити запис");
            System.out.println("5. Вивести на екран дані з файла");
            System.out.println("6. Сортувати дані по марках");
            System.out.println("7. Вивести кільксть різних кольорів кожної марки");
            System.out.println("8. Зберегти та вийти");
            System.out.println("9. Вийти");
            select = obj.nextInt();
            switch (select){
                case 1:
                    try {
                        vehicles.add(input());
                        System.out.println("Дані успішно додано в список");
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
                        System.out.println("Дані успішно видалено із списку");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    for (Vehicle vehicle: readFile("data.txt")) {
                        vehicle.output();
                    }
                    break;
                case 6:
                    sort(vehicles);
                    break;
                case 7:
                    countЕheNumberOfColorsOfEachBrand(vehicles);
                    break;
                case 8:
                  save(vehicles,"data.txt");
                    break;
            }
        }while(select != 8&& select!=9);
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

        if (phone.length < 10) throw new IOException("Номер телефону введено некоректно: кількість символів повинна бути не менша за 10");
        if (phone.length > 10 && phone.length != 13) throw new IOException("Номер телефону введено некоректно: кількість символів повинна бути не більшою за 13");
        if (year < 1800) throw new IOException("Рік введено некоректно");
        if (year > 2021) throw new IOException("Рік введено некоректно");

        return new Car(brand, color, PhoneNumber, year, data);
    }
    public static void editing(List<Vehicle> vehicles) throws Exception {
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ":");
            vehicles.get(i).output();
        }
        System.out.println("Виберіть порядковий номер, який бажаєте змінити");
        int i = obj.nextInt();
        if (i < 0) throw new Exception("Порядковий номер не може бути від'ємним");
        if (i > vehicles.size()) throw new Exception("Введенего порядкового номера не знайдено");
        vehicles.get(i - 1).edit();
    }
    public static void remove(List<Vehicle> vehicles) throws Exception{
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ":");
            vehicles.get(i).output();
        }
        System.out.println("иберіть порядковий номер, який бажаєте видалити");
        int index = obj.nextInt();
        if (index < 0) throw new Exception("Порядковий номер не може бути від'ємним");
        if (index > vehicles.size()) throw new Exception("Введенего порядкового номера не знайдено");
            vehicles.remove(index - 1);
    }
    public static List<Vehicle> readFile(String fileName){
        List<Vehicle> vehicles = new LinkedList<>();

        String str = "";
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
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
    public static void sort(List<Vehicle> vehicles){
        vehicles = (List<Vehicle>) vehicles.stream().sorted(Comparator.comparing(Vehicle::getBrand)
        ).collect(Collectors.toList());
        for (Vehicle vehicle :vehicles){
            vehicle.output();
        }
    }
    public static void countЕheNumberOfColorsOfEachBrand(List<Vehicle> vehicles){
        List<Vehicle> vehicles1 = new LinkedList<>();
        vehicles1.addAll(vehicles);
        for (int i = 0;i < vehicles1.size();i++) {
            List<Vehicle> vehiclesByBrand = searchBrand(vehicles1,vehicles1.get(i).getStringBrand());
            int amountOfColors = 0;
            for(int j = 0;j<vehiclesByBrand.size();j++){
                j+= searchColor(vehiclesByBrand,vehiclesByBrand.get(j).getColor()).size() - 1;
                amountOfColors++;
            }
            System.out.println("Кількість кольорів марки "+vehicles1.get(i).getStringBrand() +" - "+amountOfColors);
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
    public static void save(List<Vehicle> vehicles, String fileName){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Vehicle performance : vehicles) bw.write(performance.toSave());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Дані успішно записано");
    }
}
