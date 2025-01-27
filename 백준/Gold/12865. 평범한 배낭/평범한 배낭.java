import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] stuffs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stuffs = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            stuffs[i][0] = Integer.parseInt(st.nextToken()); // 무게
            stuffs[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        // DP 배열 초기화
        int[] dp = new int[K + 1];

        // DP 점화식 계산
        for (int i = 1; i <= N; i++) {
            int w = stuffs[i][0]; // 현재 물건의 무게
            int v = stuffs[i][1]; // 현재 물건의 가치
            for (int j = K; j >= w; j--) { // 역순으로 계산
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        System.out.println(dp[K]);
    }
}
