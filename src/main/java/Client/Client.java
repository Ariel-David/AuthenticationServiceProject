package Client;
enum UserActions{
    REGISTER,
    LOGIN,
    UPDATE_NAME,
    UPDATE_PASSWORD,
    EXIT
};
public class Client {
    private String token;

    public static void main(String[] args) {
        UserActions action;
        while(true)
        {
            action=System.in.read();
            switch (action) {
                case REGISTER:
                    break;
                case LOGIN:
                    break;
                case UPDATE_NAME:
                    break;
                case UPDATE_PASSWORD:
                    break;
                case EXIT:
                    break;
            }
        }

    }
}
