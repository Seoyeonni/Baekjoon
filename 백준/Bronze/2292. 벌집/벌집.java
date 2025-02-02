import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		if (N == 1)
			System.out.println(1);
		else {
			int count = 1;
			int rangeMin = 2;
			
			while (rangeMin <= N) {
				rangeMin = rangeMin + (6 * count);
				count++;
			}
			System.out.println(count);
		}
	} // end of main()

} // end of Main
