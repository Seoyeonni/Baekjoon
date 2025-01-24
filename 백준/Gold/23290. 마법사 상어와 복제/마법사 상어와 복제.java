import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int M, S;
	static int[][] smell = new int[5][5];
	static int[][] map = new int[5][5];
	static List<Fish> fishList = new LinkedList<>();
	static int sx, sy;
	static int[] sdx = { -1, 0, 1, 0 };
	static int[] sdy = { 0, -1, 0, 1 };
	static int[] fdx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] fdy = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static int count;
	static int[][] points;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			fishList.add(new Fish(x, y, d));
		}

		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());

		run();

		System.out.println(fishList.size());
	} // end of main()

	static void run() {
		while (S-- > 0) {
			// 복제 마법 시전
			List<Fish> copyFishList = new LinkedList<>();
			for (Fish fish : fishList)
				copyFishList.add(new Fish(fish));

			// 물고기 이동
			fishMove();

			// 상어 이동
			visited = new boolean[5][5];
			count = -1;
			points = new int[3][2];
			int[][] curPoints = new int[3][2];
			sharkMove(sx, sy, 0, 0, curPoints);
			sx = points[2][0];
			sy = points[2][1];

			// 물고기 제거
			removeFish();

			// 냄새 제거
			removeSmell();

			// 복제 마법 완료
			fishList.addAll(copyFishList);
		}
	} // end of run()

	static void fishMove() {
		map = new int[5][5]; // map 초기화

		for (Fish fish : fishList) {
			for (int i = 0; i < 8; i++) {
				int nd = (fish.d - i + 8) % 8; // 음수 처리
				int nx = fish.x + fdx[nd];
				int ny = fish.y + fdy[nd];

				// 범위를 벗어나거나 상어가 있거나 물고기의 냄새가 있다면
				if (nx <= 0 || nx > 4 || ny <= 0 || ny > 4 || (nx == sx && ny == sy) || smell[nx][ny] != 0)
					continue; // 넘어감

				// 물고기 이동
				fish.d = nd;
				fish.x = nx;
				fish.y = ny;
				break;
			}
			map[fish.x][fish.y]++;
		}
	} // end of move()

	static void sharkMove(int x, int y, int depth, int curCount, int[][] curPoints) {
		// 3칸 이동했다면
		if (depth > 2) {
			// 더 많은 물고기를 제거했다면
			if (count < curCount) {
				count = curCount;
				for (int i = 0; i < 3; i++)
					points[i] = Arrays.copyOf(curPoints[i], 2);
			}
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nx = x + sdx[d];
			int ny = y + sdy[d];

			// 범위를 벗어나거나 이미 방문했다면
			if (nx <= 0 || nx > 4 || ny <= 0 || ny > 4)
				continue; // 넘어감

			curPoints[depth][0] = nx;
			curPoints[depth][1] = ny;

			// 새로 방문하는 칸이라면
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				sharkMove(nx, ny, depth + 1, curCount + map[nx][ny], curPoints);
				visited[nx][ny] = false; // 백트랙킹
			}
			// 이미 방문한 칸이라면
			else
				sharkMove(nx, ny, depth + 1, curCount, curPoints);
		}
	} // end of sharkMove()

	static void removeFish() {
		LinkedList<Fish> newFishList = new LinkedList<>();
		for (Fish fish : fishList) {
			boolean removed = false;
			for (int i = 0; i < 3; i++) {
				int x = points[i][0];
				int y = points[i][1];
				if (fish.x == x && fish.y == y) {
					smell[x][y] = 3;
					removed = true;
					break;
				}
			}
			// 제거되지 않은 물고기라면
			if (!removed)
				newFishList.add(fish);
		}
		fishList = newFishList;
	} // end of removeFish()

	static void removeSmell() {
		for (int x = 1; x <= 4; x++) {
			for (int y = 1; y <= 4; y++) {
				if (smell[x][y] != 0)
					smell[x][y]--;
			}
		}
	} // end of removeSmell()

	static class Fish {
		int x, y, d;

		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		} // end of Fish()

		public Fish(Fish fish) {
			this.x = fish.x;
			this.y = fish.y;
			this.d = fish.d;
		} // end of Fish()

	} // end of Fish

} // end of Main
