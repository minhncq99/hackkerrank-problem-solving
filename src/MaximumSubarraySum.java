package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaximumSubarraySum {
    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            Path path = Paths.get("");
            fr = new FileReader(path.toAbsolutePath().toString() + "/src/resources/maximumSubarraySum.txt");
            br = new BufferedReader(fr);
            while (br.ready()) {
                Map<Long, List<Long>> map = stringToMap(br.readLine());
                Long key = map.keySet().iterator().next();
                System.out.println(maximumSum(map.get(key), key));
            }
        } catch (Exception ex) {
            System.out.println("Ex: " + ex.getMessage());
        } finally {
            if (fr!=null) fr.close();
            if (br!=null) br.close();
        }
    }

    // Todo: Need to optimize running time
    private static long maximumSum(List<Long> arr, long m) {
        Long maxResult = 0L;
        Long sumResult = 0L;
        int size = arr.size();

        for (Long l : arr) {
            sumResult += l;
            maxResult = Math.max(maxResult, l % m); // check max with i = 0
        }
        maxResult = Math.max(maxResult, sumResult % m); // check max with i = arr.size-1

        Long sumOfDefault = sumResult - arr.get(size -1);
        for (int offset = 1; offset < size; offset++) {
            Long sumOfGroup = sumOfDefault;
            maxResult = Math.max(maxResult, sumOfGroup % m);
            for (int step = 1; step <= offset; step++) {
                // sub left
                sumOfGroup -= arr.get(step -1);
                // add right
                sumOfGroup += arr.get(size -1 -offset +step);
                maxResult = Math.max(maxResult, sumOfGroup % m);
            }
            sumOfDefault -= arr.get(size -1 -offset);
        }

        return maxResult;
    }

    private static Map<Long, List<Long>> stringToMap(String str) {
        Map<Long, List<Long>> map = new HashMap<>();
        String[] arr = str.split("-");
        Long key = Long.valueOf(arr[0].trim());
        List<Long> values = Arrays.stream(arr[1].split(" "))
                .filter(s -> !s.trim().equals(""))
                .map(Long::valueOf).collect(Collectors.toList());
        map.put(key, values);
        return  map;
    }
}
