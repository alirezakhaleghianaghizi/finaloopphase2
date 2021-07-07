package menus;

import controller.MainController;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    protected String name;
    protected Menu parentMenu;
    protected HashMap<Integer, Menu> submenus;
    public static Scanner scanner;
    public MainController manager;
    public Logger logger;
    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        this.manager=new MainController();
        this.submenus=new HashMap<>();
        this.logger=new Logger();
        this.manager.personsController=this.manager.personsController.reloadUsers.readFile(this.manager.personsController);
    }

    public Menu(String name) {
        this.name = name;
    }

    public Menu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public void show() {
        System.out.println("");
        System.out.print(Color.GREEN_BOLD_BRIGHT);
        System.out.println(this.name + ": ");
        System.out.print(Color.RESET);
        System.out.println("");
        for (Integer integer : submenus.keySet()) {
            System.out.print(Color.CYAN_BOLD_BRIGHT);
            System.out.print(integer + ". " );
            System.out.print(Color.RESET);
            System.out.println( submenus.get(integer).getName());
        }
        if (this.parentMenu == null) {
            System.out.print(Color.CYAN_BOLD_BRIGHT);
            System.out.print((submenus.size() + 1));
            System.out.print(Color.RESET);
            System.out.println( ". exit");
        } else {
            System.out.print(Color.CYAN_BOLD_BRIGHT);
            System.out.print((submenus.size() + 1) );
            System.out.print(Color.RESET);
            System.out.println( ". back");
        }
        System.out.println("");
    }

    public void execute() {
        Menu nextMenu;
        int nextMenuNum;

        while (true) {
            try{
                nextMenuNum= Integer.parseInt(scanner.nextLine());
                if (nextMenuNum == submenus.size() + 1) {
                    if (this.parentMenu == null) {
                        System.exit(1);
                    } else {
                        nextMenu = parentMenu;
                        break;
                    }
                } else if (nextMenuNum < submenus.size() + 1 && nextMenuNum > 0) {
                    nextMenu = submenus.get(nextMenuNum);
                    break;
                } else {
                    System.err.println("Invalid input!");
                    logger.lastChange=new Date();
                    this.logger.commands.add("Error,"+logger.lastChange.toString()+",Invalid input in menu bar");
                }
            }catch(NumberFormatException e){
                System.err.println("please enter correct format of number to choose ");
                logger.lastChange=new Date();
                this.logger.commands.add("Error,"+logger.lastChange.toString()+",Number format exception in menu bar. ");

            }
        }
        nextMenu.show();
        nextMenu.execute();
    }

    public void setSubmenus(HashMap<Integer, Menu> submenus) {
        this.submenus = submenus;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public String getName() {
        return name;
    }


}
