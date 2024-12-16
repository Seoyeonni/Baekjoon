import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] S;
	static int min = Integer.MAX_VALUE;
	static boolean[] chk;
	
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
		
		chk = new boolean[N + 1];
		chk[1] = true;
		chkTeam(1, 1);
		
		System.out.println(min);
	} // end of main()
	
	static void chkTeam(int count, int before) {
		if (count == N / 2) {
			calcMin();
			return;
		}
		
		for (int i = 2; i <= N; i++) {
			if (!chk[i] && i > before) {
				chk[i] = true;
				chkTeam(count + 1, i);
				chk[i] = false;
			}
		}
	}
	
	static void calcMin() {
		int sum1 = 0;
		int sum2 = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (chk[i] && chk[j]) {
					sum1 += S[i][j];
				}
				else if (!chk[i] && !chk[j]){
					sum2 += S[i][j];
				}
			}
		}
		
		min = Math.min(min, Math.abs(sum1 - sum2));
	}
} // end of Main