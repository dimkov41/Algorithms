import java.util.*;

/**
 * @author Radostin Dimkov 27.05.19
 */
public class FractionalKnapsackProblem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String capacityStr = in.nextLine();
        String itemsStr = in.nextLine();

        int capacity = Integer.parseInt(capacityStr.split(": ")[1]);
        int itemsCount = Integer.parseInt(itemsStr.split(": ")[1]);

        List<double[]> items = new ArrayList<>();

        for (int i = 0; i < itemsCount; i++) {
            double[] currentItem =
                    Arrays
                            .stream(in.nextLine().split(" -> "))
                            .mapToDouble(Double::parseDouble)
                            .toArray();


            items.add(currentItem);
        }

        items.sort((o1, o2) -> {
            Double o1pricePerKilo = o1[0] / o1[1];
            Double o2pricePerKilo = o2[0] / o2[1];

            return o2pricePerKilo.compareTo(o1pricePerKilo);
        });

        double totalPrice = 0f;

        for (double[] currentItem : items)  {

            double itemPrice = currentItem[0];
            double itemWeight = currentItem[1];

            if (capacity >= itemWeight) {
                capacity -= itemWeight;
                totalPrice += itemPrice;
                System.out.println(String.format("Take 100%% of item with price %.2f and weight %.2f", itemPrice, itemWeight));

            } else {
                //capacity < itemWeight
                if (capacity <= 0) {
                    break;
                } else {
                    //capacity < itemWeight && capacity > 0

                    double percentage = capacity / itemWeight;
                    capacity -= itemWeight * percentage;
                    totalPrice += itemPrice * percentage;

                    System.out.println(
                            String.format("Take %.2f%% of item with price %.2f and weight %.2f",
                                    percentage * 100,
                                    itemPrice,
                                    itemWeight)
                    );
                }
            }
        }

        System.out.println(String.format("Total price: %.2f", totalPrice));
    }
}
