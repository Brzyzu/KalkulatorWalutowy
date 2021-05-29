import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcCurr {

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public static void main(String[] args) {
        HashMap<String, Double> currenciesMap = new ReadFile().parseToMap("eurofxref-daily.xml");


        boolean isRunning = true;

        System.out.println("KALKULATOR WALUTOWY");
        System.out.println("exit - zakonczenie programu");
        System.out.println("walute wprowadzamy za pomoca 3 literowego skrótu");
        System.out.println("przecinek jest definiowany jako symbol .");
        System.out.println();

        while (isRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj walutę jaką chcesz policzyć.");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                isRunning = false;
                System.out.println("Exiting");

            } else if (currenciesMap.containsKey(input.toUpperCase())) {
                System.out.println("Podaj kwotę jaką chcesz zamienić.");
                boolean isRunningInner = true;

                while (isRunningInner) {
                    String input2 = scanner.nextLine();
                    if (input2.equalsIgnoreCase("exit")) {
                        isRunningInner = false;
                        System.out.println("Exiting");
                    }
                    Pattern pattern = Pattern.compile("\\d+[.].\\d{0,2}|\\d+");
                    Matcher matcher = pattern.matcher(input2);
                    if (matcher.matches()) {
                        double inputValue = Double.parseDouble(input2);
                        System.out.println(inputValue + " EUR = " + df.format(currenciesMap.get(input.toUpperCase()) * inputValue) + " " + input.toUpperCase());
                        isRunningInner = false;

                    } else {
                        System.out.println("Niepoprawna kwota, podaj ponownie kwotę.");
                    }
                }
            } else {
                System.out.println("Niepoprawna komenda");
            }
        }
    }
}
