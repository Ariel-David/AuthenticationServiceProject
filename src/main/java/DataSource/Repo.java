package DataSource;

import be.User;

public class Repo {

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

        throw new UnsupportedOperationException();
    }

    public User getUserByID(int id) {
        throw new UnsupportedOperationException();
    }

    public User getUserByEmail(String email) {
        throw new UnsupportedOperationException();
    }

}
