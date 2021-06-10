package com.company;

public class Main {

    public static void main(String[] args) throws Exception {

    }

    public static int min(int[]mas) throws Exception{
        int index = 0;
        if(mas.length == 0) throw new ArrayIndexOutOfBoundsException();
        for(int i = 1; i < mas.length;i++){
            if(Math.abs(mas[i]) < Math.abs(mas[index])) index = i;
        }
        return index;
    }
    public static int product(int[] mass)throws Exception{
        if(mass.length == 0) throw new ArrayIndexOutOfBoundsException("Масив не містить жодного елемента");
        int first = -1;
        int last = -1;
        for (int i = 0; i < mass.length;i++){
            if(mass[i] == 0){

                first = i;
                break;
            }
        }
        if(first == -1) throw new Exception("Не знайдено жодного нульового елемента");
        for (int i = first + 1; i < mass.length;i++){
            if(mass[i] == 0) {
                last = i;
            }
        }

        if(last == -1) throw new Exception("Не знайдено останього нульового елемента");
        if(first== last - 1) throw new Exception("Між першим і останім елементом немає елементів");
        int dob = 1;
        for (int i = first + 1; i < last;i++){
            dob *= mass[i];
        }
        return dob;
    }

    public static int[] wrap(int[]mass)throws Exception{
        if(mass.length == 0) throw new ArrayIndexOutOfBoundsException("Масив не містить жодного елемента");
        if(mass.length%2 != 0) throw new Exception("Кількість елементів масива повинна бути парною");

        int[] result = new int[mass.length];

        int distance = mass.length/2;
        for (int i =0 ;i < distance;i++) {
            result[i] = mass[distance + i];
            result[distance + i] = mass[i];
        }
        return  result;
    }
}
