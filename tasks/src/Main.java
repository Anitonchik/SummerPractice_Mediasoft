import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        System.out.println("Task 1");

        int[] years = new int[50];
        Random rnd = new Random();
        for (int i = 0; i < years.length; i++) {
            years[i] = rnd.nextInt(2025 - 2000 + 1) + 2000;
        }

        Arrays.stream(years)
                .filter(y -> y > 2015)
                .forEach(System.out::println);

        double avgAge = Arrays.stream(years)
                .map(y -> 2025 - y)
                .average()
                .orElse(0);

        System.out.println(avgAge);


        System.out.println("\nTask 2");

        List<String> models = new ArrayList<>(List.of(
                "Toyota Camry", "BMW X5", "Tesla Model S",
                "Audi A6", "Tesla Model 3", "BMW X5",
                "Toyota Camry", "Mercedes C200"
        ));

        List<String> noDuplicates = models.stream()
                .distinct()
                .collect(Collectors.toList());

        List<String> replaced = noDuplicates.stream()
                .map(m -> m.contains("Tesla") ? "ELECTRO_CAR" : m)
                .collect(Collectors.toList());

        List<String> sorted = replaced.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        sorted.forEach(System.out::println);

        Set<String> set = new HashSet<>(sorted);


        System.out.println("\nTask 4");

        List<Car> cars = List.of(
                new Car("VIN001", "A6", "Audi", 2023, 12000, 70000, CarType.SEDAN),
                new Car("VIN002", "Model S", "Tesla", 2022, 30000, 85000, CarType.ELECTRIC),
                new Car("VIN003", "Camry", "Toyota", 2020, 60000, 30000, CarType.SEDAN),
                new Car("VIN004", "Q7", "Audi", 2021, 45000, 65000, CarType.SUV),
                new Car("VIN005", "Model 3", "Tesla", 2023, 10000, 50000, CarType.ELECTRIC),
                new Car("VIN006", "Corolla", "Toyota", 2022, 20000, 25000, CarType.SEDAN)
        );

        List<Car> lowMileage = cars.stream()
                .filter(c -> c.getMileage() < 50000)
                .collect(Collectors.toList());

        List<Car> sortedByPriceDesc = lowMileage.stream()
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .collect(Collectors.toList());

        List<Car> top3 = sortedByPriceDesc.stream()
                .limit(3)
                .collect(Collectors.toList());

        top3.forEach(System.out::println);

        double avgMileage = cars.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);

        System.out.println(avgMileage);

        Map<String, List<Car>> grouped = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        grouped.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });


        System.out.println("\nLast task");

        CarDealership dealership = new CarDealership();

        dealership.addCar(new Car("VIN100", "A8", "Audi", 2024, 5000, 90000, CarType.SEDAN));
        dealership.addCar(new Car("VIN101", "Model X", "Tesla", 2023, 15000, 120000, CarType.ELECTRIC));
        dealership.addCar(new Car("VIN102", "RAV4", "Toyota", 2021, 30000, 35000, CarType.SUV));
        dealership.addCar(new Car("VIN103", "C-Class", "Mercedes", 2019, 45000, 40000, CarType.SEDAN));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 - найти по производителю");
            System.out.println("2 - средняя цена по типу");
            System.out.println("3 - сортировка по году выпуска");
            System.out.println("4 - количество машин по типам");
            System.out.println("5 - самая старая машина");
            System.out.println("6 - самая новая машина");
            System.out.println("0 - выход");

            int cmd = sc.nextInt();
            sc.nextLine();

            switch (cmd) {
                case 0:
                    return;
                case 1:
                    String m = sc.nextLine();
                    dealership.findByManufacturer(m).forEach(System.out::println);
                    break;
                case 2:
                    String t = sc.nextLine();
                    System.out.println(dealership.averagePriceByType(CarType.valueOf(t)));
                    break;
                case 3:
                    dealership.sortedByYear().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println(dealership.countByType());
                    break;
                case 5:
                    dealership.oldestCar().ifPresent(System.out::println);
                    break;
                case 6:
                    dealership.newestCar().ifPresent(System.out::println);
                    break;
                default:
                    System.out.println("Неверная команда");
            }
        }

    }
}
