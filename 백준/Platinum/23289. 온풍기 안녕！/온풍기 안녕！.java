import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K, W;
	static int[][] map;
	static boolean[][][] wall;
	static List<int[]> heaterList = new ArrayList<>();
	static List<int[]> researchList = new ArrayList<>();
	static final int Right = 1, Left = 2, UP = 3, Down = 4; // 우좌상하
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	static int[][] windDr = { {}, { -1, 0, 1 }, { -1, 0, 1 }, { -1, -1, -1 }, { 1, 1, 1 } };
	static int[][] windDc = { {}, { 1, 1, 1 }, { -1, -1, -1 }, { -1, 0, 1 }, { -1, 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= C; c++) {
				int d = Integer.parseInt(st.nextToken());
				if (d > 0 && d < 5)
					heaterList.add(new int[] { r, c, d });
				else if (d == 5)
					researchList.add(new int[] { r, c });
			}
		}

		W = Integer.parseInt(br.readLine());
		wall = new boolean[R + 1][C + 1][5];
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			if (t == 0) {
				wall[x][y][UP] = true;
				wall[x - 1][y][Down] = true;
			} else {
				wall[x][y][Right] = true;
				wall[x][y + 1][Left] = true;
			}
		}

		System.out.println(getAns());
	} // end of main()

	static int getAns() {
		int ans = 0;

		while (ans < 100) {
			wind(); // 온풍기에서 바람이 나옴
			setTemp(); // 온도 조절
			reduceTemp(); // 온도 감소
			ans++; // 초콜릿 먹기
			if (research())
				return ans;
		}
		
		return 101;
	} // getAns()
	
	static boolean research() {
		for (int[] node : researchList) {
			if (map[node[0]][node[1]] < K)
				return false;
		}
		
		return true;
	} // end of check()
	
	static void reduceTemp() {
		for (int r = 1; r <= R; r++) {
			if (map[r][1] > 0)
				map[r][1]--;
			if (map[r][C] > 0)
				map[r][C]--;
		}
		for (int c = 2; c < C; c++) {
			if (map[1][c] > 0)
				map[1][c]--;
			if (map[R][c] > 0)
				map[R][c]--;
		}
	} // end of reduceTemp()
	
	static void setTemp() {
		int[][] newMap = new int[R + 1][C + 1];
		
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				for (int d = 1; d <= 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위를 벗어나거나 벽이 있다면
					if (nr <= 0 || nr > R || nc <= 0 || nc > C || wall[r][c][d])
						continue; // 넘어감
					
					// 현재 칸이 온도가 더 높다면
					if (map[r][c] > map[nr][nc]) {
						int temp = (map[r][c] - map[nr][nc]) / 4;
						newMap[r][c] -= temp;
						newMap[nr][nc] += temp;
					}
				}
			}
		}
		
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++)
				map[r][c] += newMap[r][c];
		}
	} // end of setTemp()

	static void wind() {
		for (int[] heater : heaterList) {
			int d = heater[2];
			// 온도가 증가하는 첫 칸
			int x = heater[0] + dr[d];
			int y = heater[1] + dc[d];
			map[x][y] += 5; // 온도 증가

			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] { x, y, 4 }); // 다음 칸의 온도를 저장

			boolean[][] visited = new boolean[R + 1][C + 1];
			visited[x][y] = true;

			while (!q.isEmpty()) {
				int[] node = q.poll();
				int r = node[0];
				int c = node[1];
				int t = node[2];
				
				for (int i = 0; i < 3; i++) {
					int nr = r + windDr[d][i];
					int nc = c + windDc[d][i];
					
					// 범위를 벗어나거나 이미 방문했다면
					if (nr <= 0 || nr > R || nc <= 0 || nc > C || visited[nr][nc])
						continue; // 넘어감
					
					// 현재 칸이 옆칸이라면
					if (i == 1) {
						// 벽이 있다면
						if (wall[r][c][d])
							continue; // 넘어감
					}
					// 현재 칸이 대각선 칸이라면
					else {
						// 오른쪽 또는 왼쪽으로 바람이 분다면
						if (d == 1 || d == 2) {
							// 벽이 있다면
							if (i == 0 && (wall[r][c][UP] || wall[nr][c][d]))
								continue; // 넘어감
							// 벽이 있다면
							else if (i == 2 && (wall[r][c][Down] || wall[nr][c][d]))
								continue; // 넘어감
						}
						// 위쪽 또는 아래쪽으로 바람이 분다면
						else {
							// 벽이 있다면
							if (i == 0 && (wall[r][c][Left] || wall[r][nc][d]))
								continue; // 넘어감
							// 벽이 있다면
							else if (i == 2 && (wall[r][c][Right] || wall[r][nc][d]))
								continue; // 넘어감
						}
					}
					
					map[nr][nc] += t; // 해당 칸 온도 증가
					visited[nr][nc] = true; // 방문 표시
					
					// 다음 칸의 온도를 증가시킬 수 있다면
					if (t - 1 > 0)
						q.add(new int[] { nr, nc, t - 1 });
				}
			}
		}
	} // end of wind()

} // end of Main
