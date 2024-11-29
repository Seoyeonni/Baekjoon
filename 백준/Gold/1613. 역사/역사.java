import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main (String[] args) throws IOException {
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = {};
        
        
        input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        
        
        boolean[][] event = new boolean[N+1][N+1];
        
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            event[a][b] = true;
        }
        
        
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (event[i][k] && event[k][j]) 
                        event[i][j] = true;
                }
            }
        }
        
        
        StringBuilder sb = new StringBuilder();
        
        int S = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < S; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            
            if (event[a][b])
                sb.append(-1 + " ");
            else if (event[b][a])
                sb.append(1 + " ");
            else
                sb.append(0);
            
            sb.append("\n");
        }
        
        
        System.out.println(sb);
        
        
        br.close();
    }
}