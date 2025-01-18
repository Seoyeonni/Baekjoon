import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, fuel;
	static int[][] map;
	static int taxiR, taxiC;
	static Passenger[] passengers;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };
	static int curId = 0;
	static boolean[][] visited;
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()) * -1; // 벽은 -1
			}
		}

		st = new StringTokenizer(br.readLine());
		taxiR = Integer.parseInt(st.nextToken());
		taxiC = Integer.parseInt(st.nextToken());

		passengers = new Passenger[M + 1];
		for (int id = 1; id <= M; id++) {
			st = new StringTokenizer(br.readLine());

			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			int endR = Integer.parseInt(st.nextToken());
			int endC = Integer.parseInt(st.nextToken());

			passengers[id] = new Passenger(startR, startC, endR, endC);
			map[startR][startC] = id;
		}

		visited = new boolean[N + 1][N + 1];

		System.out.println(getAns());
	} // end of main()

	static int getAns() {
		for (int i = 0; i < M; i++) {
			int dist = getStartDist(M - i);
			// 승객을 태울 수 없다면
			if (dist == -1)
				return -1; // 운행 종료

			// 승객 태우기
			taxiR = passengers[curId].startR;
			taxiC = passengers[curId].startC;
			fuel -= dist; // 연료 감소
			// 연료가 바닥났다면
			if (fuel <= 0) // 0 이하면 목적지로 이동할 수 없음
				return -1; // 운행 종료
//			System.out.println(curId + "번 승객까지 이동 거리: " + dist + ", 연료: " + fuel); // 디버깅

			dist = getEndDist();
			// 승객 목적지로 갈 수 없다면
			if (dist == -1)
				return -1; // 운행 종료

			// 승객 목적지 도착
			taxiR = passengers[curId].endR;
			taxiC = passengers[curId].endC;
			fuel -= dist; // 연료 감소
			// 연료가 바닥났다면
			if (fuel < 0) // 승객을 목적지로 이동시킨 동시에 연료가 바닥나는 경우 제외
				return -1; // 운행 종료
//			System.out.println(curId + "번 승객 목적지까지 이동 거리: " + dist + ", 연료: " + fuel); // 디버깅
			fuel += dist * 2; // 연료 충전
//			System.out.println("연료: " + fuel); // 디버깅
//			System.out.println(); // 디버깅
		}

		return fuel;
	} // end of getAns()

	static int getStartDist(int alive) {
		// 승객이 있다면
		if (map[taxiR][taxiC] > 0) {
			curId = map[taxiR][taxiC]; // 승객 id 표시
			map[taxiR][taxiC] = 0; // 빈칸 설정
			return 0; // 거리 반환
		}

		q.offer(new int[] { taxiR, taxiC, 0 });

		// 방문 표시 배열 초기화
		for (int r = 1; r <= N; r++) {
			Arrays.fill(visited[r], false);
		}
		visited[taxiR][taxiC] = true; // 방문 표시

		int id = 0;
		int count = 0;
		int r = Integer.MAX_VALUE;
		int c = Integer.MAX_VALUE;
		int dist = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			int[] node = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = node[0] + dr[d];
				int nc = node[1] + dc[d];

				// 범위를 벗어나거나 벽이거나 이미 방문했다면
				if (nr <= 0 || nr > N || nc <= 0 || nc > N || map[nr][nc] == -1 || visited[nr][nc])
					continue; // 넘어감
				
				// 승객이 있다면
				if (map[nr][nc] > 0) {
					count++; // 찾은 승객 수 증가
					
					// 승객 거리, 위치 비교
					if (dist > node[2] + 1) {
						id = map[nr][nc];
						r = nr;
						c = nc;
						dist = node[2] + 1;
					}
					else if (dist == node[2] + 1) {
						if (r > nr || (r == nr && c > nc)) {
							id = map[nr][nc];
							r = nr;
							c = nc;
							dist = node[2] + 1;
						}
					}
					
					// 모든 승객을 다 찾았다면
					if (count == alive) {
						q.clear();
						curId = id;
						map[r][c] = 0; // 빈칸 설정
						return dist; // 거리 반환
					}
				}

				visited[nr][nc] = true; // 방문 표시
				q.offer(new int[] { nr, nc, node[2] + 1 });
			}
		}

		return -1; // 승객 태우기 실패
	} // end of getStartDist()

	static int getEndDist() {
		q.offer(new int[] { taxiR, taxiC, 0 });

		// 방문 표시 배열 초기화
		for (int r = 1; r <= N; r++) {
			Arrays.fill(visited[r], false);
		}
		visited[taxiR][taxiC] = true; // 방문 표시

		while (!q.isEmpty()) {
			int[] node = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = node[0] + dr[d];
				int nc = node[1] + dc[d];

				// 범위를 벗어나거나 벽이거나 이미 방문했다면
				if (nr <= 0 || nr > N || nc <= 0 || nc > N || map[nr][nc] == -1 || visited[nr][nc])
					continue; // 넘어감

				// 목적지라면
				if (nr == passengers[curId].endR && nc == passengers[curId].endC) {
					q.clear();
					return node[2] + 1; // 거리 반환
				}

				visited[nr][nc] = true; // 방문 표시
				q.offer(new int[] { nr, nc, node[2] + 1 });
			}
		}

		return -1; // 목적지 이동 실패
	} // end of getEndDist()

	static class Passenger {
		int startR, startC, endR, endC;

		public Passenger(int startR, int startC, int endR, int endC) {
			this.startR = startR;
			this.startC = startC;
			this.endR = endR;
			this.endC = endC;
		} // end of Passenger()

	} // end of Passenger

} // end of Main
