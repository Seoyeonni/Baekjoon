import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		move(r, c, d, 0);
	} // end of main()

	static void move(int r, int c, int d, int turn) {
		// 청소되지 않은 빈 칸이라면
		if (map[r][c] == 0) {
			map[r][c] = 2; // 청소된 방은 2로 표시
			count++;
		}

		switch (d) {
		case 0: { // 북 -> 서
			// 회전 후 앞에있는 칸이
			// 청소되지 않은 빈 칸이라면
			if (map[r][c - 1] == 0) {
				move(r, c - 1, 3, 0);
			}
			// 청소된 빈 칸이거나 벽이라면
			else {
				// 주변 4칸 중 청소되지 않은 빈 칸이 없다면
				if (turn == 4) {
					// 후진할 수 있다면
					if (map[r + 1][c] == 2) {
						move(r + 1, c, d, 0);
					}
					// 후진할 수 없다면
					else {
						System.out.println(count);
						System.exit(0); // 시스템 종료
					}
				}

				turn++;
				move(r, c, 3, turn);
			}
		}
		case 1: { // 동 -> 북
			// 회전 후 앞에있는 칸이
			// 청소되지 않은 빈 칸이라면
			if (map[r - 1][c] == 0) {
				move(r - 1, c, 0, 0);
			}
			// 청소된 빈 칸이거나 벽이라면
			else {
				// 주변 4칸 중 청소되지 않은 빈 칸이 없다면
				if (turn == 4) {
					// 후진할 수 있다면
					if (map[r][c - 1] == 2) {
						move(r, c - 1, d, 0);
					}
					// 후진할 수 없다면
					else {
						System.out.println(count);
						System.exit(0); // 시스템 종료
					}
				}

				turn++;
				move(r, c, 0, turn);
			}
		}
		case 2: { // 남 -> 동
			// 회전 후 앞에있는 칸이
			// 청소되지 않은 빈 칸이라면
			if (map[r][c + 1] == 0) {
				move(r, c + 1, 1, 0);
			}
			// 청소된 빈 칸이거나 벽이라면
			else {
				// 주변 4칸 중 청소되지 않은 빈 칸이 없다면
				if (turn == 4) {
					// 후진할 수 있다면
					if (map[r - 1][c] == 2) {
						move(r - 1, c, d, 0);
					}
					// 후진할 수 없다면
					else {
						System.out.println(count);
						System.exit(0); // 시스템 종료
					}
				}

				turn++;
				move(r, c, 1, turn);
			}
		}
		case 3: { // 서 -> 남
			// 회전 후 앞에있는 칸이
			// 청소되지 않은 빈 칸이라면
			if (map[r + 1][c] == 0) {
				move(r + 1, c, 2, 0);
			}
			// 청소된 빈 칸이거나 벽이라면
			else {
				// 주변 4칸 중 청소되지 않은 빈 칸이 없다면
				if (turn == 4) {
					// 후진할 수 있다면
					if (map[r][c + 1] == 2) {
						move(r, c + 1, d, 0);
					}
					// 후진할 수 없다면
					else {
						System.out.println(count);
						System.exit(0); // 시스템 종료
					}
				}

				turn++;
				move(r, c, 2, turn);
			}
		}
		}
	}
} // end of Main