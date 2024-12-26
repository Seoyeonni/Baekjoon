import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static int T;
	static int[][] A;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] airPurifier = new int[2];
	static int[][] nextA;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int i = 0;

		A = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= C; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());

				if (A[r][c] == -1) {
					airPurifier[i] = r;
					i++;
				}
			}
		}
		
		System.out.println(run());
	} // end of Main()

	static int run() {
		for (int i = 0; i < T; i++) {
			// 미세먼지 확산 후 상태를 저장할 배열 선언
			nextA = new int[R + 1][C + 1];
			nextA[airPurifier[0]][1] = -1;
			nextA[airPurifier[1]][1] = -1;

			// 미세먼지 확산
			for (int r = 1; r <= R; r++) {
				for (int c = 1; c <= C; c++) {
					// 미세먼지가 있다면
					if (A[r][c] > 0) {
						spread(r, c);
					}
				}
			}
			
			A = nextA;

			// 공기청정기 작동
			operation();
		}
		
		int sum = 0;
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (A[r][c] > 0) {
					sum += A[r][c];
				}
			}
		}
		
		return sum;
	} // end of run()

	static void spread(int r, int c) {
		nextA[r][c] += A[r][c];

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 인접한 방향에 공기청정기가 없고 칸이 있다면
			if (nr > 0 && nr <= R && nc > 0 && nc <= C && nextA[nr][nc] != -1) {
				nextA[nr][nc] += A[r][c] / 5;
				nextA[r][c] -= A[r][c] / 5;
			}
		}
	} // end of fineDustSpread()
	
	static void operation() {
		// 위쪽 공기청정기 바람
		for (int r = airPurifier[0] - 1; r > 1; r--) {
			A[r][1] = A[r - 1][1];
		}
		
		for (int c = 1; c < C; c++) {
			A[1][c] = A[1][c + 1];
		}
		
		for (int r = 1; r < airPurifier[0]; r++) {
			A[r][C] = A[r + 1][C];
		}
		
		for (int c = C; c > 2; c--) {
			A[airPurifier[0]][c] = A[airPurifier[0]][c - 1];
		}
		
		A[airPurifier[0]][2] = 0;
		
		// 아래쪽 공기청정기 바람
		for (int r = airPurifier[1] + 1; r < R; r++) {
			A[r][1] = A[r + 1][1];
		}
		
		for (int c = 1; c < C; c++) {
			A[R][c] = A[R][c + 1];
		}
		
		for (int r = R; r > airPurifier[1]; r--) {
			A[r][C] = A[r - 1][C];
		}
		
		for (int c = C; c > 2; c--) {
			A[airPurifier[1]][c] = A[airPurifier[1]][c - 1];
		}
		
		A[airPurifier[1]][2] = 0;
	} // end of operation()
} // end of Main
