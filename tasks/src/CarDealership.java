import java.util.*;
import java.util.stream.Collectors;

public class CarDealership {

    private List<Car> cars = new ArrayList<>();

    public boolean addCar(Car car) {
        if (cars.stream().anyMatch(c -> c.getVin().equals(car.getVin()))) {
            return false;
        }
        cars.add(car);
        return true;
    }

    public List<Car> findByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    public double averagePriceByType(CarType type) {
        return cars.stream()
                .filter(c -> c.getType() == type)
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
    }

    public List<Car> sortedByYear() {
        return cars.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public Map<CarType, Long> countByType() {
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getType, Collectors.counting()));
    }

    public Optional<Car> oldestCar() {
        return cars.stream()
                .min(Comparator.comparingInt(Car::getYear));
    }

    public Optional<Car> newestCar() {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getYear));
    }

    public List<Car> getCars() {
        return cars;
    }
}
