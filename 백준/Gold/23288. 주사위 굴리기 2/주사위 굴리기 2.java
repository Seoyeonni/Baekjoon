import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static int[] dice = { 2, 1, 5, 6, 4, 3 };
	static final int north = 0;
	static final int top = 1;
	static final int south = 2;
	static final int bottom = 3;
	static final int west = 4;
	static final int east = 5;
	
	static int diceR = 1;
	static int diceC = 1;
	static int diceD = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(getAns());
	} // end of Main()

	static int getAns() {
		int sum = 0;

		while (K-- > 0) {
			rollDice();
			
			sum += map[diceR][diceC] * getC(map[diceR][diceC]);
			
			// 이동 방향 결정
			if (dice[bottom] > map[diceR][diceC])
				diceD = (diceD + 1) % 4; // 시계 방향으로 90도 회전
			else if (dice[bottom] < map[diceR][diceC])
				diceD = (diceD - 1 + 4) % 4; // 반시계 방향으로 90도 회전, 음수 처리
		}

		return sum;
	} // end of getAns()

	static void rollDice() {
		// 현재 이동 방향으로 주사위를 굴린 위치
		int nr = diceR + dr[diceD];
		int nc = diceC + dc[diceD];

		// 범위를 벗어난다면
		if (nr <= 0 || nr > N || nc <= 0 || nc > M) {
			diceD = (diceD + 2) % 4; // 이동 방향 반대로 뒤집기
			// 반대로 뒤집힌 이동 방향으로 주사위를 굴린 위치
			nr = diceR + dr[diceD];
			nc = diceC + dc[diceD];
		}

		// 주사위 위치 갱신
		diceR = nr;
		diceC = nc;

		// 주사위 정보 갱신
		int temp;
		switch (diceD) {
		case 0: // 동
			temp = dice[east];
			dice[east] = dice[top];
			dice[top] = dice[west];
			dice[west] = dice[bottom];
			dice[bottom] = temp;
			break;
		case 1: // 남
			temp = dice[south];
			dice[south] = dice[top];
			dice[top] = dice[north];
			dice[north] = dice[bottom];
			dice[bottom] = temp;
			break;
		case 2: // 서
			temp = dice[west];
			dice[west] = dice[top];
			dice[top] = dice[east];
			dice[east] = dice[bottom];
			dice[bottom] = temp;
			break;
		case 3: // 북
			temp = dice[north];
			dice[north] = dice[top];
			dice[top] = dice[south];
			dice[south] = dice[bottom];
			dice[bottom] = temp;
			break;
		}
	} // end of rollDice()

	// bfs
	static int getC(int B) {
		int C = 0;
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { diceR, diceC });
		
		boolean[][] visited = new boolean[N + 1][M + 1];
		visited[diceR][diceC] = true; // 방문 표시
		
		while (!q.isEmpty()) {
			int[] node = q.poll();
			C++;
			
			for (int d = 0; d < 4; d++) {
				int nr = node[0] + dr[d];
				int nc = node[1] + dc[d];
				
				// 범위를 벗어나거나 이미 방문했거나 B가 있는 칸이 아니라면
				if (nr <= 0 || nr > N || nc <= 0 || nc > M || visited[nr][nc] || map[nr][nc] != B)
					continue;
				
				visited[nr][nc] = true; // 방문 표시
				q.offer(new int[] { nr, nc });
			}
		}
		
		return C;
	} // end of getScore()

} // end of Main
