package Services;

import DataSource.Repo;
import be.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    public static AuthService getInstance()
    {
       if(instance==null) instance= new AuthService();
       return  instance;
    }
    private  static AuthService instance;
    private Map<Integer,String> Tokens;
    private AuthService()
    {
        Tokens=new HashMap<>();
    }
    private String createNewToken(String email)
    {
        return
    }
    public boolean login(String email,String password)
    {
        Repo repo=Repo.getInstance();
        User userByEmail = repo.getUserByEmail(email);
        if(userByEmail==null) throw new IllegalArgumentException("There is no user with the email you type");
        if(userByEmail.getPassword().equals(password)) {

        }
        return false;
    }
}
