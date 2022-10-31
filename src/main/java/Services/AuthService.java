package Services;

import DataSource.Repo;
import be.User;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class AuthService {
    public static AuthService getInstance()
    {
       if(instance==null) instance= new AuthService();
       return  instance;
    }
    private  static AuthService instance;
    private final  Map<Integer,String> Tokens;
    private AuthService()
    {
        Tokens=new HashMap<>();
    }
    private String createNewToken(String email)
    {
        byte [] bytes=new byte[8];
        ThreadLocalRandom.current().nextBytes(bytes);
        var s=  new String( bytes, StandardCharsets.US_ASCII);
        User user= ServicesUtil.isUserExists(Repo.getInstance().getUserByEmail(email));
        Tokens.put(user.getId(),s);
        return s;
    }
    public String login(String email,String password)
    {
        Repo repo=Repo.getInstance();
        User userByEmail= ServicesUtil.isUserExists(repo.getUserByEmail(email));
        if(userByEmail.getPassword().equals(password)) {
            return createNewToken(email);
        }
       throw new IllegalArgumentException("The password does not match the email.");
    }
    public boolean checkToken(String email,String Token)
    {
        User user = ServicesUtil.isUserExists(Repo.getInstance().getUserByEmail(email));
        return Tokens.get(user.getId()).equals(Token);
    }

}
