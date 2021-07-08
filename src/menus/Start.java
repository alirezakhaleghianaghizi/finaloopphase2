package menus;

import controller.MainController;
import model.Person;
import view.InputProcessor;
import view.Timing;

import java.util.Date;
import java.util.HashMap;

public class Start extends Menu{
    InputProcessor inputProcessor;
    int level;
    public Start(Menu parentMenu) {
        super("START",parentMenu);
        this.inputProcessor=new InputProcessor(this.manager);
    }
    @Override
    public void show() {
        System.out.println("");

        System.out.print(Color.CYAN_BOLD_BRIGHT);
        System.out.println("Enter The Level You Want To Play");
        level=-1;
        this.manager.personsController=this.manager.personsController.reloadUsers.readFile(this.manager.personsController);
        System.out.println(this.manager.personsController.CurrentUser.userName);
        this.logger.userName=this.manager.personsController.CurrentUser.userName;
        boolean isInputCorrect=false;
        this.manager.allLevels=this.manager.allLevels.FILE.reloadLevels(this.manager.allLevels);
        while(!isInputCorrect){
            try{
                while ((level=Integer.parseInt(scanner.nextLine()))>=this.manager.personsController.getCurrentUser().level+2||level>manager.allLevels.numberOfLevels){
                if(manager.allLevels.numberOfLevels<level) {
                    System.err.println("PLEASE ENTER AN AVAILABLE LEVEL IN THE GAME. ");
                    logger.lastChange=new Date();
                    this.logger.commands.add("ERROR,"+logger.lastChange.toString()+",NOT AVAILABLE LEVEL. ");
                }
                if(this.manager.personsController.getCurrentUser().level+2<=level) {
                    System.err.println("PLEAS ENTER THE LEVEL WITCH IS OPENED FOR YOU");
                    logger.lastChange=new Date();
                    this.logger.commands.add("ERROR,"+logger.lastChange.toString()+",LEVEL NOT OPENED. ");
                }
                }
                isInputCorrect=true;
            }
            catch (NumberFormatException e){
                    logger.lastChange=new Date();
                    this.logger.commands.add("ERROR,"+logger.lastChange.toString()+",NOT CORRECT format INPUT . ");
                    System.err.println("numberFormatException");
            }
            }
        logger.lastChange=new Date();
        this.logger.commands.add("INFO,"+logger.lastChange.toString()+",STARTED THE GAME. ");
        this.logger.jasonWriter(this.logger);
        System.out.print(Color.RESET);
        System.out.println("");
        this.manager.personsController.getCurrentUser().currentLevel=this.manager.allLevels.levels.get(level-1);
        this.manager.personsController.getCurrentUser().totalCoins=this.manager.personsController.CurrentUser.coins+this.manager.personsController.getCurrentUser().currentLevel.startCoins;
        this.manager.logger=this.logger;
        Timing.setCurrentTime(0);
        this.inputProcessor.run(scanner,level);
        HashMap<String , Person> users=this.manager.personsController.getUserNamePersonMap();
        users.put(this.manager.personsController.CurrentUser.userName,this.manager.personsController.CurrentUser);
        this.manager.personsController.setUserNamePersonMap(users);
        this.manager.personsController.reloadUsers.jasonWriter(this.manager.personsController);
        this.manager=new MainController();
        this.manager.personsController=this.manager.personsController.reloadUsers.readFile(this.manager.personsController);
    }
    @Override
    public void execute() {
        this.parentMenu.show();
        this.parentMenu.execute();
    }
}
