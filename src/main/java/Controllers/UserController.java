package Controllers;

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
        validation.validPassword(password);
        UserController.getInstance().createNewUser(email, password, userName);
    }
}
