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
	static int K;
	static int[][] board;
	static final int apple = 1;
	static int L;
	static Map<Integer, String> turn;
	static int time = 0;
	static Deque<int[]> snake;

	static final int north = 0;
	static final int east = 1;
	static final int south = 2;
	static final int west = 3;
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		StringTokenizer st;

		board = new int[N + 2][N + 2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int ar = Integer.parseInt(st.nextToken());
			int ac = Integer.parseInt(st.nextToken());
			board[ar][ac] = apple;

		}

		L = Integer.parseInt(br.readLine());

		turn = new HashMap<Integer, String>(L);
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			String value = st.nextToken();
			turn.put(key, value);
		}

		snake = new ArrayDeque<>();
		snake.offerFirst(new int[] { 1, 1 });

		gameStart();

		System.out.println(time);
	} // end of Main()

	static void gameStart() {
		int nr = 1;
		int nc = 2;
		int d = east;

		while (true) {
			time++; // 초 증가

			// 벽에 부딪히면 게임 종료
			if (nr < 1 || nr > N || nc < 1 || nc > N)
				return;

			// 자기자신의 몸과 부딪히면 게임 종료
			for (int[] body : snake) {
				if (body[0] == nr && body[1] == nc)
					return;
			}

			// 몸길이를 늘려 머리를 다음칸에 위치
			snake.offerFirst(new int[] { nr, nc });

			// 이동한 칸에 사과가 있다면
			if (board[nr][nc] == apple) {
				// 사과가 없어지고 꼬리는 움직이지 않음
				board[nr][nc] = 0;
			}
			// 이동한 칸에 사과가 없다면
			else {
				// 몸길이를 줄여서 꼬리가 위치한 칸을 비움
				snake.removeLast();
			}

			// 방향을 회전해야 한다면
			if (turn.containsKey(time)) {
				// 왼쪽으로 회전
				if (turn.get(time).equals("L")) {
					d = (d - 1 + 4) % 4;
				}
				// 오른쪽으로 회전
				else {
					d = (d + 1 + 4) % 4;
				}
			}

			nr = nr + dr[d];
			nc = nc + dc[d];
		}
	}

} // end of Main
