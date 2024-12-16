import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int count = 0;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

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

		move(r, c, d);

		System.out.println(count);
	} // end of main()

	static void move(int r, int c, int d) {
		// 청소되지 않은 빈 칸이라면
		if (map[r][c] == 0) {
			map[r][c] = 2; // 청소된 방은 2로 표시
			count++;
		}

		for (int i = 0; i < 4; i++) {
			// 반시계 방향으로 회전
			d = (d + 3) % 4;

			// 앞에 있는 칸이 청소되지 않은 빈칸이라면
			if (map[r + dr[d]][c + dc[d]] == 0) {
				move(r + dr[d], c + dc[d], d);
				return;
			}
		}
		
		// 주변 4칸 중 청소되지 않은 빈 칸이 없다면 후진
		int back = (d + 2) % 4;
		if (map[r + dr[back]][c + dc[back]] != 1) {
		    move(r + dr[back], c + dc[back], d); // 후진하면서 방향은 그대로 유지
		}
		// 후진한 곳이 벽이라면
		else {
		    return;
		}

	} // end of move
} // end of Main