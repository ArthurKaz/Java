package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Student {

    private String name;
    private String surname;
    private int creditBookNumber;
    private List<Lesson> lessons = new ArrayList<Lesson>();

    @Override
    public String toString(){
    return "\nІм'я " + name+"\nПрізвище "+surname+"\nНомер залікової книжки "+creditBookNumber + "\n" + "Середній бал "+middleScore();
    }

    public String toTableString(){
        return  name+"\t "+surname+"\t"+creditBookNumber + "\t"+middleScore();
    }


    public  boolean equals(Student obj){
        return obj.middleScore() > middleScore();
    }



    public Student(String name,String surname,int creditBookNumber){
        this.name = name;
        this.surname = surname;
        this.creditBookNumber = creditBookNumber;
    }

    public void addLesson(){
        int select;
        Scanner obj = new Scanner(System.in);
        do{

            System.out.println("1. Додати предмет");
            System.out.println("2. Вихід");
            select = obj.nextInt();

            switch (select){
                case 1:
                    String name;
                    int score;
                    System.out.println("Введіть назву предмета");
                    name = obj.next();
                    System.out.println("Введіть оцінку");
                    score = obj.nextInt();

                    try{
                        lessons.add(new Lesson(name,score));
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }while (select!=2);
    }

    public int middleScore(){
        int allScores = 0;
        for (Lesson lesson:lessons) {
            allScores += lesson.score;
        }
        return allScores/lessons.size();
    }

    public boolean isUnsatisfactoryScore(int min) throws Exception{
        if(min <0) throw new Exception("Поріг не може бути від'ємним");
        if(min > 80) throw new Exception("Поріг не може бути вище 80");
        for (Lesson lesson : lessons) {
            System.out.println(lesson.score);
            if(lesson.score < min) return true;
        }
        return false;
    }

    private class Lesson{
        public String name;
        public int score;


        public Lesson(String name,int score) throws Exception{
            if(score > 100) throw new Exception("Оцінка не може бути більше ніж 100");
            if (score < 0)throw  new Exception("Оцінка не може бути від'ємною");
            this.name = name;
            this.score = score;
        }
    }


}
