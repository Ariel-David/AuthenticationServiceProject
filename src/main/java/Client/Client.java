package Client;

import Controllers.AuthController;
import Controllers.UserController;

import java.util.Scanner;

public class Client {
    private static String token;
    private static String email;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        UserActions action;
        while(true)
        {
            System.out.println("please choose one of the following options:");
            int i=0;
            for (UserActions act:UserActions.values())
            {
                System.out.printf("%d: %s\n",++i,act);
            }
            action= UserActions.valueOf(scanner.nextLine().trim());
            switch (action) {
                case REGISTER:
                    //UserController.getInstance().createNewUser("itamar@gmail.com","dsdsffdf","");
                    handelRegister(scanner);
                    break;
                case LOGIN:
                    handleLogin(scanner);
                    break;
                case UPDATE_NAME:
                    handleUpdateName(scanner);
                    break;
                case UPDATE_PASSWORD:
                    handleUpdatePassword(scanner);
                    break;
                case UPDATE_EMAIL:
                    handleUpdateEmail(scanner);
                    break;
                case EXIT:
                    return;
            }
        }

    }
    private static void handleUpdatePassword(Scanner scanner) {
        System.out.println("enter your new password:");
        String newPassword=scanner.nextLine();
        UserController.getInstance().modifyPassword(email,token,newPassword);
    }
    private static void handleUpdateEmail(Scanner scanner)
    {
        System.out.println("enter your new email:");
        String newEmail=scanner.nextLine();
        UserController.getInstance().modifyEmail(email,token,newEmail);
    }

    private static void handleUpdateName(Scanner scanner) {
        System.out.println("enter your new name:");
        String newName=scanner.nextLine();
        UserController.getInstance().modifyUserName(email,token,newName);
    }

    private static void handleLogin(Scanner scanner) {
        System.out.println("enter your email:");
        String email= scanner.nextLine();
        System.out.println("enter your password:");
        String password= scanner.nextLine();
        token=AuthController.getInstance().tryLogin(email,password);
        Client.email=email;
    }

    private static void handelRegister(Scanner scanner) {
        System.out.println("    Start Registration:   ");
        System.out.println("enter your name:");
        String name= scanner.nextLine();
        System.out.println("enter a valid email:");
        String email= scanner.nextLine();
        System.out.println("enter a password:");
        String password= scanner.nextLine();
        UserController.getInstance().createNewUser(email,password,name);
    }
}
