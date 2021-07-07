package controller;

import fileOperator.FileUsersNamePassword;
import model.Person;

import java.util.HashMap;

public class PersonsController {
    public Person CurrentUser;
    public boolean isAnyOneInTheGame;
    private HashMap<String,Person> userNamePersonMap;
    public FileUsersNamePassword reloadUsers;
    public PersonsController() {
        this.userNamePersonMap=new HashMap<>();
        CurrentUser = null;
        this.isAnyOneInTheGame = false;
        this.reloadUsers=new FileUsersNamePassword();
        this.userNamePersonMap=new HashMap<>();
    }

    public void setUserNamePersonMap(HashMap<String, Person> userNamePersonMap) {
        this.userNamePersonMap = userNamePersonMap;
    }

    public void setCurrentUser(Person currentUser) {
        CurrentUser = currentUser;
    }

    public Person getCurrentUser() {
        return CurrentUser;
    }

    public HashMap<String, Person> getUserNamePersonMap() {
        return userNamePersonMap;
    }
}
