import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T, count;
	static List<Integer>[] disk;
	static int[][] tArr;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		count = N * M;

		disk = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			disk[i] = new ArrayList<>();
			disk[i].add(0);
			for (int j = 1; j <= M; j++) {
				disk[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		tArr = new int[T][3];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; j++) {
				tArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(getAns());
	} // end of Main()

	static int getAns() {
		for (int[] t : tArr) {
			// 번호가 x의 배수인 원판
			for (int x = t[0]; x <= N; x += t[0]) {
				rotate(x, t[1], t[2]); // 회전
			}
			
//			// 디버깅
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= M; j++) {
//					System.out.print(disk[i].get(j) + " ");
//				}
//				System.out.println();
//			}
//			System.out.println(count);
//			System.out.println();

			// 원판에 수가 남아 있으면
			if (count > 0)
				// 지워진 수가 없다면
				if (!remove()) // 인접하면서 같은 수 지우기
					setDisc();
			
//			// 디버깅
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= M; j++) {
//					System.out.print(disk[i].get(j) + " ");
//				}
//				System.out.println();
//			}
//			System.out.println(count);
//			System.out.println();

			// 남은 수가 없다면
			if (count == 0)
				return 0;
		}

		// 원판에 적힌 수의 합 구하기
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum += disk[i].get(j);
			}
		}
		return sum;
	}

	static void rotate(int x, int d, int k) {
		k %= M; // 불필요한 회전 방지
		
		// 시계방향
		if (d == 0) {
			for (int i = 0; i < k; i++) {
	            disk[x].add(1, disk[x].remove(M));
	        }
		}
		// 반시계 방향
		else {
			for (int i = 0; i < k; i++) {
	            disk[x].add(M, disk[x].remove(1));
	        }
		}
	} // end of rotate()

	static boolean remove() {
		int initCount = count;

		Queue<int[]> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 원판에 숫자가 없다면
				if (disk[i].get(j) == 0)
					continue; // 넘어감

				int num = disk[i].get(j);
				int curCount = count;

				q.offer(new int[] { i, j });

				while (!q.isEmpty()) {
					int[] node = q.poll();

					for (int d = 0; d < 4; d++) {
						int ni = node[0] + di[d];
						int nj = node[1] + dj[d];

						// 원판 개수 범위를 벗어나면
						if (ni < 1 || ni > N)
							continue; // 넘어감

						// nj 값 조정
						nj = nj < 1 ? M : nj;
						nj = nj > M ? 1 : nj;

						// 수가 같지 않다면
						if (disk[ni].get(nj) != num)
							continue; // 넘어감

						disk[ni].set(nj, 0); // 수 지우기
						count--; // 남아있는 수 감소
						q.offer(new int[] { ni, nj });
					}
				}
			}
		}

		// 지워진 수가 없다면
		if (initCount == count)
			return false;

		return true;
	} // end of remove()

	static void setDisc() {
		// 평균 구하기
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum += disk[i].get(j);
			}
		}

		// 수 조정
		double avg = (double) sum / count;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (disk[i].get(j) == 0)
					continue;
				
				if (disk[i].get(j) > avg)
					disk[i].set(j, disk[i].get(j) - 1);
				else if (disk[i].get(j) < avg)
					disk[i].set(j, disk[i].get(j) + 1);
			}
		}
	} // end of setDisc()

} // end of Main
