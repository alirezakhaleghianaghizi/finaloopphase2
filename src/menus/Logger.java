package menus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Logger {
    public Date dateStartOperatingLogger;
    public String userName;
    public Date lastChange;
    public ArrayList<String> commands;

    public Logger( ) {
        this.dateStartOperatingLogger =new Date();
        this.userName = null;
        this.commands = new ArrayList<>();
    }
    public void  jasonWriter(Logger logger){
        GsonBuilder builder=new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        String s=gson.toJson(logger);
        // System.out.println(s);
        writeToFile(s,false);

    }
    public int writeToFile(String string,boolean append){
        try {
            File file =new File("resource\\"+"Logger.txt");
            if(!file.exists())file.createNewFile();
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(string);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
