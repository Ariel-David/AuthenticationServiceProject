package Controllers;

import Services.AuthService;

public class AuthController {
    private Validation validation;

    public static AuthController getInstance() {
        if (instance == null) instance = new AuthController();
        return instance;
    }

    private static AuthController instance;

    private AuthController() {
        validation = new Validation();
    }

    public String tryLogin(String email, String password) {
        validation.isValidEmail(email);
        validation.isValidPassword(password);
        return AuthService.getInstance().login(email, password);
    }

    public void checkToken(String email, String Token) {
        AuthService.getInstance().checkToken(email, Token);
    }
}
