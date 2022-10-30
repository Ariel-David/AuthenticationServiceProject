package Client;

import java.util.Scanner;

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
            action= UserActions.valueOf(scanner.next());
            switch (action) {
                case REGISTER:
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
        String name=scanner.nextLine();
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

    }

    private static void handelRegister(Scanner scanner) {
        System.out.println("    Start Registration:   ");
        System.out.println("enter your name:");
        String name= scanner.nextLine();
        System.out.println("enter a valid email:");
        String email= scanner.nextLine();
        System.out.println("enter a password:");
        String password= scanner.nextLine();
    }
}
