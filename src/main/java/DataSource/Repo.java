package DataSource;

import be.User;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;
public class Repo {
    private static final String PATH = "src/main/java/DataSource/jsons/";
    private static Gson gson = new Gson();
    private Map<Integer, User> users;
    private static Repo instance;

    private Repo() {
    }

    public void updateUser(User user){
        if(users.containsKey(user.getId())){
            users.put(user.getId(), user);
        }
    }
    
    public static Repo getInstance() {
        if (instance == null) {
            instance = new Repo();
        }
        return instance;
    }

    public void addNewUser(User user) {
        int userId = user.getId();
        FileWriter writer;
        File userJsonFile = new File(PATH + "User" + userId);
        String userJson = gson.toJson(user);
        try {
            userJsonFile.createNewFile();
            writer = new FileWriter(userJsonFile);
            writer.write(userJson);
        } catch (IOException e) {
            System.out.println("new user json file creation/writing failed");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User getUserById(int id) {
        File userJsonFile = new File(PATH + "User" + id);
        FileReader fileReader;
        if (!userJsonFile.exists()) {
            System.out.println("User " + id + "does not exist");
            return null;
        }
        try {
            fileReader = new FileReader(userJsonFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);  //will never occur
        }
        User userFromJson = gson.fromJson(fileReader, User.class);
        return userFromJson;
    }

    public User getUserByEmail(String email) {
        throw new UnsupportedOperationException();
    }
}
