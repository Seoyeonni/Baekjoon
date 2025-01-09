import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] A, zone;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(getAns());
	} // end of Main()

	static int getAns() {
		int ans = Integer.MAX_VALUE;

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d2 = 1; y + d2 <= N; d2++) {
					for (int d1 = 1; x + d1 + d2 <= N; d1++) {
						if (y - d1 < 1)
							continue;
						divide(x, y, d1, d2);
						int min = getDiff();
						ans = Math.min(ans, min);
					}
				}
			}
		}

		return ans;
	}

	static void divide(int x, int y, int d1, int d2) {
		zone = new int[N + 1][N + 1];

		// 5번 선거구
		for (int r = x, c = y; r <= x + d1 && c >= y - d1; r++, c--)
			zone[r][c] = 5;

		for (int r = x, c = y; r <= x + d2 && c <= y + d2; r++, c++)
			zone[r][c] = 5;

		for (int r = x + d1, c = y - d1; r <= x + d1 + d2 && c <= y - d1 + d2; r++, c++)
			zone[r][c] = 5;

		for (int r = x + d2, c = y + d2; r <= x + d2 + d1 && c >= y + d2 - d1; r++, c--)
			zone[r][c] = 5;

		// 1번 선거구
		for (int r = 1; r < x + d1; r++) {
			for (int c = 1; c <= y; c++) {
				if (zone[r][c] == 5)
					break;
				zone[r][c] = 1;
			}
		}

		// 2번 선거구
		for (int r = 1; r <= x + d2; r++) {
			for (int c = N; c > y; c--) {
				if (zone[r][c] == 5)
					break;
				zone[r][c] = 2;
			}
		}

		// 3번 선거구
		for (int r = x + d1; r <= N; r++) {
			for (int c = 1; c < y - d1 + d2; c++) {
				if (zone[r][c] == 5)
					break;
				zone[r][c] = 3;
			}
		}

		// 4번 선거구
		for (int r = x + d2 + 1; r <= N; r++) {
			for (int c = N; c >= y - d1 + d2; c--) {
				if (zone[r][c] == 5)
					break;
				zone[r][c] = 4;
			}
		}

//		for (int r = 1; r <= N; r++) {
//			for (int c = 1; c <= N; c++) {
//				System.out.print(zone[r][c] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}

	static int getDiff() {
		Map<Integer, Integer> map = new HashMap<>();

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int z = zone[r][c] == 0 ? 5 : zone[r][c];
				map.put(z, map.getOrDefault(z, 0) + A[r][c]);
			}
		}

		for (int i = 1; i <= 5; i++) {
			map.putIfAbsent(i, 0);
		}

		int max = map.get(1);
		int min = map.get(1);

		for (int i = 2; i <= 5; i++) {
			max = Math.max(max, map.get(i));
			min = Math.min(min, map.get(i));
		}

		return max - min;
	}

} // end of Main
