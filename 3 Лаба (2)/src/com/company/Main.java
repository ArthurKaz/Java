package com.company;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class Main {
    private static Scanner obj = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        List<File> files = new LinkedList<File>();

        String str = new String();
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
            int c;
            while ((c = br.read()) != -1) {
                str += (char)c;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String [] strings = str.split("\n");

        for (String string: strings) {

            files.add(new File(string));
        }


        int select;
        do {
            System.out.println("1. Додати файл в список");
            System.out.println("2. Вивести всі файли");
            System.out.println("3. Редагувати записи");
            System.out.println("4. Cортувати записи (за розширенням)");
            System.out.println("5. Пошук");
            System.out.println("6. Вихід");
            select = obj.nextInt();
            switch (select) {

                case 1:

                    System.out.println("Введіть назву файла");
                    String name = obj.next();
                    System.out.println("Введіть розширення");
                    String expansion = obj.next();
                    System.out.println("Введіть розмір");
                    int size = obj.nextInt();

                    try {
                        files.add(new File(name,expansion,size));
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    for (File file: files) {
                        System.out.println(file.toString());
                    }
                    break;
                case 3: {
                        changeList(files);
                }   break;
                case 4:
                    for (int i = 0; i < files.size();i++){
                        for (int j = 1; j<files.size()-i;j++){
                            if(files.get(j).compare(files.get(j-1))) {
                                File tmp = files.get(j);
                                files.set(j, files.get(j -1));
                                files.set(j-1, tmp);
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Введіть рік");
                    String year = obj.next();
                    System.out.println("Введіть місяць(1-12)");
                    String month = obj.next();

                    boolean flag = false;
                    for (File file:files) {
                            if(file.equals(year,month)){
                                flag = true;
                                System.out.println(file.toString());
                            }
                    }
                    if(!flag) System.out.println("Збігів не знайдено");
                    break;
            }

        } while (select != 6);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt"))){
            for(File file : files) bw.write(file.toSave());
        }catch(IOException ex) {
            new Exception(ex.getMessage());
        }


    }
    public static void changeList(List<File> files){
        int select;
        int index = 0;
        do {


            System.out.println(files.get(index).toString());
            System.out.println("1. Наступий запис");
            System.out.println("2. Попередній запис");
            System.out.println("3. Редагувати запис");
            System.out.println("4. Видалити запис");
            System.out.println("5. Вихід");
            select=obj.nextInt();

            switch (select){
                case 1:
                    if(index + 1 >= files.size() ) {
                        System.out.println("Більше записів немає");

                    }
                    else {
                        index++;


                    }
                    break;
                case 2:
                    if(index - 1 <0) System.out.println("Більше записів немає");
                    else {

                        index--;
                    }
                    break;
                case 3:
                    System.out.println("Введіть назву файла");
                    String name = obj.next();
                    System.out.println("Введіть розширення");
                    String expansion = obj.next();
                    System.out.println("Введіть розмір");
                    int size = obj.nextInt();

                    try {
                        files.get(index).Change(name,expansion,size);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    files.remove(index);
                    index = 0;
                    break;
            }
        }
        while (select != 5);
    }
}
