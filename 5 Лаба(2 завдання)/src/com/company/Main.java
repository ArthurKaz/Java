package com.company;

import javax.print.Doc;
import java.io.*;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static Scanner obj = new Scanner(System.in);

    public static void main(String[] args) {
        List<Doctor> doctors = new LinkedList<>();
        doctors.add(new WorkingDay("asff","asdf",new Date(2004, 5, 5),65,new Time(5,5,5)));
        doctors.add(new WorkingDay("asff","asdf",new Date(2007, 5, 5),1,new Time(15,5,5)));
        doctors.add(new WorkingDay("asff","asdf",new Date(2004, 4, 5),2,new Time(7,5,5)));
        doctors.add(new WorkingDay("asff","asdf",new Date(2020, 11, 5),65,new Time(9,5,5)));
        int select;
        do {
            System.out.println("1. Додати дані");
            System.out.println("2. Вивести всі дані");
            System.out.println("3. Редагувати дані");
            System.out.println("4. Знищити запис");
            System.out.println("5. Вивести на екран дані з файла");
            System.out.println("6. Середня кількість пацієнтів");
            System.out.println("7. День з максимальним навантаженням");
            System.out.println("8. Дні, коли починав приймати після зазначеного часу");
            System.out.println("9. Зберегти та вийти");
            System.out.println("10. Вийти");
            select = obj.nextInt();
            switch (select) {
                case 1:
                    try {
                        doctors.add(input());
                        System.out.println("Дані успішно додано в список");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    for (Doctor doctor : doctors) {
                        doctor.output();
                    }
                    break;
                case 3:
                    try {
                        editing(doctors);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        remove(doctors);
                        System.out.println("Дані успішно видалено із списку");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    for (Doctor doctor : readFile("data.txt")) {
                        doctor.output();
                    }
                    break;
                case 6:
                    System.out.println("Середня кільксть пацієнтів - " + middle(doctors));
                    break;
                case 7:
                    Date date = maxLoad(doctors);
                    System.out.println("День з максимальним навантаженням - "+date.getDay() + "."+date.getMonth()+"."+date.getYear());
                    break;
                case 8:
                    System.out.println("Введення часу початку роботи");
                    System.out.println("Введіть годину");
                    int hours = obj.nextInt();
                    System.out.println("Введіть хвилину");
                    int minutes = obj.nextInt();
                    Time startTime = new Time(hours, minutes, 0);
                    System.out.println("Дні, коли починав приймати після зазначеного часу:");
                    for (Date date1 : theDaysWhenHeStartedTakingAfterTheSpecifiedTime(doctors,startTime)){
                        System.out.println(date1.getDate()+"."+date1.getMonth()+"."+date1.getYear());
                    }
                    break;
                case 9:
                    save(doctors, "data.txt");
                    break;
            }
        } while (select != 9 && select != 10);
    }

    public static Doctor input() throws Exception {
        System.out.println("Введіть ПІБ лікаря");
        String PIB = obj.next();
        System.out.println("Введіть спеціальність");
        String specialty = obj.next();
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


        System.out.println("Введіть кільксть пацієнтів");
        int amountOfPatient = obj.nextInt();
        System.out.println("Введення часу початку роботи");
        System.out.println("Введіть годину");
        int hours = obj.nextInt();
        System.out.println("Введіть хвилину");
        int minutes = obj.nextInt();

        if (minutes > 59 || minutes < 0) throw new Exception("Некорекно введена хвилина");
        if (hours > 24 || hours < 0) throw new Exception("Некорекно введена година");
        Time startTime = new Time(hours, minutes, 0);

        return new WorkingDay(PIB, specialty, new Date(year, month, date), amountOfPatient, startTime);
    }

    public static void editing(List<Doctor> doctors) throws Exception {
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ":");
            doctors.get(i).output();
        }
        System.out.println("Виберіть порядковий номер, який бажаєте змінити");
        int i = obj.nextInt();
        if (i < 0) throw new Exception("Порядковий номер не може бути від'ємним");
        if (i > doctors.size()) throw new Exception("Введенего порядкового номера не знайдено");
        doctors.get(i - 1).edit();
    }

    public static void remove(List<Doctor> doctors) throws Exception {
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ":");
            doctors.get(i).output();
        }
        System.out.println("Виберіть порядковий номер, який бажаєте видалити");
        int index = obj.nextInt();
        if (index < 0) throw new Exception("Порядковий номер не може бути від'ємним");
        if (index > doctors.size()) throw new Exception("Введенего порядкового номера не знайдено");
        doctors.remove(index - 1);
    }

    public static List<Doctor> readFile(String fileName) {
        List<Doctor> doctors = new LinkedList<>();

        String str = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = br.read()) != -1) {
                str += (char) c;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (String string : str.split("\n")) {
            doctors.add(new WorkingDay(string));
        }
        return doctors;
    }

    public static void save(List<Doctor> doctors, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Doctor doctor : doctors) bw.write(doctor.toSave());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Дані успішно записано");
    }
    public static float middle(List<Doctor> doctors){
        int allPatient = 0;
        for (Doctor doctor: doctors) {
            allPatient += doctor.getAmountOfPatient();
        }
        return allPatient/doctors.size();
    }
    public static Date maxLoad(List<Doctor> doctors){
        int max = 0;
        for (int i = 0; i < doctors.size(); i++) {
            if(doctors.get(i).getAmountOfPatient() > doctors.get(max).getAmountOfPatient()) max = i;
        }
        return doctors.get(max).getDate();
    }
    public static List<Date> theDaysWhenHeStartedTakingAfterTheSpecifiedTime(List<Doctor> doctors,Time time){
        List<Date> temp = new LinkedList<>();
        for (Doctor doctor: doctors) {
            if(doctor.isOver(time)) temp.add(doctor.getDate());
        }
        return temp;
    }

}
