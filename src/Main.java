import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name = askQuestion("What is your name? ");
        System.out.printf("Welcome %s!%n", name);

        int partySize = (int) stringToDouble(askQuestion("How many people are in your party? "));

        double tipPercent = -1;
        while (tipPercent < 0) {
            tipPercent = stringToDouble(askQuestion("What percent would you like to tip? "));
        }

        Map<String, ItemInfo> itemCosts = getItems();

        double totalCost = 0;
        for (ItemInfo itemInfo : itemCosts.values()) {
            totalCost += itemInfo.getItemCost() * itemInfo.getItemCount();
        }

        System.out.println("===== Receipt =====");

        itemCosts.forEach((itemName, itemCost) -> {
            //format to 2 decimal places
            System.out.printf("Item: %s | Count: %d | Cost: $%.2f%n", itemName, itemCost.getItemCount(), itemCost.getItemCost());
        });

        System.out.printf("Cost before tip: $%.2f%n", totalCost);

        double tipCost = totalCost * (tipPercent / 100);
        System.out.printf("Tip cost: $%.2f%n", tipCost);

        double totalCostWithTip = totalCost + tipCost;
        System.out.printf("Total cost: $%.2f%n", totalCostWithTip);

        double costPerPerson = totalCostWithTip / partySize;
        System.out.printf("Cost per person: $%.2f%n", costPerPerson);

        System.out.println("===================");
    }

    static Map<String, ItemInfo> getItems() {
        System.out.println("Input the costs for each of your items. Input '-1' or '' to exit");
        Map<String, ItemInfo> costMap = new HashMap<>();

        String item = askQuestion("Input an item name >>>");
        double itemCost = stringToDouble(askQuestion(String.format("How much does %s cost? >>>", item)));
        int itemCount = (int) stringToDouble(askQuestion(String.format("How many %s did you purchase? >>>", item)));
        costMap.put(item, new ItemInfo(itemCost, itemCount));

        while (itemCost != -1 && itemCount != -1 && !item.isEmpty()) {
            item = askQuestion("Input an item name >>>");
            if (item.isEmpty()) {
                break;
            }
            itemCost = stringToDouble(askQuestion(String.format("How much does %s cost? >>>", item)));
            if (itemCost == -1) {
                break;
            }
            itemCount = (int) stringToDouble(askQuestion(String.format("How many %s did you purchase? >>>", item)));
            if (itemCount == -1) {
                break;
            }

            //use a ItemInfo class since mappings only hold key:value pairs
            costMap.put(item, new ItemInfo(itemCost, itemCount));
        }

        return costMap;
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
            //prevent crashing
            System.out.println("Invalid input");
            return 0;
        }
    }
}
