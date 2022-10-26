package common;

import java.util.List;
import static common.CityUtils.*;

/**
 * @author Vladislav Konovalov
 */
public class Main {
    public static void main(String[] args) {
        List<City> cities = parse();
        int[] populations = getPopulations(cities);
        int maxAt = getIndexOfMax(populations);
        int max = populations[maxAt];
        System.out.println("[" + maxAt + "] = " + max);
    }

    private static int getIndexOfMax(int[] array) {
        if (array.length == 0)
            return -1;
        int maxAt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[maxAt]) {
                maxAt = i;
            }
        }
        return maxAt;
    }
}