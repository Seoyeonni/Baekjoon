import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] city;
	static ArrayList<int[]> chickenList = new ArrayList<>();
	static ArrayList<int[]> houseList = new ArrayList<>();
	static boolean[] selected;
	static int minDistance = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		city = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] == 1) {
					houseList.add(new int[] { i, j }); // 집 위치 저장
				} else if (city[i][j] == 2) {
					chickenList.add(new int[] { i, j }); // 치킨집 위치 저장
				}
			}
		}

		selected = new boolean[chickenList.size()];

		dfs(0, 0); // 조합 생성 시작

		System.out.println(minDistance); // 결과 출력
	} // end of Main()

	// M개의 치킨집을 선택하는 조합
	static void dfs(int start, int count) {
		if (count == M) {
			calcChickenDistance(); // 현재 조합으로 치킨 거리 계산
			return;
		}

		for (int i = start; i < chickenList.size(); i++) {
			selected[i] = true;
			dfs(i + 1, count + 1);
			selected[i] = false;
		}
	} // end of dfs()

	// 선택된 치킨집 조합으로 치킨 거리 계산
	static void calcChickenDistance() {
		int totalDistance = 0;

		for (int[] house : houseList) {
			int houseDistance = Integer.MAX_VALUE;

			for (int i = 0; i < chickenList.size(); i++) {
				if (selected[i]) { // 선택된 치킨집만 고려
					int[] chicken = chickenList.get(i);
					int distance = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
					houseDistance = Math.min(houseDistance, distance);
				}
			}
			totalDistance += houseDistance;
		}

		minDistance = Math.min(minDistance, totalDistance); // 최소 거리 갱신
	} // end of calcChickenDistance()
} // end of Main
