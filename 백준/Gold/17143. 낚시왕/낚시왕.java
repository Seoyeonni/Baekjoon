import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int R, C, M;
	static Shark[] sharks;
	static int[][] map;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sharks = new Shark[M + 1];
		map = new int[R + 1][C + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks[i] = new Shark(r, c, s, d, z);
			map[r][c] = i;
		}
		
		System.out.println(getAns());
	} // end of main()
	
	static int getAns() {
		int sum = 0;
		for (int king = 1; king <= C; king++) {
			// 상어 잡기
			for (int r = 1; r <= R; r++) {
				if (map[r][king] != 0) {
					int id = map[r][king];
					sharks[id].alive = false;
					sum += sharks[id].z;
					break;
				}
			}
			
			move(); // 상어 이동
		}
		
		return sum;
	} // end of getAns()
	
	static void move() {
		int[][] newMap = new int[R + 1][C + 1];
		
		for (int id = 1; id <= M; id++) {
			Shark shark = sharks[id];
			// 죽은 상어라면
			if (!shark.alive)
				continue; // 넘어감
			
			// 속력 조절
			int s = shark.s;
			if (shark.d == 1 || shark.d == 2)
				s %= (R - 1) * 2;
			else
				s %= (C - 1) * 2;
			
			int nr = shark.r;
			int nc = shark.c;
			for (int i = 0; i < s; i++) {
				nr = nr + dr[shark.d];
				nc = nc + dc[shark.d];
				
				// 범위를 벗어나면
				if (nr <= 0 || nr > R || nc <= 0 || nc > C) {
					// 방향 반대로 바꾸기
					if (shark.d == 1 || shark.d == 3)
						shark.d++;
					else
						shark.d--;
					
					nr = nr + dr[shark.d] * 2;
					nc = nc + dc[shark.d] * 2;
				}
			}
			
			// 다른 물고기가 있다면
			if (newMap[nr][nc] != 0) {
				// 현재 상어가 더 크다면
				if (shark.z > sharks[newMap[nr][nc]].z) {
					sharks[newMap[nr][nc]].alive = false;
					newMap[nr][nc] = id;
					shark.r = nr;
					shark.c = nc;
				}
				// 현재 상어가 더 작다면
				else
					shark.alive = false;
			}
			// 다른 물고기가 없다면
			else {
				newMap[nr][nc] = id;
				shark.r = nr;
				shark.c = nc;
			}
		}
		
		map = newMap;
	} // end of move()

	static class Shark {
		int r, c, s, d, z;
		boolean alive;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			alive = true;
		} // end of Shark()
		
	} // end of Shark

} // end of Main
