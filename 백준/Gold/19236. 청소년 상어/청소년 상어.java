import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static Fish[] fishs = new Fish[17];
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[][] map = new int[4][4];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int i = 1;
		for (int r = 0; r < 4; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 4; c++) {
				int id = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				fishs[i++] = new Fish(id, r, c, d);
				map[r][c] = id;
			}
		}
		fishs[0] = new Fish(-1, -1, -1, -1);
		Arrays.sort(fishs);

		// (0, 0)에 있는 물고기를 먹고 맵에 들어감
		int d = fishs[map[0][0]].d;
		int sum = map[0][0];
		fishs[map[0][0]].alive = false;
		map[0][0] = -1;

		eatFish(0, 0, d, sum); // 물고기 먹기

		System.out.println(max);
	} // end of main()

	static void eatFish(int r, int c, int d, int sum) {
		max = Math.max(max, sum); // 점수 계산
		
		// 백업
		int[][] curMap = new int[4][4];
		for (int i = 0; i < 4; i++)
			curMap[i] = Arrays.copyOf(map[i], 4);
		
		Fish[] curFishs = new Fish[17];
		for (int i = 1; i <= 16; i++)
			curFishs[i] = new Fish(fishs[i]);
		
		moveFish(); // 물고기 이동

		// 상어 이동
		for (int step = 1; step < 4; step++) {
			int nr = r + dr[d] * step;
			int nc = c + dc[d] * step;

			// 이동할 수 없는 칸이라면
			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || map[nr][nc] == 0)
				continue; // 넘어감

			// 물고기 먹기
			int id = map[nr][nc];
			int nd = fishs[id].d;
			fishs[id].alive = false;
			map[r][c] = 0;
			map[nr][nc] = -1;

			eatFish(nr, nc, nd, sum + id); // 물고기 먹기

			// 백트랙킹
			fishs[id].alive = true;
			map[r][c] = -1;
			map[nr][nc] = id;
		}
		
		// 복구
		for (int i = 0; i < 4; i++)
			map[i] = Arrays.copyOf(curMap[i], 4);
		
		for (int i = 1; i <= 16; i++)
			fishs[i] = new Fish(curFishs[i]);
	} // end of eatFish()

	static void moveFish() {
		for (int idx = 1; idx <= 16; idx++) {
			Fish fish = fishs[idx];
			
			// 먹힌 물고기라면
			if (!fish.alive)
				continue; // 넘어감

			for (int i = 0; i < 8; i++) {
				int nr = fish.r + dr[(fish.d + i) % 8];
				int nc = fish.c + dc[(fish.d + i) % 8];

				// 이동할 수 없다면
				if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || map[nr][nc] == -1)
					continue; // 반시계 방향으로 45도 회전

				int temp = map[nr][nc];
				map[nr][nc] = map[fish.r][fish.c];
				map[fish.r][fish.c] = temp;

				// 물고기가 있는 칸이라면
				if (temp != 0) {
					// 위치 갱신
					fishs[temp].r = fish.r;
					fishs[temp].c = fish.c;
				}

				// 위치 갱신
				fish.r = nr;
				fish.c = nc;
				fish.d = (fish.d + i) % 8;

				break;
			}
		}
	} // end of moveFish()

	static class Fish implements Comparable<Fish> {
		int id, r, c, d;
		boolean alive;

		public Fish(int id, int r, int c, int d) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.d = d;
			this.alive = true;
		} // end of Fish()

		public Fish(Fish fish) {
			this.id = fish.id;
			this.r = fish.r;
			this.c = fish.c;
			this.d = fish.d;
			this.alive = fish.alive;
		} // end of Fish()

		@Override
		public int compareTo(Fish fish) {
			return this.id - fish.id;
		} // end of compareTo()
		
	} // end of Fish
	
} // end of Main
