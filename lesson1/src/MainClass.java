import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainClass {

    private static final String[] providerCodes = {"093", "063", "073", "050", "099", "066", "067", "091", "092", "094", "095", "096", "097", "098"};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isPhoneCorrect;
        String phoneNumber;

        while (true) {
            System.out.print("Please enter the phone number: ");
            phoneNumber = reader.readLine();
            isPhoneCorrect = validate(phoneNumber);
            if (isPhoneCorrect) {
                System.out.println("Phone number is correct.");
                break;
            } else
                System.out.println("Phone number is incorrect.");
        }

        phoneNumber = normalize(phoneNumber);

        System.out.println("International format: " + phoneNumber);

        phoneNumber = calculate(phoneNumber.replace("+", ""));

        System.out.println("Final result is: " + prepareResult(phoneNumber));

        reader.close();
    }

    private static String prepareResult(String result) {
        if (result.equals("1")) return "One";
        if (result.equals("2")) return "Two";
        if (result.equals("3")) return "Three";
        return result;
    }

    private static String calculate(String number) {
        int round = 1;
        do {
            short rez = 0;
            for (int i = 0; i < number.length(); i++) {
                rez += Short.parseShort(String.valueOf(number.charAt(i)));
            }
            number = String.valueOf(rez);
            System.out.println(round++ + " round of calculation, sum is: " + number);
        } while (number.length() > 1);

        return number;
    }

    private static String normalize(String input) throws Exception {
        if (input.startsWith("+380")) return input;
        if (input.startsWith("00380")) return input.replaceAll("^00", "+");
        if (input.startsWith("380")) return "+".concat(input);
        if (input.startsWith("80")) return "+3".concat(input);
        if (input.startsWith("0")) return "+38".concat(input);
        throw new Exception("Number incorrect");
    }

    private static boolean validate(String input) {
        if (input.matches("[+]?[0-9]{10,14}")) {
            if (input.startsWith("00380") && checkProvider(input.substring(4, 7)) && input.length() == 14) return true;
            if (input.startsWith("+380") && checkProvider(input.substring(3, 6)) && input.length() == 13) return true;
            if (input.startsWith("380") && checkProvider(input.substring(2, 5)) && input.length() == 12) return true;
            if (input.startsWith("80") && checkProvider(input.substring(1, 4)) && input.length() == 11) return true;
            if (input.startsWith("0") && checkProvider(input.substring(0, 3)) && input.length() == 10) return true;
        }
        return false;
    }

    private static boolean checkProvider(String code) {
        for (String provider : providerCodes) {
            if (code.equals(provider))
                return true;
        }
        return false;
    }
}
