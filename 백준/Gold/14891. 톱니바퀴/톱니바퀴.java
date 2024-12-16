import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int k;
	static LinkedList<LinkedList<Integer>> gearList;
	static int[][] rotation;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		gearList = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			LinkedList<Integer> gear = new LinkedList<>();

			String poles = br.readLine();
			for (int j = 0; j < 8; j++) {
				int digit = poles.charAt(j) - '0';
				gear.add(digit);
			}
			gearList.add(gear);
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
			LinkedList<Integer> gear = gearList.get(idx);

			leftGearRotate(gear, direction, idx);
			rightGearRotate(gear, direction, idx);

			rotate(gear, direction);
		}

		int score = 0;
		for (int i = 0; i < 4; i++) {
			// S극이면
			if (gearList.get(i).get(0) == 1) {
				score += Math.pow(2, i);
			}
		}

		return score;
	} // end of calcScore()

	static void leftGearRotate(LinkedList<Integer> gear, int direction, int idx) {
		// 제일 왼쪽 톱니바퀴라면
		if (idx == 0) {
			return;
		}

		LinkedList<Integer> leftGear = gearList.get(idx - 1);

		// 다른 극이면
		if (gear.get(6) != leftGear.get(2)) {
			direction = direction * -1;
			leftGearRotate(leftGear, direction, idx - 1);
			rotate(leftGear, direction);
		}
	} // end of leftGearRotate()

	static void rightGearRotate(LinkedList<Integer> gear, int direction, int idx) {
		// 제일 오른쪽 톱니바퀴라면
		if (idx == 3) {
			return;
		}

		LinkedList<Integer> rightGear = gearList.get(idx + 1);

		// 다른 극이면
		if (gear.get(2) != rightGear.get(6)) {
			direction = direction * -1;
			rightGearRotate(rightGear, direction, idx + 1);
			rotate(rightGear, direction);
		}
	} // end of rightGearRotate()

	static void rotate(LinkedList<Integer> gear, int direction) {
		// 시계 방향
		if (direction == 1) {
			int pole = gear.get(7);
			gear.removeLast();
			gear.addFirst(pole);
		}
		// 반시계 방향
		else {
			int pole = gear.get(0);
			gear.remove();
			gear.addLast(pole);
		}
	} // end of rotate()
} // end of Main