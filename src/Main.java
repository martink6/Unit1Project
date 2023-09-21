import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name = askQuestion("What is your name? ");
        System.out.printf("Welcome %s!", name);
        int partySize = (int) stringToDouble(askQuestion("How many people are in your party? "));
    }

    static String askQuestion(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        return scanner.nextLine();
    }

    static double stringToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            System.out.printf("Failed to convert string to double: %s, using 0.", e);
            return 0;
        }
    }
}