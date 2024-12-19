import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer> students;
	static Map<Integer, List<Integer>> likes;
	static int[][] classroom;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		students = new ArrayList<>();
		likes = new HashMap<>();
		for (int i = 1; i <= N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			students.add(student);

			List<Integer> likeList = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				likeList.add(Integer.parseInt(st.nextToken()));
			}
			likes.put(student, likeList);
		}

		classroom = new int[N + 1][N + 1];

		placeStudent();

		System.out.println(calcSatisfaction());
	} // end of Main()

	static void placeStudent() {
		for (int student : students) {
			int likeCount = Integer.MIN_VALUE;
			int emptyCount = Integer.MIN_VALUE;
			int bestR = Integer.MAX_VALUE;
			int bestC = Integer.MAX_VALUE;

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					// 이미 학생이 있는 칸이라면
					if (classroom[r][c] != 0)
						continue;

					int[] counts = calcCounts(student, r, c);

					// 자리 선정 우선순위 체크
					if (likeCount < counts[0] || (likeCount == counts[0] && emptyCount < counts[1])
							|| (likeCount == counts[0] && emptyCount == counts[1]
									&& (bestR > r || (bestR == r && bestC > c)))) {
						likeCount = counts[0];
						emptyCount = counts[1];
						bestR = r;
						bestC = c;
					}
				}
			}

			// 자리 배치
			classroom[bestR][bestC] = student;
		}
	} // end of placeStudent()

	static int[] calcCounts(int student, int r, int c) {
		int[] counts = new int[2];
		int likeCount = 0;
		int emptyCount = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
				if (likes.get(student).contains(classroom[nr][nc]))
					likeCount++;
				if (classroom[nr][nc] == 0)
					emptyCount++;
			}
		}

		counts[0] = likeCount;
		counts[1] = emptyCount;

		return counts;
	} // end of calcCounts()

	static int calcSatisfaction() {
		int satisfaction = 0;

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (classroom[r][c] == 0)
					continue;
				int likeCount = calcCounts(classroom[r][c], r, c)[0];
				if (likeCount > 0) {
					satisfaction += Math.pow(10, likeCount - 1);
				}
			}
		}

		return satisfaction;
	} // end of calcSatisfaction()

} // end of Main
