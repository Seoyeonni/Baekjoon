import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int L;
	static int R;
	static int[][] population;
	static boolean[][] visited;
	static int day = 0;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static LinkedList<int[]> association;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		population = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				population[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		run();

		System.out.println(day);
	} // end of Main()

	static void run() {
		visited = new boolean[N][N]; // 전체 연합국 초기화

		boolean isMove = false;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 아직 연합하지 않은 나라라면
				if (!visited[r][c]) {
					association = new LinkedList<>(); // 연합국 저장 배열 초기화
					association.add(new int[] { r, c });
					
					visited[r][c] = true;
					
					sum = 0;
					sum += population[r][c];
					
					associate(r, c);

					// 연합국이 존재한다면
					if (association.size() > 1) {
						move();
						isMove = true;
					}
				}
			}
		}
		
		// 인구 이동이 있었다면
		if (isMove) {
			day++;
			run();
		}
		// 인구 이동이 없었다면
		else {
			return;
		}
	} // end of run()

	static void associate(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				int difference = Math.abs(population[r][c] - population[nr][nc]);

				if (difference >= L && difference <= R && !visited[nr][nc]) {
					association.add(new int[] { nr, nc });
					visited[nr][nc] = true;
					sum += population[nr][nc];
					associate(nr, nc);
				}
			}
		}
	} // end of associate()

	static void move() {
		int num = sum / association.size();

		for (int[] country : association) {
			population[country[0]][country[1]] = num;
		}
	} // end of move()

} // end of Main
