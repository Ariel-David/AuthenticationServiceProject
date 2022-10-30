package DataSource;

import be.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Repo {
    static final String PATH = "src/main/java/DataSource/jsons";

    private static Repo instance;

    private Repo() {
    }

    public static Repo getInstance() {
        if (instance == null) {
            instance = new Repo();
        }
        return instance;
    }

    public void addNewUser(User user) {
        Gson gson = new Gson();
        int userId = user.getId();
        File userJsonFile = new File(PATH+"User"+userId);
        try {
            userJsonFile.createNewFile();
        } catch (IOException e) {
            System.out.println("User file creation failed");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String userJson = gson.toJson(user);
        //write to json
        throw new UnsupportedOperationException();
    }

    public User getUserByID(int id) {
        throw new UnsupportedOperationException();
    }

    public User getUserByEmail(String email) {
        throw new UnsupportedOperationException();
    }

}
