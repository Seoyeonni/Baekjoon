import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int L;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		countRoad();
	} // end of Main()

	static void countRoad() {
		int count = 0;

		// 가로
		for (int r = 0; r < N; r++) {
			if (judgRoad(map[r])) {
				count++;
			}
		}

		// 세로
		for (int c = 0; c < N; c++) {
			int[] arr = new int[N];
			for (int r = 0; r < N; r++) {
				arr[r] = map[r][c];
			}
			if (judgRoad(arr)) {
				count++;
			}
		}

		System.out.println(count);
	} // end of countRoad()

	static boolean judgRoad(int[] road) {
		int count = 1;
		for (int i = 1; i < N; i++) {
			// 높이가 같다면
			if (road[i] - road[i - 1] == 0) {
				count++;
			}
			// 높이가 1 높아졌다면
			else if (road[i] - road[i - 1] == 1) {
				// 경사로를 놓을 수 있다면
				if (count >= L) {
					count = 1;
				}
				// 경사로를 놓을 수 없다면
				else
					return false;
			}
			// 높이가 1 낮아졌다면
			else if (road[i] - road[i - 1] == -1) {
				int tempCount = 0;
				int j;
				for (j = i; j < N; j++) {
					// 경사로를 놓을 높이가 같다면
					if (road[i] == road[j]) {
						tempCount++;
					}
					// 경사로를 놓을 높이가 다르다면
					else {
						break;
					}

					// 경사로를 놓을 수 있다면
					if (tempCount == L) {
						break;
					}
				}

				// 경사로를 놓을 수 있다면
				if (tempCount == L) {
					count = 0;
					i = j;
				}
				// 경사로를 놓을 수 없다면
				else
					return false;
			} else
				return false;
		}

		return true;
	} // end of judgRoad()

} // end of Main
