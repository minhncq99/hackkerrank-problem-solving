package src;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Gemstones {
    public static void main(String[] args) throws IOException {
        FileReader fr =  null;
        BufferedReader bufferedReader = null;
        try {
            Path currentPath = Paths.get("");
            fr = new FileReader(currentPath.toAbsolutePath().toString() + "/src/resources/gemstones.txt");
            bufferedReader = new BufferedReader(fr);

            while (bufferedReader.ready()) {
                String str = bufferedReader.readLine();
                List<String> arr = strToStones(str);
                System.out.println(solving(arr));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (fr != null) fr.close();
            if (bufferedReader != null) bufferedReader.close();
        }
    }

    public static int solving(List<String> arr) {
        int result = 0;
        List<String> minerals = Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).collect(Collectors.toList());
        for (int mineralI = 0; mineralI < minerals.size(); mineralI++) {
            int rockLength = arr.size();
            int count = 0;
            for (int rockI = 0; rockI < arr.size(); rockI++) {
                if (arr.get(rockI).contains(minerals.get(mineralI)))
                    count++;
            }
            if (count == rockLength) result++;
        }
        return result;
    }

    public static List<String> strToStones(String str) {
        String stones[] = str.split("-")[1].split(" ");
        List<String> result = Arrays.stream(stones).map(s -> s.trim())
                .filter(s -> (s!=null && !s.equals(""))).collect(Collectors.toList());
        return result;
    }
}