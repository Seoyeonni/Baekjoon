import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String str;
	static int[] counts = new int[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toUpperCase();

		int max = 0;
		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - 'A';
			counts[idx]++;
			max = Math.max(max, counts[idx]);
		}

		int cnt = 0;
		char ans = '.';
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] == max) {
				cnt++;
				if (cnt > 1) {
					System.out.println("?");
					return;
				}
				ans = (char) (i + 'A');
			}
		}
		System.out.println(ans);
	} // end of main()

} // end of Main
