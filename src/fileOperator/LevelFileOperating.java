package fileOperator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.AllLevels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LevelFileOperating {
    public void  jasonWriter(AllLevels allLevels){
        GsonBuilder builder=new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        String s=gson.toJson(allLevels);
        // System.out.println(s);
        writeToFile(s,false);

    }
    public int writeToFile(String string,boolean append){
        try {
            File file =new File("resource\\"+"mission.txt");
            if(!file.exists())file.createNewFile();
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(string);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public AllLevels readFile( AllLevels allLevels){
        File file =new File("resource\\"+"mission.txt");
        String output="";
        try {
            Scanner scanner =new Scanner(file);

            while (scanner.hasNextLine()){
                output+=scanner.nextLine();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        GsonBuilder builder=new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        allLevels=gson.fromJson(output, AllLevels.class);
        System.out.println("ok");
        return allLevels;
    }
    public AllLevels reloadLevels (AllLevels allLevels){
        try{
                return this.readFile(allLevels);
        }
        catch (Exception e){
            System.out.println("File Mission Damaged");
            e.printStackTrace();
        }
        return new AllLevels();
    }
}
