package src;

import java.util.Scanner;

public class SuperReducedString {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input your text:");
        String source = scanner.nextLine();
        System.out.printf("Result is: %s%n", superReducedString(source));
    }

//    public static String superReducedString(String s) {
//        StringBuilder strBuilder = new StringBuilder(s);
//        for (int index = 0; index < s.length() -1; index++) {
//            int nextIndex = index +1;
//            if (compare2CharEqual(strBuilder, index, nextIndex)) {
//                strBuilder.delete(index, nextIndex +1);
//                return superReducedString(strBuilder.toString());
//            }
//        }
//        String result = strBuilder.toString();
//        return result.isEmpty() ? "Empty String" : result;
//    }

    public static String superReducedString(String s) {
        StringBuilder strBuilder = new StringBuilder(s);
        for (int index = 0; index < strBuilder.length() -1; index++) {
            int nextIndex = index +1;
            if (compare2CharEqual(strBuilder, index, nextIndex)) {
                strBuilder.delete(index, nextIndex +1);
                index = -1;
            }
        }
        String result = strBuilder.toString();
        return result.isEmpty() ? "Empty String" : result;
    }

    private static boolean compare2CharEqual(
            StringBuilder strBuilder,
            int firstIndex,
            int secondIndex) {
        return strBuilder.charAt(firstIndex) == strBuilder.charAt(secondIndex);
    }
}
