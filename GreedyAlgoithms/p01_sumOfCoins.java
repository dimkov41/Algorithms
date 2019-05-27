import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p01_sumOfCoins {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));


        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);
        // fancy printing
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        Arrays.sort(coins);

        Map<Integer, Integer> map = new LinkedHashMap<>();

        int coinIndex = coins.length - 1;

        while (targetSum > 0 && coinIndex >= 0) {
            int currentCoin = coins[coinIndex];

            if (currentCoin <= targetSum) {
                targetSum -= currentCoin;

                if (map.containsKey(currentCoin)) {
                    map.put(currentCoin, map.get(currentCoin) + 1);
                } else {
                    map.put(currentCoin, 1);
                }
            } else {

                coinIndex--;

                if(coinIndex < 0){
                    throw new IllegalArgumentException();
                }
            }
        }

        return map;
    }
}
