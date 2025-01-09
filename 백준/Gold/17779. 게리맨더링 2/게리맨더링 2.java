import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] A;
	static int totalSum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				totalSum += A[r][c];
			}
		}

		System.out.println(getAns());
	} // end of Main()

	static int getAns() {
		int ans = Integer.MAX_VALUE;

		for (int x = 1; x < N; x++) {
			for (int y = 1; y < N; y++) {
				for (int d2 = 1; y + d2 <= N; d2++) {
					for (int d1 = 1; x + d1 + d2 <= N; d1++) {
						if (y - d1 < 1)
							continue;
						
						int min = getDiff(x, y, d1, d2);
						ans = Math.min(ans, min);
					}
				}
			}
		}

		return ans;
	} // end of getAns()

	static int getDiff(int x, int y, int d1, int d2) {
		int[][] zone = new int[N + 1][N + 1];
		int[] sumArr = new int[5];

		// 5번 선거구 경계 표시
		for (int i = 0; i <= d1; i++) {
			zone[x + i][y - i] = 5;
			zone[x + d2 + i][y + d2 - i] = 5;
		}
		for (int i = 0; i <= d2; i++) {
			zone[x + i][y + i] = 5;
			zone[x + d1 + i][y - d1 + i] = 5;
		}

		// 1번 선거구
		for (int r = 1; r < x + d1; r++) {
			for (int c = 1; c <= y; c++) {
				if (zone[r][c] == 5)
					break;
				sumArr[1] += A[r][c]; // 인구 계산
			}
		}

		// 2번 선거구
		for (int r = 1; r <= x + d2; r++) {
			for (int c = N; c > y; c--) {
				if (zone[r][c] == 5)
					break;
				sumArr[2] += A[r][c]; // 인구 계산
			}
		}

		// 3번 선거구
		for (int r = x + d1; r <= N; r++) {
			for (int c = 1; c < y - d1 + d2; c++) {
				if (zone[r][c] == 5)
					break;
				sumArr[3] += A[r][c]; // 인구 계산
			}
		}

		// 4번 선거구
		for (int r = x + d2 + 1; r <= N; r++) {
			for (int c = N; c >= y - d1 + d2; c--) {
				if (zone[r][c] == 5)
					break;
				sumArr[4] += A[r][c]; // 인구 계산
			}
		}
		
		// 5번 선거구 인구 계산
		sumArr[0] = totalSum;
		for (int i = 1; i <= 4; i++)
			sumArr[0] -= sumArr[i];
		
		Arrays.sort(sumArr); // 정렬
		
		return sumArr[4] - sumArr[0];
	} // end of divide()

} // end of Main
