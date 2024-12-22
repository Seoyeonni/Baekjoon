import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[] cmd;

	static int[] dice = new int[6];
	static final int up = 0;
	static final int top = 1;
	static final int down = 2;
	static final int bottom = 3;
	static final int left = 4;
	static final int right = 5;

	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		cmd = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			cmd[i] = Integer.parseInt(st.nextToken());
		}

		move(x, y);
	} // end of Main()

	static void move(int x, int y) {
		int r = x;
		int c = y;

		for (int d : cmd) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 주사위를 이동시켰을 때 지도를 벗어나면
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			
			r = nr;
			c = nc;

			int[] ndice = new int[6];

			switch (d) {
			case 1: { // 동
				ndice[up] = dice[up];
				ndice[top] = dice[left];
				ndice[down] = dice[down];
				ndice[bottom] = dice[right];
				ndice[left] = dice[bottom];
				ndice[right] = dice[top];
				break;
			}
			case 2: { // 서
				ndice[up] = dice[up];
				ndice[top] = dice[right];
				ndice[down] = dice[down];
				ndice[bottom] = dice[left];
				ndice[left] = dice[top];
				ndice[right] = dice[bottom];
				break;
			}
			case 3: { // 북
				ndice[up] = dice[top];
				ndice[top] = dice[down];
				ndice[down] = dice[bottom];
				ndice[bottom] = dice[up];
				ndice[left] = dice[left];
				ndice[right] = dice[right];
				break;
			}
			case 4: { // 남
				ndice[up] = dice[bottom];
				ndice[top] = dice[up];
				ndice[down] = dice[top];
				ndice[bottom] = dice[down];
				ndice[left] = dice[left];
				ndice[right] = dice[right];
				break;
			}
			} // end of switch()

			dice = ndice;
			
			// 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
			if (map[r][c] == 0) {
				map[r][c] = dice[bottom];
			}
			// 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바다면으로 복사되며, 칸에 있는 수는 0이 됨
			else {
				dice[bottom] = map[r][c];
				map[r][c] = 0;
			}
			
			System.out.println(dice[top]);
		}
	}

} // end of Main
