import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] A, map;
	static List<Integer>[][] treeList;
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][N + 1];
		map = new int[N + 1][N + 1];
		treeList = new List[N + 1][N + 1];
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				map[r][c] = 5;
				treeList[r][c] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			treeList[x][y].add(z);
		}

		System.out.println(getAns());
	} // end of Main()

	static int getAns() {
		while (K-- > 0) {
			springToSummer();
			fall();
			winter();
		}

		// 살아남은 나무 수 계산
		int count = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				count += treeList[r][c].size();
			}
		}
		
		return count;
	} // end of getAns()

	static void springToSummer() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				// 현재 칸에 나무가 없다면
				if (treeList[r][c].isEmpty())
					continue; // 넘어감
				
				// 생사여부에 따라 나무를 저장할 list 생성
				List<Integer> aliveTreeList = new ArrayList<>();
				List<Integer> deadTreeList = new ArrayList<>();
				
				// 나이가 어린 나무부터 양분을 먹기 위해 treeList 정렬
				Collections.sort(treeList[r][c]);
				
				for (int z : treeList[r][c]) {
					// 자신의 나이만큼 양분을 먹을 수 있다면
					if (map[r][c] >= z) {
						map[r][c] -= z; // 양분 먹기
						aliveTreeList.add(z + 1); // 나이 증가
					}
					// 양분을 먹을 수 없다면
					else
						deadTreeList.add(z);
				}
				
				// 생존한 나무만 treeList에 저장
				treeList[r][c] = aliveTreeList;
				
				// 여름이 되어 봄에 죽은 나무가 양분으로 변함
				for (int z : deadTreeList)
					map[r][c] += z / 2; // 양분 추가
			}
		}
	} // end of springToSummer()

	static void fall() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				for (int z : treeList[r][c]) {
					// 나이가 5의 배수가 아니라면
					if (z % 5 != 0)
						continue; // 넘어감
					
					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						// 땅을 벗어난다면
						if (nr <= 0 || nr > N || nc <= 0 || nc > N)
							continue; // 넘어감

						treeList[nr][nc].add(1); // 나이가 1인 나무 생성
					}
				}
			}
		}
	} // end of fall()

	static void winter() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] += A[r][c]; // 양분 추가
			}
		}
	} // end of winter()

} // end of Main
