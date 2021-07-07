package menus;

import java.util.Date;

public class Settings extends Menu{
    public Settings(Menu parentMenu) {
        super("SETTINGS",parentMenu);
    }
    @Override
    public void show() {
        logger.lastChange=new Date();
        this.logger.commands.add("ALARM,"+logger.lastChange.toString()+",SETTING MENU NOT INSTALLED. ");
        System.err.println("SETTING MENU IS AVAILABLE IN NEXT VERSION");
    }

    @Override
    public void execute() {
        this.parentMenu.show();
        this.parentMenu.execute();
    }
}
