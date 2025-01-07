import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static List<int[]> inactVirusList = new ArrayList<>();
	static Deque<int[]> actVirusList = new LinkedList<>();
	static int min = Integer.MAX_VALUE;
	static int empty = 0;

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

				// 바이러스라면
				if (map[r][c] == 2)
					inactVirusList.add(new int[] { r, c });
				// 빈칸이라면
				if (map[r][c] == 0)
					empty++;
			}
		}

		getAns();
	} // end of Main()

	static void getAns() {
		setActVirusList(0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	// dfs
	static void setActVirusList(int idx) {
		// 활성 상태로 변경할 바이러스 M개를 선택했다면
		if (actVirusList.size() == M) {
			int[][] curMap = spreadVirus();

			int max = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 바이러스가 퍼지지 않은 칸이 있다면
					if (curMap[r][c] == 0)
						return;
					max = Math.max(max, curMap[r][c]);
				}
			}
			min = Math.min(min, max - 3);
			return;
		}

		for (int i = idx; i < inactVirusList.size(); i++) {
			actVirusList.offer(inactVirusList.get(i));
			map[inactVirusList.get(i)[0]][inactVirusList.get(i)[1]] = 3; // 바이러스 활성
			setActVirusList(i + 1);
			actVirusList.pollLast(); // 백트래킹
			map[inactVirusList.get(i)[0]][inactVirusList.get(i)[1]] = 2; // 바이러스 비활성
		}
	}

	static int[][] spreadVirus() {
		// 현재 연구소 상태 초기화
		int[][] curMap = new int[N][N];
		for (int r = 0; r < N; r++)
			curMap[r] = Arrays.copyOf(map[r], N);

		// 방문 여부 확인 배열 초기화
		boolean[][] visited = new boolean[N][N];
		for (int[] virus : actVirusList)
			visited[virus[0]][virus[1]] = true; // 초기 활성 바이러스 방문 표시
		
		int curEmpty = empty;

		Deque<int[]> tempQueue = new LinkedList<>(actVirusList);
		while (!tempQueue.isEmpty()) {
			int[] virus = tempQueue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = virus[0] + dr[d];
				int nc = virus[1] + dc[d];

				// 범위를 벗어나거나 벽이거나 이미 방문했다면
				if (nr < 0 || nr > N - 1 || nc < 0 || nc > N - 1 || curMap[nr][nc] == 1 || visited[nr][nc])
					continue; // 넘어감

				visited[nr][nc] = true; // 방문 표시
				
				// 빈칸이면
				if (curMap[nr][nc] == 0) {
					curEmpty--;
	                curMap[nr][nc] = curMap[virus[0]][virus[1]] + 1; // 시간 증가
				}
				// 비활성 바이러스라면
				else if (curMap[nr][nc] == 2) {
					// 남은 빈칸이 없다면
					if (curEmpty == 0) {
						curMap[nr][nc] = curMap[virus[0]][virus[1]]; // 시간 증가 없음
						return curMap;
					}
					curMap[nr][nc] = curMap[virus[0]][virus[1]] + 1; // 시간 증가
				}
				
				tempQueue.offer(new int[] { nr, nc }); // 큐에 추가
			}
		}

		return curMap;
	}
} // end of Main
