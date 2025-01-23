import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, shark;
	static int[][] map;
	static int[][] magics;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };

	static int[] beadCount = new int[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		shark = (N + 1) / 2;

		map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		magics = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 2; j++) {
				magics[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		run();

		System.out.println(beadCount[1] + beadCount[2] * 2 + beadCount[3] * 3);
	} // end of main()

	static void run() {
		for (int i = 0; i < M; i++) {
			blizzard(magics[i][0], magics[i][1]); // 마법 시전
			ArrayList<Integer> beadList = getBeadList(); // 구슬 담기
			// 남은 구슬이 없다면
			if (beadList.isEmpty())
				return; // 종료
			beadList = explode(beadList); // 구슬 폭발
			// 남은 구슬이 없다면
			if (beadList.isEmpty())
				return; // 종료
			beadList = change(beadList); // 구슬 변화
			move(beadList); // 구슬 이동
		}
	} // end of run()

	static void blizzard(int d, int s) {
		int[] dr = { 0, -1, 1, 0, 0 };
		int[] dc = { 0, 0, 0, -1, 1 };

		for (int i = 1; i <= s; i++) {
			int nr = shark + dr[d] * i;
			int nc = shark + dc[d] * i;
			map[nr][nc] = 0; // 구슬 파괴
		}
	} // end of destroy()

	static ArrayList<Integer> getBeadList() {
		int[] straightMoves = { 1, 1, 2, 2 }; // 직선 이동 횟수

		// 구슬 담기
		ArrayList<Integer> beadList = new ArrayList<>();
		int r = shark;
		int c = shark;
		while (true) {
			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < straightMoves[d]; i++) {
					r += dr[d];
					c += dc[d];

					// 구슬이 있다면
					if (map[r][c] != 0)
						beadList.add(map[r][c]);

					// (1, 1)까지 이동했다면
					if (r == 1 && c == 1)
						return beadList; // 멈춤
				}
			}

			// 직선 이동 횟수 증가
			for (int i = 0; i < 4; i++)
				straightMoves[i] += 2;
		}
	} // end of getBeadList()

	static ArrayList<Integer> explode(ArrayList<Integer> beadList) {
		boolean exploded = true;
		while (exploded) {
			// 남은 구슬이 없다면
			if (beadList.isEmpty())
				return beadList; // 종료
			
			exploded = false; // 폭발 여부 초기화

			// 구슬 폭발
			ArrayList<Integer> newBeadList = new ArrayList<>();
			int num = beadList.get(0);
			int count = 1;
			for (int i = 1; i < beadList.size(); i++) {
				// 이전 구슬과 다르다면
				if (num != beadList.get(i)) {
					// 폭발한다면
					if (count >= 4) {
						exploded = true; // 폭발 표시
						beadCount[num] += count; // 폭발 구슬 개수 증가
						// 새로운 구슬
						num = beadList.get(i);
						count = 1;
					}
					// 폭발하지 않는다면
					else {
						for (int j = i - count; j < i; j++)
							newBeadList.add(num); // 구슬 유지
						// 새로운 구슬
						num = beadList.get(i);
						count = 1;
					}
				}
				// 이전 구슬과 같다면
				else
					count++;
			}

			// 마지막 구슬이 폭발한다면
			if (count >= 4) {
				exploded = true; // 폭발 표시
				beadCount[num] += count; // 폭발 구슬 개수 증가
			}
			// 마지막 구슬이 폭발하지 않는다면
			else {
				for (int i = beadList.size() - count; i < beadList.size(); i++)
					newBeadList.add(num); // 구슬 유지
			}

			beadList = newBeadList;
		}

		return beadList;
	} // end of explode()

	static ArrayList<Integer> change(ArrayList<Integer> beadList) {
		// 구슬 변화
		ArrayList<Integer> newBeadList = new ArrayList<>();
		int num = beadList.get(0);
		int count = 1;
		for (int i = 1; i < beadList.size(); i++) {
			// 이전 구슬과 다르다면
			if (num != beadList.get(i)) {
				newBeadList.add(count);
				newBeadList.add(num);

				// 구슬이 칸의 수 이상이 되었다면
				if (newBeadList.size() >= N * N - 1)
					return newBeadList; // 멈춤

				num = beadList.get(i);
				count = 1;
			}
			// 이전 구슬과 같다면
			else
				count++;
		}

		// 마지막 구슬 변화
		newBeadList.add(count);
		newBeadList.add(num);

		return newBeadList;
	} // end of change()

	static void move(ArrayList<Integer> beadList) {
		int[] straightMoves = { 1, 1, 2, 2 }; // 직선 이동 횟수

		// 구슬 이동
		int[][] newMap = new int[N + 1][N + 1];
		int r = shark;
		int c = shark;
		while (true) {
			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < straightMoves[d]; i++) {
					r += dr[d];
					c += dc[d];
					newMap[r][c] = beadList.remove(0);

					// 더 이상 구슬이 없거나 (1, 1)까지 이동했다면
					if (beadList.isEmpty() || (r == 1 && c == 1)) {
						map = newMap;
						return; // 멈춤
					}
				}
			}

			// 직선 이동 횟수 증가
			for (int i = 0; i < 4; i++)
				straightMoves[i] += 2;
		}
	} // end of move()

} // end of Main
