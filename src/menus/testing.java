package menus;

import java.util.Scanner;

public class testing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu mainMenu = new SignLogMenu();
        Menu.setScanner(scanner);
        mainMenu.show();
        mainMenu.execute();
    }
}
