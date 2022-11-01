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
            for (var act:UserActions.values())
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
                    handelLogin(scanner);
                case UPDATE_NAME:
                    HandelUpdateName(scanner);
                case UPDATE_PASSWORD:
                    HandelUpdatePassword(scanner);
                    break;
                case EXIT:
                    return;
            }
        }

    }
    private static void HandelUpdatePassword(Scanner scanner) {
        System.out.println("enter your token:");
        String token=scanner.nextLine();
        System.out.println("enter your new password:");
        String password=scanner.nextLine();
        UserController.getInstance().modifyPassword(email,token,password);
    }

    private static void HandelUpdateName(Scanner scanner) {
        System.out.println("enter your token:");
        String token=scanner.nextLine();
        System.out.println("enter your new name:");
        String name=scanner.nextLine();

    }

    private static void handelLogin(Scanner scanner) {
        System.out.println("enter your email:");
        String email= scanner.nextLine();
        System.out.println("enter your password:");
        String password= scanner.nextLine();
        token=AuthController.getInstance().tryLogin(email,password);
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
