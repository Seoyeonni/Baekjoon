import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[] shirts = new int[6];

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<6; i++){
            shirts[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int answerT = 0;
        for(int s: shirts) {
            int tmp = s/T;
            if(s%T != 0) {
                tmp += 1;
            }
            answerT += tmp;
        }
        sb.append(answerT).append('\n');

        int answerP1 = N/P;
        int answerP2 = N%P;

        sb.append(answerP1).append(' ').append(answerP2).append('\n');
        System.out.println(sb);
    }
}