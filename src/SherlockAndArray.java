package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SherlockAndArray {
    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        BufferedReader bufferedReader = null;

        try {
            Path path = Paths.get("");
            fr = new FileReader(path.toAbsolutePath().toString() + "/src/resources/sherlockAndArray.txt");
            bufferedReader = new BufferedReader(fr);

            while (bufferedReader.ready()) {
                System.out.println(balancedSums(strToListInt(bufferedReader.readLine())));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (fr!=null) fr.close();
            if (bufferedReader!=null) fr.close();
        }
    }

    private static String balancedSums(List<Integer> arr) {
        int sumLeft = 0;
        int sumRight = arr.stream().reduce(0, Integer::sum);
        for (int i = 0; i < arr.size(); i++) {
            sumRight -= arr.get(i);
            if (sumLeft == sumRight) return "YES";
            sumLeft += arr.get(i);
        }
        return "NO";
    }

    private static List<Integer> strToListInt(String str) {
        return Arrays.stream(str.split(" "))
                .filter(s -> !s.trim().equals(""))
                .map(Integer::valueOf).collect(Collectors.toList());
    }

}
