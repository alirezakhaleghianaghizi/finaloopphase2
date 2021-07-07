package menus;

import java.util.HashMap;

public class SignLogMenu extends Menu{
    public SignLogMenu() {
        super("Main menu : ");
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new Login(this));
        submenus.put(2, new Signin(this));
        this.setSubmenus(submenus);
    }
}
