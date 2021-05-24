package com.company;

import javafx.beans.binding.ListBinding;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Main {
    private static Scanner obj = new Scanner(System.in);
    public static void main(String[] args) {

        List<String> words = new LinkedList<String>();

        int i = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
            int c;
            String temp = new String();
            while ((c = br.read()) != -1){
                if(c != ' ') temp += (char) c;
                else {
                    words.add(temp);
                    temp = new String();
                }
            }
            words.add(temp);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (String word:words) System.out.println(word);

        int select;
        do{
            System.out.println("1 Видалити всі слова, що починаються з голосних літер");
            System.out.println("2 підраховує кількість слів у тексті");
            System.out.println("3 Вихід");
            select = obj.nextInt();

            switch (select){
                case 1:
                    Task1(words);
                    break;
                case 2:
                    System.out.println("Кількіть слів у тексті - "+words.size());
                    break;
            }
        }while(select!=3);

    }


    public static void Task1( List<String> words){
        List<String> temp = new LinkedList<>();

        int amount = 0;
        for (String word:words){

            if ( !(word.toCharArray()[0] == 'а' ||  word.toCharArray()[0] == 'о' ||  word.toCharArray()[0] == 'у' ||  word.toCharArray()[0] == 'е' ||  word.toCharArray()[0] == 'и' ||  word.toCharArray()[0] == 'і'))
            {
               temp.add(word);
            }else amount++;
        }

        System.out.println("Всього слів видалено - "+amount);

        try (BufferedWriter bw=new BufferedWriter(new FileWriter("output.txt"))){
            for(String word:temp) bw.write(word + " ");//str += word + " ";
        }catch(IOException ex) {
            new Exception(ex.getMessage());
        }
    }
}
