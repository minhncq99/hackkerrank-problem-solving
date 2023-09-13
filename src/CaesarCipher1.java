package src;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaesarCipher1 {

    private static final int ALPHABET_LENGTH = 26;
    private static final Scanner SC = new Scanner(System.in);
    private static final int ASCII_LOWER_A_CODE = (int) 'a';
    private static final int ASCII_LOWER_Z_CODE = (int) 'z';
    private static final int ASCII_UPPER_A_CODE = (int) 'A';
    private static final int ASCII_UPPER_Z_CODE = (int) 'Z';
    private static final Pattern LOWER_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern UPPER_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern CHARACTER_PATTERN = Pattern.compile("\\w");

    public static void main(String[] args) {
        String encriptString;
        int step;
        System.out.println("Enter your encrypt text: ");
        encriptString = SC.nextLine();
        System.out.println("Enter your step: ");
        step = SC.nextInt();
        System.out.printf("Result: %s \n", caesarCipher(encriptString, step));
    }

    public static String caesarCipher(String s, int k) {
        StringBuilder encryptStringBuilder = new StringBuilder();
        char[] sourceCharList = s.toCharArray();
        for (char c : sourceCharList) {
            encryptStringBuilder.append(transformCharacter(c, k));
        }
        return encryptStringBuilder.toString();
    }

    private static char transformCharacter(char source, int rotatedStep) {
        if (isCharacter(source)) {
            int asciiChar = ((int) source) + (rotatedStep % ALPHABET_LENGTH);

            if (isLower(source)) {
                return (char) ((asciiChar > ASCII_LOWER_Z_CODE)
                        ? ((asciiChar % ASCII_LOWER_Z_CODE) + ASCII_LOWER_A_CODE -1)
                        : asciiChar);
            }

            if (isUpper(source)) {
                return (char) ((asciiChar > ASCII_UPPER_Z_CODE)
                        ? ((asciiChar % ASCII_UPPER_Z_CODE) + ASCII_UPPER_A_CODE -1)
                        : asciiChar);
            }
        }
        return source;
    }

    private static boolean isCharacter(char c) {
        return checkingPattern(c, CHARACTER_PATTERN);
    }

    private static boolean isLower(char c) {
        return checkingPattern(c, LOWER_PATTERN);
    }

    private static boolean isUpper(char c) {
        return checkingPattern(c, UPPER_PATTERN);
    }

    private static boolean checkingPattern(char c, Pattern pattern) {
        Matcher matcher = pattern.matcher(String.valueOf(c));
        return matcher.find();
    }
}
