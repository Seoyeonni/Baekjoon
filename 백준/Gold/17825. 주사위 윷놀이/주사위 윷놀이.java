import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int[] dice = new int[10];
	static Piece[] pieces = new Piece[4];
	static LinkedList<Node> map = new LinkedList<>();
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		// piece 세팅
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}

		makeMap();
		move(0, 0);

		System.out.println(max);
	} // end of Main()

	static void move(int sum, int d) {
		if (d == 10) {
			max = Math.max(sum, max);
			return;
		}

		for (int p = 0; p < 4; p++) {
			Node node = (pieces[p].blue == 0) ? map.get(pieces[p].idx)
					: map.get(pieces[p].blue).blueMap.get(pieces[p].idx);
			if (node.equals(map.getLast()))
				continue;

			int idx = pieces[p].idx;
			int nIdx = idx + dice[d];

			// 파란 칸에서 시작한다면
			if (pieces[p].blue == 0 && (idx == 5 || idx == 10 || idx == 15)) {
				nIdx = dice[d] - 1;

				// 도착한다면
				if (nIdx >= map.get(idx).blueMap.size() - 1) {
					pieces[p].idx = map.get(idx).blueMap.size() - 1;
					move(sum, d + 1);
					pieces[p].idx = idx;
				}
				// 도착하지 않는다면
				else {
					// 이동하려는 위치에 다른 말이 있다면
					if (!canMove(idx, nIdx))
						continue; // 넘어감

					pieces[p].blue = idx;
					pieces[p].idx = nIdx;
					sum += map.get(idx).blueMap.get(nIdx).num;
					move(sum, d + 1);
					sum -= map.get(idx).blueMap.get(nIdx).num;
					pieces[p].idx = idx;
					pieces[p].blue = 0;
				}
			}
			// 빨간 칸에서 시작한다면
			else {
				// 빨간 경로라면
				if (pieces[p].blue == 0) {
					// 도착한다면
					if (nIdx >= map.size() - 1) {
						pieces[p].idx = map.size() - 1;
						move(sum, d + 1);
						pieces[p].idx = idx;
					}
					// 도착하지 않는다면
					else {
						// 이동하려는 위치에 다른 말이 있다면
						if (!canMove(pieces[p].blue, nIdx))
							continue; // 넘어감

						pieces[p].idx = nIdx;
						sum += map.get(nIdx).num;
						move(sum, d + 1);
						sum -= map.get(nIdx).num;
						pieces[p].idx = idx;
					}
				}
				// 파란 경로라면
				else {
					// 도착한다면
					if (nIdx >= map.get(pieces[p].blue).blueMap.size() - 1) {
						pieces[p].idx = map.get(pieces[p].blue).blueMap.size() - 1;
						move(sum, d + 1);
						pieces[p].idx = idx;
					}
					// 도착하지 않는다면
					else {
						// 이동하려는 위치에 다른 말이 있다면
						if (!canMove(pieces[p].blue, nIdx))
							continue; // 넘어감

						pieces[p].idx = nIdx;
						sum += map.get(pieces[p].blue).blueMap.get(nIdx).num;
						move(sum, d + 1);
						sum -= map.get(pieces[p].blue).blueMap.get(nIdx).num;
						pieces[p].idx = idx;
					}
				}
			}
		}
	} // end of move()

	static boolean canMove(int blue, int idx) {
		Node target = (blue == 0) ? map.get(idx) : map.get(blue).blueMap.get(idx);

		for (int p = 0; p < 4; p++) {
			Node node = (pieces[p].blue == 0) ? map.get(pieces[p].idx)
					: map.get(pieces[p].blue).blueMap.get(pieces[p].idx);

			// 도착했다면
			if (node.equals(map.getLast()))
				continue; // 넘어감

			if (target.equals(node))
				return false;
		}

		return true;
	} // end of canMove()

	static void makeMap() {
		for (int n = 0; n <= 40; n += 2) {
			map.add(new Node(n));
		}
		map.add(new Node(-1));

		LinkedList<Node> endMap = new LinkedList<>();
		for (int n = 25; n <= 35; n += 5) {
			endMap.add(new Node(n));
		}
		endMap.add(map.get(map.size() - 2));
		endMap.add(map.getLast());

		LinkedList<Node> blueMap;

		blueMap = new LinkedList<>();
		for (int n = 13; n <= 19; n += 3) {
			blueMap.add(new Node(n));
		}
		map.get(5).blueMap.addAll(blueMap);
		map.get(5).blueMap.addAll(endMap);

		blueMap = new LinkedList<>();
		for (int n = 22; n <= 24; n += 2) {
			blueMap.add(new Node(n));
		}
		map.get(10).blueMap.addAll(blueMap);
		map.get(10).blueMap.addAll(endMap);

		blueMap = new LinkedList<>();
		for (int n = 28; n >= 26; n--) {
			blueMap.add(new Node(n));
		}
		map.get(15).blueMap.addAll(blueMap);
		map.get(15).blueMap.addAll(endMap);
	} // end of makeMap()

	static class Node {
		int num;
		LinkedList<Node> blueMap = new LinkedList<>();

		public Node(int num) {
			this.num = num;
		} // end of Node()

	} // end of Node

	static class Piece {
		int blue, idx;

		public Piece() {
			blue = 0;
			idx = 0;
		} // end of Piece()

	} // end of Piece

} // end of Main