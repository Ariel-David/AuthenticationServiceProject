package Services;

import Controllers.UserController;
import DataSource.Repo;
import be.User;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UserService {

    private static UserService instance;

    public static UserService getInstance() {
        if(instance!=null)instance=new UserService();
        return instance;
    }
    private  UserService(){}
    public void validUniqueEmail(String email) {
        if (Repo.getInstance().getUserByEmail(email) != null) {
            throw new IllegalArgumentException("A user with this email already exists. Choose another email.");
        }
    }

    public User createUser(String name, String email, String password) {
        User u = new User(name, email, password);
        Repo.getInstance().addNewUser(u);
        System.out.println("User is created!");
        return u;
    }

    public void changePassword(User u, String password) {
        u.setPassword(password);
    }

    public void changeName(User u, String name) {
        u.setPassword(name);
    }
}
