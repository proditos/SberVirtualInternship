package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Vladislav Konovalov
 */
public class CityUtils {
    private static final String RESOURCES_ROOT = "src/main/resources/";
    private static final String DATA_FILE_PATH = RESOURCES_ROOT + "city_ru.csv";

    private CityUtils() {}

    public static void findMaxPopulationAndPrint(List<City> cities) {
        City[] array = new City[cities.size()];
        cities.toArray(array);
        int maxAt = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i].getPopulation() > array[maxAt].getPopulation()) {
                maxAt = i;
            }
        }
        System.out.printf("[%d] = %d%n", maxAt, array[maxAt].getPopulation());
    }

    public static void countAndPrintCitiesByRegion(List<City> cities) {
        Map<String, Integer> regionMap = new HashMap<>();
        for (City city : cities) {
            String region = city.getRegion();
            if (regionMap.containsKey(region)) {
                int counter = regionMap.get(region);
                regionMap.put(region, counter + 1);
            } else {
                regionMap.put(region, 1);
            }
        }

        regionMap.forEach((region, counter) -> System.out.printf("%s - %d%n", region, counter));
    }

    public static void sortByNameIgnoreCase(List<City> cities) {
        cities.sort((city1, city2) -> city1.getName().compareToIgnoreCase(city2.getName()));
    }

    public static void sortByDistrictAndName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    public static List<City> parse() {
        List<City> cities = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File(DATA_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                cities.add(parse(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void print(List<City> cities) {
        for (City city : cities) {
            System.out.println(city);
            System.out.println();
        }
    }

    private static City parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d+");
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = scanner.hasNext() ? scanner.next() : null;
        scanner.close();
        return new City(name, region, district, population, foundation);
    }
}
