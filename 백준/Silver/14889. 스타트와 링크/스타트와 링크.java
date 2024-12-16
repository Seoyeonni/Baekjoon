import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] S;
	static int min = Integer.MAX_VALUE;
	static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		
		S = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		check = new boolean[N + 1];
		check[1] = true;
		dfs(1, 1);
		
		System.out.println(min);
	} // end of main()
	
	static void dfs(int count, int before) {
		if (count == N / 2) {
			calcMin();
			return;
		}
		
		for (int i = before + 1; i <= N; i++) {
			if (!check[i]) {
				check[i] = true;
				dfs(count + 1, i);
				check[i] = false;
			}
		}
	}
	
	static void calcMin() {
		int sum1 = 0;
		int sum2 = 0;
		
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (check[i] && check[j]) {
					sum1 += S[i][j];
					sum1 += S[j][i];
				}
				else if (!check[i] && !check[j]){
					sum2 += S[i][j];
					sum2 += S[j][i];
				}
			}
		}
		
		min = Math.min(min, Math.abs(sum1 - sum2));
		
		// 현재 최솟값이 0이라면 더 이상 탐색할 필요 없음
		if (min == 0) {
			System.out.println(min);
			System.exit(0);
		}
	}
} // end of Main