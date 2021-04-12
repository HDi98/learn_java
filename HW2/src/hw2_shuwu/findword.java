package hw2_shuwu;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class findword {
    public static void main(String[] args) {
        File file = new File("data/wordsFile.txt");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder fileContent = new StringBuilder();
        while (fileScanner.useDelimiter("\n").hasNext()) {
            fileContent.append(fileScanner.next() + "\n");
        }
        fileScanner.close();

        String[] words = fileContent.toString().split("\n");
        for(String word : words){
            if(word.equals("cde")){
                System.out.println(word + "    true");
            }else if(word.equals("ced")){
                System.out.println(word + "    true");
            }else if(word.equals("dec")){
                System.out.println(word + "    true");
            }else if(word.equals("dce")){
                System.out.println(word + "    true");
            }else if(word.equals("edc")){
                System.out.println(word + "    true");
            }else if(word.equals("ecd")){
                System.out.println(word + "    true");
            }else if(word.equals("cdo")){
                System.out.println(word + "    true");
            }else if(word.equals("cod")){
                System.out.println(word + "    true");
            }else if(word.equals("dco")){
                System.out.println(word + "    true");
            }else if(word.equals("doc")){
                System.out.println(word + "    true");
            }else if(word.equals("odc")){
                System.out.println(word + "    true");
            }else if(word.equals("ocd")){
                System.out.println(word + "    true");
            }else if(word.equals("ceo")){
                System.out.println(word + "    true");
            }else if(word.equals("coe")){
                System.out.println(word + "    true");
            }else if(word.equals("eco")){
                System.out.println(word + "    true");
            }else if(word.equals("eoc")){
                System.out.println(word + "    true");
            }else if(word.equals("oec")){
                System.out.println(word + "    true");
            }else if(word.equals("oce")){
                System.out.println(word + "    true");
            }else if(word.equals("oec")){
                System.out.println(word + "    true");
            }else if(word.equals("deo")){
                System.out.println(word + "    true");
            }else if(word.equals("doe")){
                System.out.println(word + "    true");
            }else if(word.equals("ode")){
                System.out.println(word + "    true");
            }else if(word.equals("oed")){
                System.out.println(word + "    true");
            }else if(word.equals("eod")){
                System.out.println(word + "    true");
            }else if(word.equals("edo")){
                System.out.println(word + "    true");
            }
        }

    }
}
