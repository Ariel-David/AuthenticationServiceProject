package DataSource;

import be.User;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Repo {
    private static final String PATH = "src/main/java/DataSource/jsons/";
    private static final Gson gson = new Gson();
    private final Map<String, User> usersByEmails;
    private static Repo instance;

    private Repo() {
        usersByEmails =new HashMap<>();
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
            Files.write(Paths.get(PATH + "User" + userId), userJson.getBytes(StandardCharsets.UTF_8));
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
        return gson.fromJson(fileReader, User.class);
    }

    public Optional<User> getUserByEmail(String email) {
        FileReader fileReader;
        File dir = new File(PATH);
        File[] foundFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("User");
            }
        });

        for (File file : foundFiles) {
            try {
                fileReader = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);  //will never occur
            }
            User tempUser = gson.fromJson(fileReader, User.class);
            usersByEmails.put(tempUser.getEmail(), tempUser);
        }
        if(usersByEmails.containsKey(email)) return Optional.of(usersByEmails.get(email));
        return Optional.empty();
    }

    public void updateUsersName(String email, String newName)   {
        User tempUser = throwUserNotFoundException(getUserByEmail(email),email);
        tempUser.setName(newName);
        addNewUser(tempUser);
    }

    public void updateUsersPassword(String email, String newPassword)   {
        User tempUser = throwUserNotFoundException(getUserByEmail(email),email);
        tempUser.setPassword(newPassword);
        addNewUser(tempUser);
    }

    public void updateUsersEmail(String email, String newEmail)   {
        User tempUser = throwUserNotFoundException(getUserByEmail(email),email);
        tempUser.setEmail(newEmail);
        addNewUser(tempUser);
    }
    private User throwUserNotFoundException(Optional<User> user,String email)
    {
        if(user.isPresent())return user.get();
        throw new NullPointerException("No user with the following email address: "+ email+ " was found in the system.");
    }
}
