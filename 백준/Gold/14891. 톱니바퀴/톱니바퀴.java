import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k;
	static int[][] gears;
	static int[][] rotation;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		gears = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String poles = br.readLine();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = poles.charAt(j) - '0';
			}
		}

		k = Integer.parseInt(br.readLine());

		rotation = new int[k][2];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			rotation[i][0] = Integer.parseInt(st.nextToken()) - 1; // 톱니바퀴 0번부터 시작
			rotation[i][1] = Integer.parseInt(st.nextToken());
		}

		System.out.println(calcScore());
	} // end of main()

	static int calcScore() {
		for (int i = 0; i < k; i++) {
			int idx = rotation[i][0];
			int direction = rotation[i][1];

			leftGearRotate(idx, direction);
			rightGearRotate(idx, direction);

			rotate(gears[idx], direction);
		}

		int score = 0;
		for (int i = 0; i < 4; i++) {
			// S극이면
			if (gears[i][0] == 1) {
				score += Math.pow(2, i);
			}
		}

		return score;
	} // end of calcScore()

	static void leftGearRotate(int idx, int direction) {
		// 제일 왼쪽 톱니바퀴라면
		if (idx == 0) {
			return;
		}

		int[] leftGear = gears[idx - 1];

		// 다른 극이면
		if (gears[idx][6] != leftGear[2]) {
			leftGearRotate(idx - 1, direction * -1);
			rotate(leftGear, direction * -1);
		}
	} // end of leftGearRotate()

	static void rightGearRotate(int idx, int direction) {
		// 제일 오른쪽 톱니바퀴라면
		if (idx == 3) {
			return;
		}

		int[] rightGear = gears[idx + 1];

		// 다른 극이면
		if (gears[idx][2] != rightGear[6]) {
			rightGearRotate(idx + 1, direction * -1);
			rotate(rightGear, direction * -1);
		}
	} // end of rightGearRotate()

	static void rotate(int[] gear, int direction) {
		// 시계 방향
		if (direction == 1) {
			int last = gear[7];
			for (int i = 7; i > 0; i--) {
				gear[i] = gear[i - 1];
			}
			gear[0] = last;
		}
		// 반시계 방향
		else {
			int first = gear[0];
			for (int i = 0; i < 7; i++) {
				gear[i] = gear[i + 1];
			}
			gear[7] = first;
		}
	} // end of rotate()
} // end of Main