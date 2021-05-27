package com.company;

import java.io.*;

public class Main {


    public static void rewriteFile(String file) throws  Exception{
        String str = new String();
        int begin = 0;
        int end = -1;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            int c;
            while ((c = br.read()) != -1){
                str += (char) c;
            }

        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }

        char[] mass = str.toCharArray();

        for (int i =0;i<mass.length;i++){
            if(mass[i] == '.') begin = i;
            if(mass[i] == '!'){
                end = i;
                break;
            }
        }

        if(end == -1 )throw new Exception("Немає  рядка в кінці якого знаходиться символ \"!\"");

        String temp = new String();

        for (int i = 0;i < mass.length;i++){
            if(i < begin || i > end)temp+=mass[i];
        }


        try (BufferedWriter bw=new BufferedWriter(new FileWriter("newSomeFile.txt"))){
            bw.write(temp);
        }catch(IOException ex) {
            new Exception(ex.getMessage());
        }

        System.out.println("Дані успішно перезаписано");
    }

    public static void main(String[] args) {

        try {
            rewriteFile("someFile.txt");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
