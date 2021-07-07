package menus;

import model.Person;

import java.util.Date;
import java.util.HashMap;

public class LogOut extends Menu{
    public LogOut(Menu parentMenu) {
        super("LOG OUT",parentMenu);

    }
    @Override
    public void show() {
        System.out.println("LOG OUT");
    }

    @Override
    public void execute() {
        this.manager.personsController=this.manager.personsController.reloadUsers.readFile(this.manager.personsController);
        System.err.println("ARE YOU SURE YOU WANT TO LOG OUT THE ACCOUNT . (Y/N)");
        String answer=scanner.nextLine();
        while(!answer.equalsIgnoreCase("y")&&!answer.equalsIgnoreCase("n")){
            logger.lastChange=new Date();
            this.logger.commands.add("Error,"+logger.lastChange.toString()+",INCORRECT COMMMAND FOR LOG OUT. ");
            System.err.println("ARE YOU SURE YOU WANT TO LOG OUT THE ACCOUNT . (Y/N)");
             answer=scanner.nextLine();
        }

            if(answer.equalsIgnoreCase("Y")){
            this.manager.personsController.isAnyOneInTheGame=false;
            HashMap<String,Person> personHashMap=this.manager.personsController.getUserNamePersonMap();
            this.manager.personsController.setCurrentUser(null);
            this.manager.personsController.setUserNamePersonMap(personHashMap);
            HashMap<Integer, Menu> submenus = new HashMap<>();
            submenus.put(1, new SignLogMenu());
            this.setSubmenus(submenus);
            this.manager.personsController.reloadUsers.jasonWriter(this.manager.personsController);
            logger.lastChange=new Date();
            this.logger.commands.add("INFO,"+logger.lastChange.toString()+",loge out the account. ");
            this.submenus.get(1).show();
            this.submenus.get(1).execute();
        }
            else if(answer.equalsIgnoreCase("n")){
                logger.lastChange=new Date();
                this.logger.commands.add("INFO,"+logger.lastChange.toString()+",NOT LOGE OUT THE ACCOUNT. ");
                this.parentMenu.show();
                this.parentMenu.execute();
            }

    }


}
