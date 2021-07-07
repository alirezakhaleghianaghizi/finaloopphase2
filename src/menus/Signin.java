package menus;

import model.Person;

import java.util.Date;
import java.util.HashMap;

public class Signin extends Menu{
    public Signin(SignLogMenu signLogMenu) {
        super("SIGN IN",signLogMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new StartMenu(this));
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {
        System.out.println("");
        System.out.print(Color.GREEN_BOLD_BRIGHT);
        System.out.println("SIGN IN");
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
            this.logger.commands.add("ERROR,"+logger.lastChange.toString()+",LOGGED IN ALREADY. ");
            System.out.println("You have Already logged in");
            System.out.println("your userName is \" "+this.manager.personsController.getCurrentUser().userName+"\" \n your password is \" "+this.manager.personsController.getCurrentUser().password+"\"");
            return true;
        }
        System.out.print(Color.CYAN_BOLD_BRIGHT);
        System.out.println("Enter Your User Name To signin");
        userName=scanner.nextLine();
        if(this.manager.personsController.getUserNamePersonMap().containsKey(userName)){
            logger.lastChange=new Date();
            this.logger.commands.add("ERROR,"+logger.lastChange.toString()+",USERNAME TAKEN. ");
            System.err.println("this User name + (  "+userName+" ) is token" );
            return false;
        }
        System.out.println("ENTER YOUR PASSWORD");
        password=scanner.nextLine();
        System.out.print(Color.RESET);
        HashMap<String,Person> personHashMap=this.manager.personsController.getUserNamePersonMap();
        personHashMap.put(userName,new Person(userName,password,0));
        this.manager.personsController.setUserNamePersonMap(personHashMap);
        this.manager.personsController.setCurrentUser(this.manager.personsController.getUserNamePersonMap().get(userName));
        System.out.println("your userName is \" "+userName+"\" \n your password is \" "+password+"\"");
        this.manager.personsController.isAnyOneInTheGame=true;
        this.manager.personsController.reloadUsers.jasonWriter(this.manager.personsController);
        return true;
    }
}
