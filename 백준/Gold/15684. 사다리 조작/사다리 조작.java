import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	static boolean[][] ladder;

	static int ans = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new boolean[H + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			ladder[a][b] = true;
		}

		// 사다리 추가 없이 모든 i번 세로선의 결과가 i번이면
		if (getResult()) {
			System.out.println(0);
			return;
		}

		// 1~3개의 가로선을 추가
		for (int i = 1; i <= 3; i++) {
			if (dfs(0, i)) {
				System.out.println(i);
				return;
			}
		}

		// 3개의 가로선을 추가해도 불가능하면 -1 출력
		System.out.println(-1);
	} // end of Main()

	static boolean dfs(int count, int target) {
		if (count == target) {
			return getResult();
		}

		for (int r = 1; r <= H; r++) {
			for (int c = 1; c < N; c++) {
				// 기존에 있던 가로선이거나, 이미 선택한 가로선이거나, 옆에 가로선이 있다면
				if (ladder[r][c] || (c > 1 && ladder[r][c - 1]) || ladder[r][c + 1])
					continue;

				// 가로선 추가
				ladder[r][c] = true;
				if (dfs(count + 1, target))
					return true;

				// 백트래킹
				ladder[r][c] = false;
			}
		}

		return false;
	} // end of dfs()

	static boolean getResult() {
		for (int c = 1; c <= N; c++) {
			if (!gameStart(c))
				return false;
		}
		return true;
	} // end of getResul()

	static boolean gameStart(int c) {
		int pos = c;
		for (int r = 1; r <= H; r++) {
			if (ladder[r][pos])
				pos++;
			else if (pos > 1 && ladder[r][pos - 1])
				pos--;
		}
		return pos == c;
	} // end of gameStart()

} // end of Main
