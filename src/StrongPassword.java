package src;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrongPassword {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int minimumPasswordLength = 6;
    private static final Pattern numberPattern = Pattern.compile("[0-9]");
    private static final Pattern lowerCasePattern = Pattern.compile("[a-z]");
    private static final Pattern upperCasePattern = Pattern.compile("[A-Z]");
    private static final Pattern specialCharacters = Pattern.compile("[!@#$%^&*()\\-+]");

    public static void main(String[] args) {
        String password;
        System.out.println("Enter your password: ");
        password = scanner.nextLine();
        System.out.printf("Result: %d \n", minimumNumber(password.length(), password));
    }

    public static int minimumNumber(int n, String password) {
        int count = 0;
        if (!checkHaveNumber(password)) count++;
        if (!checkHaveLowerCase(password)) count++;
        if (!checkHaveUpperCase(password)) count++;
        if (!checkHaveSpecialCharacter(password)) count++;
        count += Math.max((minimumPasswordLength - count - n), 0);
        return count;
    }

    private static boolean checkHaveNumber(String s) {
        return checkingPattern(s, numberPattern);
    }

    private static boolean checkHaveLowerCase(String s) {
        return checkingPattern(s, lowerCasePattern);
    }

    private static boolean checkHaveUpperCase(String s) {
        return checkingPattern(s, upperCasePattern);
    }

    private static boolean checkHaveSpecialCharacter(String s) {
        return checkingPattern(s, specialCharacters);
    }

    private static boolean checkingPattern(String s, Pattern pattern) {
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }
}
