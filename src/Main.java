import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name = askQuestion("What is your name? ");
        System.out.printf("Welcome %s!%n", name);
        int partySize = (int) stringToDouble(askQuestion("How many people are in your party? "));
        double tipPercent = stringToDouble(askQuestion("How much do you want to tip? (in percent) "));
        double[] itemCosts = getItems();
        double total = 0;
        for (double itemCost : itemCosts) {
            System.out.printf("Current total: %f, item being added: %f. %n", total, itemCost);
            total += itemCost;
        }

        double tipCost = total * (tipPercent / 100);

        System.out.printf("Your total tip cost is $%f.%n", tipCost);
    }

    static double[] getItems() {
        System.out.println("Input the costs for each of your items. Input '0' or nothing to exit");
        double itemCost = stringToDouble(askQuestion("Input an item cost >>>"));
        List<Double> itemCosts = new ArrayList<>();
        itemCosts.add(itemCost);
        while (itemCosts.get(itemCosts.size() - 1) != 0) {
            itemCosts.add(stringToDouble(askQuestion("Input an item cost >>>")));
        }

        double[] costs = new double[itemCosts.size()];

        for (double cost: itemCosts) {
            costs[itemCosts.indexOf(cost)] = cost;
        }

        return costs;
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
            System.out.println(e);
            return 0;
        }
    }
}