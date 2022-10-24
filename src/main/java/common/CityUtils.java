package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Vladislav Konovalov
 */
public class CityUtils {
    private static final String RESOURCES_ROOT = "src/main/resources/";
    private static final String DATA_FILE_PATH = RESOURCES_ROOT + "city_ru.csv";

    private CityUtils() {}

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
