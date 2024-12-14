import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] T;
	static int[] P;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		T = new int[N + 1];
		P = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(maxProfit());
	} // end of main()
	
	static int maxProfit() {
		int[] DP = new int[N + 2];
		
		for (int i = N; i > 0; i--) {
			// 상담을 했을 때 다음 상담 가능 날짜
			int next = i + T[i];
			
			// 상담을 할 수 있다면
			if (next <= N + 1) {
				DP[i] = Math.max(DP[i + 1], P[i] + DP[next]);
			}
			// 상담을 할 수 없다면
			else {
				DP[i] = DP[i + 1];
			}
		}
		
		return DP[1];
	} // end of dfs()
} // end of Main