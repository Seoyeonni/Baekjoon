import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	
	static int score = 0;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		autoPlay();
		
		System.out.println(score);
	} // end of main()
	
	static void autoPlay() {
		while (true) {
			visited = new boolean[N][N];
			ArrayList<int[]> maxList = new ArrayList<>();
			int maxSize = 0;
			int maxRainbow = 0;
			int maxR = -1;
			int maxC = -1;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 일반 블록이 아니거나 이미 방문한 일반 블록이라면
					if (map[r][c] <= 0 || visited[r][c])
						continue; // 넘어감
					
					ArrayList<int[]> groubList = new ArrayList<>();
					int[] groub = getGroub(r, c, groubList);
					if (maxSize < groub[0] || // 크기가 크거나
						    (maxSize == groub[0] && // 크기가 같은데
						    (maxRainbow < groub[1] || // 무지개 블록 개수가 많거나
						    (maxRainbow == groub[1] && // 무지개 블록 개수가 같은데
						    (maxR < r || (maxR == r && maxC < c)))))) { // 기준 블록이 더 아래, 더 오른쪽이라면
							maxList = groubList;
						    maxSize = groub[0];
						    maxRainbow = groub[1];
						    maxR = r;
						    maxC = c;
						}
				}
			}
			
			// 블록 그룹이 없다면
			if (maxSize < 2)
				break; // 종료
			
			// 그룹 블록 제거
			for (int[] block : maxList)
				map[block[0]][block[1]] = -2; // 블록 제거
			score += Math.pow(maxSize, 2); // 점수 증가
			gravity(); // 중력 작용
			rotate(); // 반시계 방향 회전
			gravity(); // 중력 작용
		}
	} // end of autoPlay()
	
	static void rotate() {
		int[][] newMap = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++)
				newMap[(N - 1) - c][r] = map[r][c];
		}
		
		map = newMap;
	} // end of rotate()
	
	static void gravity() {
		for (int r = N - 2; r >= 0; r--) {
			for (int c = 0; c < N; c++) {
				// 검은색 블록 또는 빈칸이라면
				if (map[r][c] < 0)
					continue; // 넘어감
				
				for (int i = 1; r + i < N; i++) {
					// 빈칸이 아니라면
					if (map[r + i][c] != -2)
						break; // 내려갈 수 없음
					
					map[r + i][c] = map[r + i - 1][c];
					map[r + i - 1][c] = -2;
				}
			}
		}
	} // end of gravity()
	
	static int[] getGroub(int r, int c, ArrayList<int[]> groubList) {
		groubList.add(new int[] { r, c });
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r, c });
		
		boolean[][] curVisited = new boolean[N][N];
		curVisited[r][c] = true;
		visited[r][c] = true;
		
		int num = map[r][c];
		int size = 1;
		int rainbow = 0;
		while (!q.isEmpty()) {
			int[] node = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = node[0] + dr[d];
				int nc = node[1] + dc[d];
				
				// 범위를 벗어나거나 이미 방문했거나 검은색 블록 또는 빈칸이거나 무지개 블록이 아닌데 같은 색이 아니라면
				if (nr < 0 || nr >= N || nc < 0 || nc >= N ||
						curVisited[nr][nc] || map[nr][nc] < 0 || (map[nr][nc] != 0 && map[nr][nc] != num))
					continue; // 넘어감
				
				// 무지개 블록이라면
				if (map[nr][nc] == 0)
					rainbow++;
				// 무지개 블록이 아니라면
				else
					visited[nr][nc] = true;
				
				size++;
				curVisited[nr][nc] = true;
				groubList.add(new int[] { nr, nc });
				q.offer(new int[] { nr, nc });
			}
		}
		
		return new int[] { size, rainbow };
	} // end of getGroub()

} // end of Main
