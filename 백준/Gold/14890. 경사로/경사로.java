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
			if (isValidRoad(map[r]))
				count++;
		}

		// 세로
		for (int c = 0; c < N; c++) {
			int[] arr = new int[N];
			for (int r = 0; r < N; r++) {
				arr[r] = map[r][c];
			}
			if (isValidRoad(arr))
				count++;
		}

		System.out.println(count);
	} // end of countRoad()

	static boolean isValidRoad(int[] road) {
		boolean[] visited = new boolean[N]; // 경사로 설치 여부

		for (int i = 0; i < N - 1; i++) {
			int diff = road[i] - road[i + 1];

			// 높이가 같다면
			if (diff == 0)
				continue;

			// 높이가 1 감소한다면
			if (diff == 1) {
				// 높이가 감소한 칸부터 경사로 크기 만큼
				for (int j = i + 1; j <= i + L; j++) {
					// 범위를 벗어나거나 높이가 다르다면
					if (j >= N || road[j] != road[i + 1])
						return false;
					visited[j] = true;
				}
			}
			// 높이가 1 증가한다면
			else if (diff == -1) {
				// 높이가 증가한 칸 전부터 경사로 크기 만큼
				for (int j = i; j > i - L; j--) {
					// 범위를 벗어나거나 높이가 다르거나 이미 경사로를 놓았다면
					if (j < 0 || road[j] != road[i] || visited[j])
						return false;
					visited[j] = true;
				}
			}
			// 높이 차이가 1보다 크다면
			else
				return false;
		}
		return true;
	} // end of judgRoad()

} // end of Main
