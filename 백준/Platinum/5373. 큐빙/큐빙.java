import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int[] ns;
	static List<String>[] methodList;

	static char[][][] cube = new char[6][3][3];
	static char[] colors = new char[] { 'w', 'y', 'r', 'o', 'g', 'b' };
	static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		ns = new int[T];
		methodList = new ArrayList[T];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			ns[i] = Integer.parseInt(st.nextToken());

			methodList[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < ns[i]; j++)
				methodList[i].add(st.nextToken());
		}

		for (int i = 0; i < T; i++)
			run(i);
	} // end of main()

	static void run(int idx) {
		initCube(); // 큐브 초기화

		// 큐브 돌리기
		for (int i = 0; i < ns[idx]; i++) {
			String method = methodList[idx].get(i);
			chooseSide(method.charAt(0), method.charAt(1));
		}

		printUpside(); // 출력
	} // end of run()

	static void chooseSide(char s, char dir) {
		// 선택된 면이 앞면
		switch (s) {
		case 'U':
			turnCube(U, L, F, R, B, dir);
			break;
		case 'D':
			turnCube(D, B, R, F, L, dir);
			break;
		case 'F':
			turnCube(F, U, L, D, R, dir);
			break;
		case 'B':
			turnCube(B, R, D, L, U, dir);
			break;
		case 'L':
			turnCube(L, F, U, B, D, dir);
			break;
		case 'R':
			turnCube(R, D, B, U, F, dir);
			break;
		}
	} // end of turnCube()

	private static void turnCube(int f, int u, int l, int d, int r, char dir) {
		char[][] temp1 = new char[3][3];
		char[] temp2 = new char[3];

		// 시계 방향
		if (dir == '+') {
			// 앞면 회전
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; ++j) {
					temp1[j][2 - i] = cube[f][i][j];
				}
			// 옆면 회전
			for (int i = 0; i < 3; i++)
				temp2[i] = cube[u][i][0];
			for (int i = 0; i < 3; i++)
				cube[u][i][0] = cube[l][0][2 - i];
			for (int i = 0; i < 3; i++)
				cube[l][0][2 - i] = cube[d][2][i];
			for (int i = 0; i < 3; i++)
				cube[d][2][i] = cube[r][2 - i][2];
			for (int i = 0; i < 3; i++)
				cube[r][2 - i][2] = temp2[i];
		}
		// 반시계 방향
		else {
			// 앞면 회전
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; ++j) {
					temp1[2- j][i] = cube[f][i][j];
				}
			// 옆면 회전
			for (int i = 0; i < 3; i++)
				temp2[i] = cube[u][i][0];
			for (int i = 0; i < 3; i++)
				cube[u][i][0] = cube[r][2 - i][2];
			for (int i = 0; i < 3; i++)
				cube[r][2 - i][2] = cube[d][2][i];
			for (int i = 0; i < 3; i++)
				cube[d][2][i] = cube[l][0][2 - i];
			for (int i = 0; i < 3; i++)
				cube[l][0][2 - i] = temp2[i];
		}

		cube[f] = temp1;
	} // end of turnCube()

	static void initCube() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++)
					cube[i][j][k] = colors[i];
			}
		}
	} // end of initCube()

	static void printUpside() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(cube[U][j][2 - i]);
			System.out.println();
		}
	} // end of printUpside()

} // end of Main
