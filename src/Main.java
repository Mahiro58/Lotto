import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj datę i godzinę (dd.MM.yyyy HH:mm:ss): ");
        String input = scanner.nextLine();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(input, formatter);

        System.out.println("\nWybierz typ losowania:");
        System.out.println("1 - Eurojackpot (5 z 50 + 2 z 12)");
        System.out.println("2 - Lotto (6 z 49)");
        System.out.print("Twój wybór: ");

        int choice = scanner.nextInt();

        switch (choice) {

            case 1:
                TreeSet<Integer> numbers50 = new TreeSet<>();
                TreeSet<Integer> numbers12 = new TreeSet<>();

                while (numbers50.size() < 5) {
                    long seed = date.toEpochSecond(ZoneOffset.UTC);
                    Random random = new Random(seed);

                    numbers50.add(random.nextInt(50) + 1);

                    int seconds = random.nextInt(3) + 3;
                    date = date.plusSeconds(seconds);
                }

                while (numbers12.size() < 2) {
                    long seed = date.toEpochSecond(ZoneOffset.UTC);
                    Random random = new Random(seed);

                    numbers12.add(random.nextInt(12) + 1);

                    int seconds = random.nextInt(3) + 3;
                    date = date.plusSeconds(seconds);
                }

                System.out.println("\nWylosowane liczby:");
                System.out.println("5 z 50 : " + numbers50);
                System.out.println("2 z 12 : " + numbers12);
                break;

            case 2:
                TreeSet<Integer> numbers49 = new TreeSet<>();

                while (numbers49.size() < 6) {
                    long seed = date.toEpochSecond(ZoneOffset.UTC);
                    Random random = new Random(seed);

                    numbers49.add(random.nextInt(49) + 1);

                    int seconds = random.nextInt(3) + 3;
                    date = date.plusSeconds(seconds);
                }

                System.out.println("\nWylosowane liczby:");
                System.out.println("6 z 49 : " + numbers49);
                break;

            default:
                System.out.println("Niepoprawny wybór.");
        }

        scanner.close();
    }
}