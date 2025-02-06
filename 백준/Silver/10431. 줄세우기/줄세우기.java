import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		P = Integer.parseInt(br.readLine());

		while (P-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			bw.write(st.nextToken() + " ");

			List<Integer> list = new ArrayList<>();
			list.add(Integer.parseInt(st.nextToken()));

			int count = 0;
			for (int i = 1; i < 20; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				int j;
				for (j = list.size() - 1; j >= 0; j--) {
					if (list.get(j) < num) {
						break;
					}
					count++;
				}
				
				list.add(j + 1, num);
			}

			bw.write(count + "\n");
		}

		bw.flush();
	} // end of main()
} // end of Main
