import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int a, b, c;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			if (a == 0 && b == 0 && c == 0)
				break;
			
			if (a >= b && a >= c && b + c <= c) {
				System.out.println("Invalid");
				continue;
			}
			else if (b >= c && b >= a && c + a <= b) {
				System.out.println("Invalid");
				continue;
			}
			else if (c >= a && c >= b && a + b <= c) {
				System.out.println("Invalid");
				continue;
			}
			
			if (a == b && b == c && c == a)
				System.out.println("Equilateral");
			else if (a != b && b != c && c != a)
				System.out.println("Scalene");
			else
				System.out.println("Isosceles");
		}
	} // end of main()

} // end of Main
