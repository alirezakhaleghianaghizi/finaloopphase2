package menus;

public class StartMenu extends Menu {
    public StartMenu(Menu parentMenu) {
    super("START",parentMenu);
        submenus.put(1, new Start(this));
        submenus.put(2, new LogOut(this));
        submenus.put(3, new Settings(this));
    }

}
