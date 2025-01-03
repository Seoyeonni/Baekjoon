import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int[] nums = new int[10];
	static Map<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			nums[i] = Integer.parseInt(br.readLine()) % 42;
		}
		
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		System.out.println(map.size());
	} // end of Main()

} // end of Main
