package menus;

import java.util.Date;
import java.util.HashMap;

public class Login extends Menu {
    public Login(SignLogMenu signLogMenu) {
        super("LOGIN",signLogMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new StartMenu(this));
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {
        System.out.println("");
        System.out.print(Color.GREEN_BOLD_BRIGHT);
        System.out.println("LOG IN");
        System.out.print(Color.RESET);
        System.out.println("");
    }

    @Override
    public void execute() {
        if(logInFromFile()){
            this.submenus.get(1).show();
            this.submenus.get(1).execute();
        }
        else {
            this.parentMenu.show();
            this.parentMenu.execute();
        }
    }
    public boolean logInFromFile(){
        String userName;
        String password;
        this.manager.personsController=this.manager.personsController.reloadUsers.readFile(this.manager.personsController);
        if(this.manager.personsController.isAnyOneInTheGame){
            logger.lastChange=new Date();
            this.logger.commands.add("ERROR,"+logger.lastChange.toString()+",ALREADY LOGGED IN. ");
            System.out.println("");
            System.err.println("You have Already logged in");
            System.err.println("your userName is \" "+this.manager.personsController.getCurrentUser().userName+"\" \n your password is \" "+this.manager.personsController.getCurrentUser().password+"\"");
            System.out.println("");
            return true;
        }
        System.out.print(Color.CYAN_BOLD_BRIGHT);
        System.out.println("Enter Your User Name To login");
        userName=scanner.nextLine();
        if(this.manager.personsController.getUserNamePersonMap().containsKey(userName)){
            System.out.println("Enter your password");
            password=scanner.nextLine();
            while(!this.manager.personsController.getUserNamePersonMap().get(userName).password.equalsIgnoreCase(password)&&!password.equalsIgnoreCase("main")){
                if(!password.equalsIgnoreCase(this.manager.personsController.getUserNamePersonMap().get(userName).password)) System.err.println("the password is wrong");
                logger.lastChange=new Date();
                this.logger.commands.add("Error,"+logger.lastChange.toString()+",WRONG PASSWORD. ");
                System.out.println("Enter your password");
                password=scanner.nextLine();
                System.out.print(Color.RESET);
            }
            if(password.equalsIgnoreCase("main")){
                logger.lastChange=new Date();
                this.logger.commands.add("ALARM,"+logger.lastChange.toString()+",BACK TO MENU. ");
                return false;
            }
            System.out.print(Color.RESET);
            this.manager.personsController.setCurrentUser(this.manager.personsController.getUserNamePersonMap().get(userName));
            this.manager.personsController.isAnyOneInTheGame=true;
            System.out.println("your userName is \" "+userName+"\" \n your password is \" "+password+"\"");
            this.manager.personsController.reloadUsers.jasonWriter(this.manager.personsController);
            return true;
        }
        logger.lastChange=new Date();
        this.logger.commands.add("ERROR,"+logger.lastChange.toString()+",ERROR NOT USERNAME INCLUDED. ");
         System.err.println("the User Name ( "+userName+" ) dose not exist ." );
        return false;
    }
}
