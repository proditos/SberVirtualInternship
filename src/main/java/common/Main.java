package common;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Vladislav Konovalov
 */
public class Main {
    private static final String RESOURCES_ROOT = "src/main/resources/";
    private static final String DATA_FILE_PATH = RESOURCES_ROOT + "city_ru.csv";

    public static void main(String[] args) {
        List<City> cities = new LinkedList<>();

        Path dataFilePath = Paths.get(DATA_FILE_PATH);
        try (Scanner scanner = new Scanner(dataFilePath)) {
            while (scanner.hasNext()) {
                scanner.useDelimiter("\n");
                String[] cityData = scanner.next().split(";");
                City city = new City(cityData[1].trim(),
                        cityData[2].trim(),
                        cityData[3].trim(),
                        Integer.parseInt(cityData[4].trim()),
                        cityData[5].trim());
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (City city : cities) {
            System.out.println(city);
            System.out.println();
        }
    }
}