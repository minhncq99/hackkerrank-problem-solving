package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MissingNumbers {
    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        BufferedReader bufferedReader = null;
        try {
            Path path = Paths.get("");
            fr = new FileReader(path.toAbsolutePath().toString() + "/src/resources/missingNumber.txt");
            bufferedReader = new BufferedReader(fr);
            while (bufferedReader.ready()) {
                List<Integer> arr = stringToList(bufferedReader.readLine());
                List<Integer> brr = stringToList(bufferedReader.readLine());
                List<Integer> result = solving(arr, brr);
                System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (fr != null) fr.close();
            if (bufferedReader != null) bufferedReader.close();
        }
    }

    private static List<Integer> solving(List<Integer> arr, List<Integer> brr) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();

        arr.stream().forEach(i -> {
            Integer val = aMap.get(i);
            if (val == null) {
                aMap.put(i, 1);
            } else {
                aMap.replace(i, val+1);
            }
        });
        brr.stream().forEach(i -> {
            Integer val = bMap.get(i);
            if (val == null) {
                bMap.put(i, 1);
            } else {
                bMap.replace(i, val+1);
            }
        });

        bMap.forEach((i, v) -> {
            Integer val = aMap.get(i);
            if (val == null || val < v)
                result.add(i);
        });
        return result;
    }

    private static List<Integer> stringToList(String s) {
        String[] numbList = s.split("-")[1].split(" ");
        List<Integer> list = Arrays.stream(numbList)
                .filter(str -> !str.trim().equals(""))
                .map(str -> Integer.valueOf(str)).collect(Collectors.toList());
        return list;
    }
}
