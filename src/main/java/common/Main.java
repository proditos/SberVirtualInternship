package common;

import java.util.List;
import static common.CityUtils.*;

/**
 * @author Vladislav Konovalov
 */
public class Main {
    public static void main(String[] args) {
        List<City> cities = parse();
        sortByDistrictAndName(cities);
        print(cities);
    }
}