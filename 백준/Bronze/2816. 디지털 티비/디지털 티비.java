import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int N, idx1, idx2;
	static int[] channels;
	static ArrayList<Integer> ansList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		channels = new int[N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (str.equals("KBS1")) {
				channels[i] = 1;
				idx1 = i;
			} else if (str.equals("KBS2")) {
				channels[i] = 2;
				idx2 = i;
			}
		}

		run();
	} // end of main()

	static void run() {
		int arrow = 0;

		// KBS1 채널까지 화살표 이동
		while (arrow < idx1) {
			arrow++;
			ansList.add(1);
		}

		// KBS1 채널을 첫 번째로
		while (idx1 > 0) {
			if (channels[idx1 - 1] == 2)
				idx2 = idx1;
			channels[idx1] = channels[idx1 - 1];
			channels[idx1 - 1] = 1;
			idx1--;
			ansList.add(4);
		}

		arrow = 0;
		// KBS2 채널까지 화살표 이동
		while (arrow < idx2) {
			arrow++;
			ansList.add(1);
		}
		
		// KBS2 채널을 두 번째로
		while (idx2 > 1) {
			idx2--;
			ansList.add(4);
		}
		
		for (int n : ansList)
			System.out.print(n);
	}
} // end of Main
