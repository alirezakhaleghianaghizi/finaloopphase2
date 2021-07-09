package sample;

import controller.MainController;
import model.Person;

import java.sql.*;
import java.util.Scanner;

public class MyFirstJDBC {
    String url="jdbc:mysql://localhost:3306/people";
    String username="root";
    String password="99101462";
    Connection connection;
    MainController controller;

    public MyFirstJDBC(MainController controller) {
        this.controller = controller;
    }

    public boolean addUser(String user,String pass) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select username from user where username =" + "'" + user + "'");
            if (resultset.next()) return false;
            else {
                String usernameIn = user;
                String passwordIn = pass;
                String maxLevel = "0";
                String currentLevel = "0";
                String coins = "0";
                String total_coins = "0";
                String sql = "INSERT INTO user" +
                        " (username,password,max_pass_level,current_level,coins,total_coins)" +
                        "values('" + usernameIn + "','" + passwordIn + "'," + maxLevel + "," + currentLevel + "," + coins + "," + total_coins + ");";
                this.controller.personsController.CurrentUser=new Person(user,pass,0);
                this.controller.personsController.CurrentUser.currentLevel=this.controller.allLevels.levels.get(0);
                this.controller.personsController.isAnyOneInTheGame=true;
                statement.executeUpdate(sql);
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int reloadUser(String user,String pass) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from user where username =" + "'" + user + "' and password ="+"'"+pass+"'");
            if (!resultset.next()) return -1;
            else {
                Person person =new Person(user,pass,0);
                person.level=Integer.parseInt(resultset.getString("max_pass_level"));
                person.coins=Integer.parseInt(resultset.getString("coins"));
                person.totalCoins=Integer.parseInt(resultset.getString("total_coins"));
                person.currentLevel=this.controller.allLevels.levels.get(Integer.parseInt(resultset.getString("current_level")));
                this.controller.personsController.CurrentUser=person;
                this.controller.personsController.isAnyOneInTheGame=true;
                return 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -2;
    }

    public int newUser() {
        try {
            String user=this.controller.personsController.CurrentUser.userName;
            String pass;
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select username from user where username =" + "'" + user + "'");
            if (!resultset.next()) return -1;
            else {
                String sql="UPDATE user SET coins ="+this.controller.personsController.CurrentUser.coins+" WHERE username="+"'"+user+"'";
                 statement.executeUpdate(sql);
                  sql="UPDATE user SET total_coins="+this.controller.personsController.CurrentUser.totalCoins+" WHERE username="+"'"+user+"'";
                 statement.executeUpdate(sql);
                  sql="UPDATE user SET max_pass_level="+this.controller.personsController.CurrentUser.level+" WHERE username="+"'"+user+"'";
                 statement.executeUpdate(sql);
                  sql="UPDATE user SET current_level="+(this.controller.allLevels.levels.indexOf(this.controller.personsController.CurrentUser.currentLevel)+1)+" WHERE username="+"'"+user+"'";
                 statement.executeUpdate(sql);
                return 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }


    public static void main(String[] args) {

        try {
            String url="jdbc:mysql://localhost:3306/people";
            String username="root";
            String password="99101462";
            Scanner scanner =new Scanner(System.in);
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement =connection.createStatement();
            for (int i = 0; i <1 ; i++) {
                String usernameIn=scanner.nextLine();
                String passwordIn=scanner.nextLine();
                String maxLevel=scanner.nextLine();
                String currentLevel=scanner.nextLine();
                String coins=scanner.nextLine();
                String total_coins=scanner.nextLine();
                String sql="INSERT INTO user" +
                " (username,password,max_pass_level,current_level,coins,total_coins)" +
                "values('"+usernameIn+"','"+passwordIn+"',"+maxLevel+","+currentLevel+","+coins+","+total_coins+");";
                statement.executeUpdate(sql);
            }
            //String sql="DELETE FROM people  WHERE  id ="+i;
            //int a=19;
            //int b=50;
            //String sql="UPDATE people SET id="+b+" WHERE id="+a;
           // statement.executeUpdate(sql);
            ResultSet resultset =statement.executeQuery("select * from user");
            while (resultset.next()){
                System.out.println(resultset.getString("username") +"\t"+resultset.getString("password")+"\t"+ resultset.getString("max_pass_level")+"\t"+ resultset.getString("current_level")+"\t"+ resultset.getString("coins")+"\t"+ resultset.getString("total_coins") );
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        }
    }