import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] paper;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		paper = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(calcMax());
	} // end of Main()

	static int calcMax() {
		int max = 0;
		int sum = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (c + 3 < M) {
					// ■□□□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r][c + 2];
					sum += paper[r][c + 3];
					max = Math.max(max, sum);
				}

				if (r + 3 < N) {
					// ■
					// □
					// □
					// □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c];
					sum += paper[r + 2][c];
					sum += paper[r + 3][c];
					max = Math.max(max, sum);
				}

				if (r + 1 < N && c + 1 < M) {
					// ■□
					// □□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r + 1][c];
					sum += paper[r + 1][c + 1];
					max = Math.max(max, sum);
				}

				if (r + 1 < N && c + 2 < M) {
					// ■□□
					// □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r][c + 2];
					sum += paper[r + 1][c];
					max = Math.max(max, sum);
					
					// ■□□
					//   □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r][c + 2];
					sum += paper[r + 1][c + 2];
					max = Math.max(max, sum);
					
					// ■
					// □□□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c];
					sum += paper[r + 1][c + 1];
					sum += paper[r + 1][c + 2];
					max = Math.max(max, sum);
				}
				
				if (r + 1 < N && c - 2 >= 0) {
					//   ■
					// □□□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c - 2];
					sum += paper[r + 1][c - 1];
					sum += paper[r + 1][c];
					max = Math.max(max, sum);
				}
				
				if (r + 2 < N && c + 1 < M) {
					// ■□
					// □
					// □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r + 1][c];
					sum += paper[r + 2][c];
					max = Math.max(max, sum);
					
					// ■□
					//  □
					//  □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r + 1][c + 1];
					sum += paper[r + 2][c + 1];
					max = Math.max(max, sum);
					
					// ■
					// □
					// □□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c];
					sum += paper[r + 2][c];
					sum += paper[r + 2][c + 1];
					max = Math.max(max, sum);
				}
				
				if (r + 2 < N && c - 1 >= 0) {
					//  ■
					//  □
					// □□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c];
					sum += paper[r + 2][c - 1];
					sum += paper[r + 2][c];
					max = Math.max(max, sum);
				}
				
				if (r + 1 < N && c + 2 < M) {
					// ■□
					//  □□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r + 1][c + 1];
					sum += paper[r + 1][c + 2];
					max = Math.max(max, sum);
				}

				if (r + 1 < N && c - 1 >= 0 && c + 1 < M) {
					//  ■□
					// □□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r + 1][c - 1];
					sum += paper[r + 1][c];
					max = Math.max(max, sum);
				}
				
				if (r + 2 < N && c + 1 < M) {
					// ■
					// □□
					//  □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c];
					sum += paper[r + 1][c + 1];
					sum += paper[r + 2][c + 1];
					max = Math.max(max, sum);
				}
				
				if (r + 2 < N && c - 1 >= 0) {
					//  ■
					// □□
					// □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c - 1];
					sum += paper[r + 1][c];
					sum += paper[r + 2][c - 1];
					max = Math.max(max, sum);
				}
				
				if (r + 1 < N && c + 2 < M) {
					// ■□□
					//  □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r][c + 1];
					sum += paper[r][c + 2];
					sum += paper[r + 1][c + 1];
					max = Math.max(max, sum);
				}
				
				if (r + 1 < N && c - 1 >= 0 && c + 1 < M) {
					//  ■
					// □□□
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c - 1];
					sum += paper[r + 1][c];
					sum += paper[r + 1][c + 1];
					max = Math.max(max, sum);
				}
				
				if (r + 2 < N && c + 1 < M) {
					// ■
					// □□
					// □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c];
					sum += paper[r + 1][c + 1];
					sum += paper[r + 2][c];
					max = Math.max(max, sum);
				}

				if (r + 2 < N && c - 1 >= 0) {
					//  ■
					// □□
					//  □
					sum = 0;
					sum += paper[r][c];
					sum += paper[r + 1][c - 1];
					sum += paper[r + 1][c];
					sum += paper[r + 2][c];
					max = Math.max(max, sum);
				}
			}
		}

		return max;
	} // end of calcMax()

} // end of Main
