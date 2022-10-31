package Controllers;

import Services.UserService;

public class UserController {
    private static UserController instance;
    Validation validation;
    public static UserController getInstance() {
        if(instance==null)instance=new UserController();
        return instance;
    }
    private  UserController(){
        validation=new Validation();
    }
    public void createNewUser(String email,String password,String userName)
    {
        validation.isValidEmail(email);
        validation.isValidPassword(password);
        UserService.getInstance().createUser(userName,email,password);
    }
    public void modifyPassword(String email,String Token,String newPassword)
    {
        validation.isValidPassword(newPassword);
        AuthController.getInstance().checkToken(email, Token);
        UserService.getInstance().changePassword(email,newPassword);
    }
}
