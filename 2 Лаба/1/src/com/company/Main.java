package com.company;
import java.util.Scanner;

public class Main {
    private static Scanner obj = new Scanner(System.in);


    public static String[] inputWords( String str){
        int spaces = 0;
        for (char letter : str.toCharArray()){
            if(letter == ' ') spaces++;
        }

        String[] words  = new String[spaces + 1];
        words[0] = new String();

        int i = 0;
        for (char letter : str.toCharArray()) {
            if (letter == ' '){ i++;
                words[i] = new String();
            }
            else words[i] += letter;
        }
        return words;
    }


    public static int Task1(String[] words) {
        int sameVowelsAndConsonant = 0;


        for (String word : words) {
            System.out.println("Слово - " + word);
            int amountOfConsonantLetters = 0;
            int amountOfVowels = 0;
            for (char letter : word.toCharArray()) {
                if (letter == 'а' || letter == 'о' || letter == 'у' || letter == 'е' || letter == 'и' || letter == 'і')
                    amountOfVowels++;
                else amountOfConsonantLetters++;
            }
            System.out.println("Кількість голосних - " + amountOfVowels);
            System.out.println("Кількість приголосних - " + amountOfConsonantLetters);
            if (amountOfConsonantLetters == amountOfConsonantLetters) sameVowelsAndConsonant++;
        }
        return sameVowelsAndConsonant;
    }
    public static String Task2(String[] words){
        String biggestWord = "";
        for (String word : words) if(word.length() > biggestWord.length())biggestWord = word;
        return biggestWord;

    }
    public static void main(String[] args) {
        int select;
        System.out.println("Введіть рядок");
        String str = obj.nextLine();
        String[] words = new String[0];
        words = inputWords(str);

        do {

            System.out.println("1. Вивести кількість слів, які містять однакову кількість голосних і приголосних літер; ");
            System.out.println("2. Вивести на екран  найдовше слово");
            select = obj.nextInt();

            switch (select){
                case 1 :
                    System.out.println("Кількість слів, які містять однакову кількість голосних і приголосних літер - "+ Task1(words));
                    break;
                case 2 :
                    System.out.println("найдовше слово - " + Task2(words));
                    break;
            }

        }while (select != 3 );
    }
}
