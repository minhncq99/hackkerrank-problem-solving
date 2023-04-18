package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoveLetterMystery {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("");
		FileReader fr = null;
		BufferedReader bufferedReader = null;
		try {
			fr = new FileReader(path.toAbsolutePath().toString() + "/src/resources/loveLetterMystery.txt");
			bufferedReader = new BufferedReader(fr);
			
			while(bufferedReader.ready()) {
				List<String> list = stringToList(bufferedReader.readLine());
				list.forEach(s -> System.out.println(solving(s)));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (fr != null) fr.close();
			if (bufferedReader != null) bufferedReader.close();
		}
	}
	
	public static int solving(String s) {
		int result = 0;
		String[] arr = s.split("");
		for (int i = 0; i < s.length()/2; i++) {
			int count = Math.abs((int)arr[i].charAt(0) - (int)arr[arr.length -i -1].charAt(0));
			result += count;
		}
		return result;
	}
	
	public static List<String> stringToList(String str) {
		String strTestCase = str.split("-")[1];
		List<String> result = Arrays.stream(strTestCase.split(" "))
				.map(s -> s.trim()).filter(s -> (!s.equals("")))
				.collect(Collectors.toList());
		return result;
	}
}
