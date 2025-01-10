import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, Q;
	static int[][] A;
	static int ASize;
	static int[] L;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	
	static int sum = 0;
	static int maxCount = 0;
	static int count;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		ASize = (int) Math.pow(2, N);

		A = new int[ASize][ASize];
		for (int r = 0; r < ASize; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < ASize; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		L = new int[Q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}

		calcAns();
		
		System.out.println(sum);
		System.out.println(maxCount);
	} // end of Main()

	static void calcAns() {
		for (int l : L) {
			int size = (int) Math.pow(2, l);
			A = rotate(size);
			reduceIce();
		}
		
		calcSum();
		
		visited = new boolean[ASize][ASize];
		for (int r = 0; r < ASize; r++) {
			for (int c = 0; c < ASize; c++) {
				// 이미 방문했다면
				if (visited[r][c])
					continue; // 넘어감
				
				// 얼음이 없다면
				if (A[r][c] == 0) {
					visited[r][c] = true; // 방문 표시
					continue; // 넘어감
				}
				
				visited[r][c] = true; // 방문 표시
				count = 1;
				calcCount(r, c);
				maxCount = Math.max(maxCount, count);
			}
		}
	} // end of getAns()
	
	static void calcSum() {
		for (int r = 0; r < ASize; r++) {
			for (int c = 0; c < ASize; c++) {
				// 얼음이 없다면
				if (A[r][c] == 0)
					continue; // 넘어감
				
				sum += A[r][c];
			}
		}
	} // end of calcSum()
	
	static void calcCount(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위를 벗어나거나 이미 방문했다면
			if (nr < 0 || nr >= ASize || nc < 0 || nc >= ASize || visited[nr][nc])
				continue; // 넘어감
			
			// 얼음이 없다면
			if (A[nr][nc] == 0) {
				visited[nr][nc] = true; // 방문 표시
				continue; // 넘어감
			}
			
			visited[nr][nc] = true; // 방문 표시
			count++;
			calcCount(nr, nc);
		}
	} // end of calcCount()

	static int[][] rotate(int size) {
		int[][] map = new int[ASize][ASize];
		
		for (int r = 0; r < ASize; r += size) {
	        for (int c = 0; c < ASize; c += size) {
	            for (int i = 0; i < size; i++) {
	                for (int j = 0; j < size; j++) {
	                    map[r + j][c + size - 1 - i] = A[r + i][c + j];
	                }
	            }
	        }
	    }
		
		return map;
	} // end of rotate
	
	static void reduceIce() {
		visited = new boolean[ASize][ASize];
		
		for (int r = 0; r < ASize; r++) {
			for (int c = 0; c < ASize; c++) {
				// 얼음이 없다면
				if (A[r][c] == 0)
					continue; // 넘어감
				
				int count = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위를 벗어나면
					if (nr < 0 || nr >= ASize || nc < 0 || nc >= ASize)
						continue; // 넘어감
					
					// 얼음이 있다면
					if (A[nr][nc] != 0)
						count++; // 카운트
				}
				
				// 인접한 얼음이 있는 칸의 개수가 3개 미만이면
				if (count < 3)
					visited[r][c] = true;
			}
		}
		
		for (int r = 0; r < ASize; r++) {
			for (int c = 0; c < ASize; c++) {
				if (!visited[r][c])
					continue;
				
				A[r][c] = Math.max(A[r][c] - 1, 0); // 최솟값 0
			}
		}
	} // end of reduceIce()

} // end of Main
