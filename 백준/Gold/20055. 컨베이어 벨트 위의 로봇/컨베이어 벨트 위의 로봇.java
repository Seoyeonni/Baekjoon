import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static ArrayList<int[]> conveyor;
	static int step = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		conveyor = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			conveyor.add(new int[] { Integer.parseInt(st.nextToken()), 0 });
		}

		run();

		System.out.println(step);

	} // end of Main()

	static void run() {
		while (true) {
			step++;

			// 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
			int[] temp = conveyor.get(conveyor.size() - 1);
			conveyor.remove(conveyor.size() - 1);
			conveyor.add(0, temp);

			// 내리는 위치에 로봇이 있다면 내리기
			if (conveyor.get(N - 1)[1] == 1) {
				conveyor.get(N - 1)[1] = 0;
			}

			// 가장 먼저 벨트에 올라간 로봇부터 이동
			for (int i = N - 2; i >= 0; i--) {
				int[] current = conveyor.get(i);
				int[] next = conveyor.get(i + 1);

				// 해당 칸에 로봇이 있고, 로봇을 이동할 수 있다면
				if (current[1] == 1 && next[0] > 0 && next[1] == 0) {
					current[1] = 0;
					next[0]--;

					// 다음 칸이 내리는 위치가 아니라면
					if (i + 1 != N - 1) {
						next[1] = 1;
					}
				}
			}

			// 올리는 위치에 로봇 올리기
			if (conveyor.get(0)[0] > 0) {
				conveyor.get(0)[0]--;
				conveyor.get(0)[1] = 1;
			}

			// 내구도가 0인 칸의 개수 세기
			int count = 0;
			for (int[] c : conveyor) {
				if (c[0] == 0) {
					count++;
				}
			}

			// 내구도가 0인 칸의 개수가 K개 이상이라면 종료
			if (count >= K)
				break;
		}
	} // end of run()
} // end of Main
