package Controllers;

import Services.UserService;

public class UserController {
    private static UserController instance;
    Validation validation;

    public static UserController getInstance() {
        if (instance == null) instance = new UserController();
        return instance;
    }

    private UserController() {
        validation = new Validation();
    }

    public void createNewUser(String email, String password, String userName) {
        try {
            validation.isValidEmail(email);
            validation.isValidPassword(password);
            UserService.getInstance().createUser(userName, email, password);
        } catch (IllegalArgumentException exp)
        {
            ControllersUtil.printErrorToCmd("User cannot be created.",exp.getMessage());
        } catch (RuntimeException exp)
        {
            ControllersUtil.printErrorToCmd("User cannot be created.",exp.getMessage());
        }
    }
    public void modifyPassword(String email,String Token,String newPassword)
    {
        try {
            validation.isValidPassword(newPassword);
            AuthController.getInstance().checkToken(email, Token);
            UserService.getInstance().changePassword(email, newPassword);
        }
        catch (IllegalArgumentException exp)
        {

        }
    }
    public void modifyUserName(String email,String Token,String newUserName)
    {
        AuthController.getInstance().checkToken(email, Token);
        UserService.getInstance().changeName(email,newUserName);

    }
    public void modifyEmail(String email,String Token,String newEmail)
    {
        try {
            validation.isValidEmail(newEmail);
            AuthController.getInstance().checkToken(email, Token);
            UserService.getInstance().changeEmail(email, newEmail);
        }
        catch (IllegalArgumentException | NullPointerException exp)
        {
            ControllersUtil.printErrorToCmd("Failed to change email.",exp.getMessage());
        }
    }

}
