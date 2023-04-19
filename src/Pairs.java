package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pairs {

    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        BufferedReader bufferedReader = null;
        try {
            Path path = Paths.get("");
            fr = new FileReader(path.toAbsolutePath().toString() + "/src/resources/pairs.txt");
            bufferedReader = new BufferedReader(fr);
            while (bufferedReader.ready()) {
                Map<String, Object> map = getPairs(bufferedReader.readLine());
                int k = (int)map.get("k");
                List<Integer> arr = (List<Integer>) map.get("arr");
                System.out.println(pairs(k, arr));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (fr != null) fr.close();
            if (bufferedReader != null) bufferedReader.close();
        }
    }

    public static int pairs(int k, List<Integer> arr) {
        Map<Integer, Long> mapToCount = arr.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return mapToCount.keySet().stream()
                .filter(key -> mapToCount.containsKey(key + k))
                .map(key -> mapToCount.get(key) * mapToCount.get(key +k))
                .reduce(0L, Long::sum).intValue();
    }

    public static Map<String, Object> getPairs(String s) {
        Map<String, Object> map = new HashMap<>();
        String[] arr = s.split("-");
        map.put("k", Integer.valueOf(arr[1].trim()));
        map.put("arr", Arrays.stream(arr[2].split(" "))
                .filter(str -> !str.trim().equals(""))
                .map(str -> Integer.valueOf(str.trim())).collect(Collectors.toList()));

        return map;
    }
}
