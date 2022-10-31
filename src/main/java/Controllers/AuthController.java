package Controllers;

import Services.AuthService;
import Services.Validation;

public class AuthController {
    private  Validation validation;

public static AuthController getInstance()
{
    if(instance==null) instance=new AuthController();
    return instance;
}
private  static  AuthController instance;
private AuthController(){
    validation=new Validation();
}
    public  void tryLogin(String email,String password)
    {
       if(!validation.validEmail(email)) throw  new IllegalArgumentException("The email you entered is not valid.");
       if(!validation.validPassword(password)) throw  new IllegalArgumentException("The password you entered is not valid.");
       AuthService.getInstance().login(email,password);
    }
    public void checkToken(String email,String Token)
    {
        AuthService.getInstance().checkToken(email, Token);
    }
}
