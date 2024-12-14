import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] schedule;
	static int profit = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		StringTokenizer st;

		schedule = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		System.out.println(profit);
	} // end of main()
	
	static void dfs(int i, int pay) {
		if (i >= n) {
			profit = Math.max(pay, profit);
			return;
		}
		
		// 다음 상담을 마칠 수 있다면
		if (i + schedule[i][0] <= n) {
			dfs(i + schedule[i][0], pay + schedule[i][1]);
		}
		// 다음 상담을 마칠 수 없다면
		else {
			// 탈출 조건
			dfs(i + schedule[i][0], pay);
		}
		
		// 다른 경우의 수 탐색
		dfs(i + 1, pay);
	} // end of dfs()
} // end of Main