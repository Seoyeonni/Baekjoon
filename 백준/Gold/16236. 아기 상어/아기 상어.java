import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };
	static int sharkR, sharkC;
	static int sharkSize = 2;
	static int eatCount = 0;

	static int fishR, fishC, distance;
	static boolean[][] visited;

	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				// 아기 상어의 위치 저장
				if (map[r][c] == 9) {
					sharkR = r;
					sharkC = c;
					map[r][c] = 0; // 아기 상어 위치를 빈칸으로 초기화
				}
			}
		}

		System.out.println(getAns());
	} // end of Main()

	static int getAns() {
		int ans = 0;

		while (true) {
			// 초기화
			q = new LinkedList<>();
			visited = new boolean[N][N];
			q.offer(new int[] { sharkR, sharkC, 0 });
			visited[sharkR][sharkC] = true;

			fishR = -1;
			fishC = -1;
			distance = Integer.MAX_VALUE;

			eatFish();
			
			if (fishR == -1 && fishC == -1)
				return ans;
			else {
				// 위치를 이동하여 물고기 먹기
				sharkR = fishR;
				sharkC = fishC;
				map[fishR][fishC] = 0;

				ans += distance;
				eatCount++;

				// 자신의 크기와 같은 수의 물고기를 먹었다면
				if (eatCount == sharkSize) {
					sharkSize++; // 크기 증가
					eatCount = 0; // 먹은 물고기 수 초기화
				}
			}
		}
	} // end of getAns()

	// bfs
	static void eatFish() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			int curDist = cur[2];
			
			for (int d = 0; d < 4; d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];

				// 범위를 벗어나거나 이미 방문한 칸이라면
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
					continue; // 넘어감

				visited[nr][nc] = true; // 방문 표시

				// 자신의 크기보다 큰 물고기가 있는 칸이라면
				if (map[nr][nc] > sharkSize)
					continue; // 넘어감

				// 먹을 수 있는 물고기가 있다면
				if (map[nr][nc] > 0 && map[nr][nc] < sharkSize) {
					// 더 가까운 물고기를 찾았거나, 거리가 같지만 더 위/왼쪽에 있다면
					if (curDist + 1 < distance || 
					   (curDist + 1 == distance && (nr < fishR || (nr == fishR && nc < fishC)))) {
						// 먹을 물고기 갱신
						distance = curDist + 1;
						fishR = nr;
						fishC = nc;
					}
				}

				// 이동 가능한 칸은 큐에 추가
				q.offer(new int[] { nr, nc, curDist + 1 });
			}
		} // end of while()
	} // end of eatFish()

} // end of Main
