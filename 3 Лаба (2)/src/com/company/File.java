package com.company;
import java.util.Date;

public class File {
    private String name;
    private String expansion;
    private int size;

    private String dateOfCreation = "";
    private String dateOLastChange = "";


    public File(String data) {
        String[] words = data.split(" ");
        name = words[0];
        expansion = words[1];
        size = Integer.parseInt(words[2]);

        char [] ch = data.toCharArray();
        for (int i = data.indexOf('<') +1;i< data.indexOf('>');i++){
            dateOfCreation+=ch[i];
        }

        for (int i =  data.lastIndexOf('<') +1;i<  data.lastIndexOf('>');i++){
            dateOLastChange+=ch[i];
        }
    }


    public File(String fileName, String expansion, int size) throws  Exception{
        if(size<0) throw new Exception("Розмір не може бути від'ємним");
        dateOfCreation = new Date().toString();
        dateOLastChange = new Date().toString();
        name= fileName;
        this.expansion = expansion;
        this.size= size;
    }

    public void Change(String fileName, String expansion, int size) throws  Exception{
        if(size<0) throw new Exception("Розмір не може бути від'ємним");
        dateOLastChange = new Date().toString();
        name= fileName;
        this.expansion = expansion;
        this.size= size;

    }
    public  boolean compare(File obj){
        return obj.expansion.toCharArray()[1] > expansion.toCharArray()[1];
    }
    public boolean equals(String year, String age){
        return  dateOfCreation.contains(year) && dateOfCreation.contains(age);
    }
    @Override
    public String toString(){
        return "\nНазва - "+name+"\nРозширення - "+expansion+"\nРозмір - "+size+"\nДата створення - "+dateOfCreation+"\nДата останіх змін - "+dateOLastChange;
    }

    public String toSave(){
        return name+" "+expansion+" "+ size+" <"+dateOfCreation+"> <"+dateOLastChange+">\n";
    }



}
