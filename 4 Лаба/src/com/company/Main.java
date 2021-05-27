package com.company;
import java.io.*;
import java.util.*;

public class Main {

    private static Scanner obj = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here

        List<Performance> performances = new LinkedList<>();

        performances.add(new Performance("Авдонін", "Українська,", 4, new Date(2004,5,15), "asdf", 81));
        performances.add(new Performance("Базика", "Українська", 9, new Date(2008,5,15), "asdf", 24));
        performances.add(new Performance("Боженко", "Українська", 6, new Date(), "asdf", 25));
        performances.add(new Performance("Хінчегашвілі", "Українська", 45, new Date(), "asdf", 25));
        performances.add(new Performance("Жуковська", "Українська", 25, new Date(2004,5,15), "asdf", 45));
        performances.add(new Performance("Каннінгем", "Українська", 25, new Date(2004,5,15), "asdf", 40));



        int select;
        do{
            System.out.println("1. Додати дані");
            System.out.println("2. Вивести всі дані");
            System.out.println("3. Редагування записів");
            System.out.println("4. Видалення записів");

            System.out.println("5. Виведення інформації з файла на екран");
            System.out.println("6. Сумарна кількість слухачів");
            System.out.println("7. Вивести дату з найбільшою кількістю слухачів");
            System.out.println("8. Вивести записи з довжиною прізвища");

            System.out.println("9. Зберегти дані та вийти");
            System.out.println("10. Вийти");


            select =obj.nextInt();
            switch (select){
                case 1 :
                    try{
                        performances.add(input());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    for (Performance performance:performances) {
                        System.out.println(performance.toString());
                    }
                    break;
                case 3:
                        for (int i = 0; i < performances.size();i++) {
                            System.out.println((i + 1) + " :");
                            System.out.println(performances.get(i).toString());

                        }
                        try {
                            System.out.println("Введіть поярдковий номер для зміни");
                            int edit = obj.nextInt();
                            if (edit< 1) throw new IOException("Порядковий номер не може бути відємний або нульовий");//System.out.println();
                            if (edit> performances.size()) throw new IOException("Введенего порядкового номера не знайдено");//System.out.println();
                            performances.set(edit - 1,input());

                        }catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    break;
                case 4:
                    for (int i = 0; i < performances.size();i++){
                        System.out.println("\n"+(i +1) + " :");
                        System.out.println(performances.get(i).toString());

                    }
                    try {
                        System.out.println("Введіть порядковий номер");
                        int remove = obj.nextInt();
                        if (remove < 1) throw new IOException("Порядковий номер не може бути відємний або нульовий");//System.out.println();
                        if (remove > performances.size()) throw new IOException("Введенего порядкового номера не знайдено");//System.out.println();
                        performances.remove(remove-1);
                    }catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5 :
                    for (Performance item:readFile()) {
                        System.out.println(item.toString());
                    }
                    break;
                case 6:

                    int summa =0;
                    for (Performance item:performances) {
                        summa += item.getAmountOfListener();
                    }
                    System.out.println("Сумарна кільксть слухачів - "+summa);
                    break;
                case 7:
                    try {

                        System.out.println("Дата з найбльшою кількістю слухачів - " + searchDate(performances));
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    for (Performance item:performances) {
                        System.out.println(item.toString()+ "\nДовжина прізвища - "+item.getLengthOfSurname());
                    }
                    break;
                case 9:
                    try (BufferedWriter bw=new BufferedWriter(new FileWriter("info.txt"))){
                        for(Performance performance : performances) bw.write(performance.toSave());
                    }catch(IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                    System.out.println("Дані успішно записано");
                    break;
            }
        }while (select!=9 && select!=10);


    }


    public static Date searchDate(List<Performance> performances) throws IOException{

        if(performances.size() ==0)throw new IOException("Спочатку введіть дані");
        Date date = performances.get(0).getDate();
        int Listeners = calculateTheAmountOfListenersByDate(performances,date);
        for (Performance item: performances ) {
            if(Listeners < calculateTheAmountOfListenersByDate(performances,item.getDate())) Listeners = calculateTheAmountOfListenersByDate(performances,item.getDate());
        }
        return date;
    }

    public static int calculateTheAmountOfListenersByDate(List<Performance> performances,Date date){
        int result = 0;

        for (Performance item:performances) {
            if(date == item.getDate()) result+=item.getAmountOfListener();
        }
        return result;


    }


    public static Performance input() throws IOException{
        System.out.println("Введіть прізвище автора");
        String surname = obj.next();
        System.out.println("Введіть мову ");
        String language = obj.next();
        System.out.println("Введіть кількість збірок");
        int amountOfCollection = obj.nextInt();
        System.out.println("Введіть дату");

        System.out.println("Введіть день");
        int date = obj.nextInt();
        if(date < 1 || date > 31) throw new IOException("Некоректно введено день");
        System.out.println("Введіть місяць");
        int month = obj.nextInt();
        if(month < 1|| month > 12) throw new IOException("Некоректно введено місяць");
        System.out.println("Введіть рік");
        int year = obj.nextInt();
        if(year < 1900 || year > 2030) throw new IOException("Некоректно введено рік");

        System.out.println("Введіть місце");
        String place = obj.next();
        System.out.println("Введіть кількість слухачів");
        int amountOfListener = obj.nextInt();
        return new Performance(surname,language,amountOfCollection,new Date(year,month,date),place,amountOfListener);
    }
    public static List<Performance> readFile(){
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

        List<Performance> performances = new LinkedList<>();

        for (String string: str.split("\n")) {
            performances.add(new Performance(string));
        }
        return performances;
    }
}

