import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int r;
	static int c;
	static int k;
	static int row = 3;
	static int col = 3;
	static int[][] A = new int[101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= row; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= col; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(run());
	} // end of Main()

	static int run() {
		int time = 0;

		while (time <= 100) {
			if (A[r][c] == k)
				return time;

			// R 연산
			if (row >= col) {
				RCalc();
			}
			// C 연산
			else {
				CCalc();
			}
			
			time++;
		}

		return -1;
	} // end of run()

	static void RCalc() {
		int[][] newA = new int[101][101];
		int newCol = 0;

		for (int i = 1; i <= row; i++) {
			Map<Integer, Integer> map = new HashMap<>();

			for (int j = 1; j <= col; j++) {
				// 0은 무시
				if (A[i][j] == 0)
					continue;

				// 키가 존재하지 않으면 기본값 0, 키가 존재하면 +1
				map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
			}

			LinkedList<Node> list = sortMap(map);

			newCol = Math.max(newCol, list.size() * 2);

			int idx = 1;
			for (Node node : list) {
				// 배열 크기 제한
				if (idx > 100)
					break;
				newA[i][idx++] = node.num;
				newA[i][idx++] = node.count;
			}
		}

		// 배열 복사
		col = newCol;
		A = newA;
	} // end of RCalc()

	static void CCalc() {
		int[][] newA = new int[101][101];
		int newRow = 0;

		for (int j = 1; j <= col; j++) {
			Map<Integer, Integer> map = new HashMap<>();

			for (int i = 1; i <= row; i++) {
				// 0은 무시
				if (A[i][j] == 0)
					continue;

				// 키가 존재하지 않으면 기본값 0, 키가 존재하면 +1
				map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
			}

			LinkedList<Node> list = sortMap(map);

			newRow = Math.max(newRow, list.size() * 2);

			int idx = 1;
			for (Node node : list) {
				// 배열 크기 제한
				if (idx > 100)
					break;
				newA[idx++][j] = node.num;
				newA[idx++][j] = node.count;
			}
		}

		// 배열 복사
		row = newRow;
		A = newA;
	} // end of CCalc()

	static LinkedList<Node> sortMap(Map<Integer, Integer> map) {
		LinkedList<Node> list = new LinkedList<>();

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			list.add(new Node(entry.getKey(), entry.getValue()));
		}

		Collections.sort(list);

		return list;
	} // end of sortMap()

	static class Node implements Comparable<Node> {
		int num;
		int count;

		public Node(int num, int count) {
			this.num = num;
			this.count = count;
		}

		@Override
		public int compareTo(Node node) {
			// this가 node 보다 작으면 음의 정수, 같으면 0, 크면 양의 정수 반환
			if (this.count == node.count) {
				return this.num - node.num;
			}
			return this.count - node.count;
		}

	} // end of Node

} // end of Main
