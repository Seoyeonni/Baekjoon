import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
 	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(1);
		
		System.out.println(max);
	} // end of main()
	
	static void dfs(int depth) {
  		if (depth > 5) {
			max = Math.max(max, getMax());
			return;
		}
		
		// 백업
		int[][] curMap = new int[N][N];
		for (int r = 0; r < N; r++)
			curMap[r] = Arrays.copyOf(map[r], N);
		
		for (int d = 0; d < 4; d++) {
			move(d);
			// 이동한 블록이 없다면
			if (Arrays.deepEquals(curMap, map)) {
				max = Math.max(max, getMax());
				continue; // 넘어감
			}
 			dfs(depth + 1);
			map = curMap;
		}
	} // end of dfs()
	
	static void printMap(int depth, int d) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println(depth + ", " + d);
		System.out.println();
	}
	
	static void move(int d) {
		int[][] newMap = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		switch (d) {
		case 0: // 상
		case 1: // 좌
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 빈칸이면
					if (map[r][c] == 0)
						continue; // 넘어감
					
					int nr = r;
					int nc = c;
					// 중력
					while (true) {
						nr = nr + dr[d];
						nc = nc + dc[d];
						
						// 범위를 벗어나면
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
							nr = nr - dr[d];
							nc = nc - dc[d];
							newMap[nr][nc] = map[r][c];
							break; // 멈춤
						}
						
						// 다른 블록이랑 부딪히면
						if (newMap[nr][nc] != 0) {
							// 합칠 수 있다면
							if (newMap[nr][nc] == map[r][c] && !visited[nr][nc]) {
								visited[nr][nc] = true;
								newMap[nr][nc] *= 2; // 합치기
							}
							// 합칠 수 없다면
							else {
								nr = nr - dr[d];
								nc = nc - dc[d];
								newMap[nr][nc] = map[r][c];
							}
							break; // 멈춤
						}
					}
				}
			}
			break;
		case 2: // 하
		case 3: // 우
			for (int r = N - 1; r >= 0; r--) {
				for (int c = N - 1; c >= 0; c--) {
					// 빈칸이면
					if (map[r][c] == 0)
						continue; // 넘어감
					
					int nr = r;
					int nc = c;
					// 중력
					while (true) {
						nr = nr + dr[d];
						nc = nc + dc[d];
						
						// 범위를 벗어나면
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
							nr = nr - dr[d];
							nc = nc - dc[d];
							newMap[nr][nc] = map[r][c];
							break; // 멈춤
						}
						
						// 다른 블록이랑 부딪히면
						if (newMap[nr][nc] != 0) {
							// 합칠 수 있다면
							if (newMap[nr][nc] == map[r][c] && !visited[nr][nc]) {
								visited[nr][nc] = true;
								newMap[nr][nc] *= 2; // 합치기
							}
							// 합칠 수 없다면
							else {
								nr = nr - dr[d];
								nc = nc - dc[d];
								newMap[nr][nc] = map[r][c];
							}
							break; // 멈춤
						}
					}
				}
			}
			break;
		}
		
		map = newMap;
	} // end of move()
	
	static int getMax() {
		int max = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (max < map[r][c])
					max = map[r][c];
			}
		}
		return max;
	} // end of getMax()

} // end of Main
