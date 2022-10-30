package Services;

import DataSource.Repo;
import be.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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
        byte [] bytes=new byte[8];
        ThreadLocalRandom.current().nextBytes(bytes);
        var s=  new String( bytes, StandardCharsets.US_ASCII);
        User user=Repo.getInstance().getUserByEmail(email);
        Tokens.put(user.getId(),s);
        return s;
    }
    public String login(String email,String password)
    {
        Repo repo=Repo.getInstance();
        User userByEmail = repo.getUserByEmail(email);
        if(userByEmail==null) throw new IllegalArgumentException("There is no user with the email you type");
        if(userByEmail.getPassword().equals(password)) {
            return createNewToken(email);
        }
       throw  new IllegalArgumentException("The password aren't match the email ");
    }
}
