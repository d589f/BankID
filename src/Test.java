import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface InputData {
    public void input();
}

public class Test {

    public static void main(String[] args) {
        System.out.println("Welcome to the bank");
        System.out.println("Do you have an account?");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(answer.equals("yes")){
            USERHASACCOUNT userhasaccount = new USERHASACCOUNT();
            userhasaccount.inputData.input();
        }else if(answer.equals("no")){
            USERHASNOTACCOUNT userhasnotaccount = new USERHASNOTACCOUNT();
            userhasnotaccount.inputData.input();
        }else if(answer.equals("admin")){
            ADMINDATA admindata = new ADMINDATA();
            admindata.IsAdmin();
        }
    }

    static class USER {
        public void startAuthorization() {
            System.out.print("Do you have an account?: ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            try {
                if (answer.equals("yes")) {
                    USERHASACCOUNT userhasaccount = new USERHASACCOUNT();
                    userhasaccount.inputData.input();
                } else if (answer.equals("no")) {
                    USERHASNOTACCOUNT userhasnotaccount = new USERHASNOTACCOUNT();
                    userhasnotaccount.inputData.input();
                }else if(answer.equals("admin")){
                    ADMINDATA admindata = new ADMINDATA();
                    admindata.IsAdmin();
                }else {
                    System.out.println("Please enter \"yes\" or \"no\"");
                }
            } catch (Exception e) {
                System.out.println("Please enter \"yes\" or \"no\"");
            }
        }
    }

    static class USERHASACCOUNT{
        InputData inputData = new InputData() {
            @Override
            public void input() {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter your login: ");
                String login = scanner.nextLine();

                System.out.print("Enter your password: ");
                int password = scanner.nextInt();
                String parseAge = Integer.toString(password);

                for(int i = 0; i < CELLS.listLoginPassword.size(); i++){
                    if(CELLS.listLoginPassword.get(i).equals(String.format("%s %s", login, parseAge))){
                        System.out.println("Welcome");
                    }else{
                        System.out.println("Wrong login or password");
                    }
                }
            }
        };
    }

    static class USERHASNOTACCOUNT{
        InputData inputData = new InputData() {
            @Override
            public void input() {
                System.out.println("You need to create an account");
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter your name: ");
                String name = scanner.nextLine();

                if(isSpecialCharactersAbsent(name)){
                    System.out.print("Enter your age: ");
                    int age = scanner.nextInt();
                    String parseAge = Integer.toString(age);
                    CELLS.listNameAge.add(String.format("%s %s", name, parseAge));
                } else {
                    System.out.println("Invalid name. Special characters are not allowed.");
                    return;
                }

                scanner.nextLine();

                System.out.print("Create your login: ");
                String login = scanner.nextLine();

                if(isSpecialCharactersAbsent(login)){
                    System.out.print("Create your password: ");
                    int password = scanner.nextInt();
                    String parsePassword = Integer.toString(password);
                    CELLS.listLoginPassword.add(String.format("%s %s", login, parsePassword));
                } else {
                    System.out.println("Invalid login. Special characters are not allowed.");
                }
                USER user = new USER();
                user.startAuthorization();
            }
        };
    }

    static class CELLS {
        static ArrayList<String> listNameAge = new ArrayList<String>();
        public void add(String name, int age) {
            listNameAge.add(String.format("%s %d", name, age));
        }

        static ArrayList<String> listLoginPassword = new ArrayList<>();
        public void add(String login, String password) {
            listLoginPassword.add(String.format("%s %s", login, password));
        }
    }

    static class ADMINDATA {
        public void IsAdmin(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter password: ");
            int answer = scanner.nextInt();
            try{
                if(answer == 1556){
                    System.out.println("Welcome");
                    ADMINDATACHECK admindatacheck = new ADMINDATACHECK();
                    admindatacheck.AdminCheckUsers();
                }
            }catch (Exception e){
                System.out.println("Access denied");
            }
        }
    }

    static class ADMINDATACHECK{
        public void AdminCheckUsers(){
            for(int i = 0; i < CELLS.listNameAge.size(); i++){
                System.out.println(CELLS.listNameAge.get(i));
            }
            for (int i = 0; i < CELLS.listLoginPassword.size(); i++) {
                System.out.println(CELLS.listLoginPassword.get(i));
            }
        }
    }

    public static boolean isSpecialCharactersAbsent(String input) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(input);
        return !matcher.find();
    }
}
