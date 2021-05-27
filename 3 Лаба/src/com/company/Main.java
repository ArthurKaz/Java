package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static Scanner obj = new Scanner(System.in);
    public static void main(String[] args) {

        int select;

        List<Student> students = new ArrayList<Student>();


        do{
            System.out.println("1. Додати студента");
            System.out.println("2. Вивести всіх студентів");
            System.out.println("3. Впорядкувати записиу в порядку зростання середнього бала");
            System.out.println("4. Визначити відсоток студентів, що мають незадовільні оцінки");
            System.out.println("5. Вихід");

            select = obj.nextInt();

            switch (select) {
                case 1:
                    String name;
                    String surname;
                    int creditBookNumber;

                    System.out.println("Введіть ім'я студента");
                    name = obj.next();
                    System.out.println("Введіть прізвище студента");
                    surname = obj.next();
                    System.out.println("Введіть номер залікової книжки");
                    creditBookNumber = obj.nextInt();


                    Student temp = new Student(name, surname, creditBookNumber);
                    temp.addLesson();
                    students.add(temp);
                    break;
                case 2:

                    for (Student student : students) {
                        System.out.println(student.toString());
                    }


                    break;
                case 3:

                    for (int i = 0; i < students.size() ; i++) {

                        for (int j = 1; j < students.size() - i; j++) {
                            if (students.get(j).equals(students.get(j - 1))) {

                                Student tmp = students.get(j);
                                students.set(j, students.get(j -1));
                                students.set(j-1, tmp);
                            }
                        }

                    }
                    for (Student student : students) {
                        System.out.println(student.toTableString());
                    }
                    break;
                case 4:
                    int amount = 0;

                    System.out.println("Введіть поріг (оцінка нижча якої вважається незадовільна)");
                    int min = obj.nextInt();

                    for (Student student:students) {
                     try {
                         if(student.isUnsatisfactoryScore(min)) amount++;
                     }catch (Exception e) {System.out.println(e.getMessage());}
                    }

                    float percent = (float) amount/ (float)students.size()*100.f;
                    System.out.println("Відсоток студентів, що мають незадовільні оцінку - " + (int)percent+"%" );///students.size() * 100 );
                    break;
            }
        }while (select != 5);
    }
}
