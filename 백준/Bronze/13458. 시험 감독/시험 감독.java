import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	
    	StringTokenizer st;
    	
    	int[] aArr = new int[n];
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i++) {
    		aArr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        System.out.println(result(n, aArr, b, c));
    }
    
    private static long result(int n, int[] aArr, int b, int c) {
    	long sum = 0;
    	
    	for (int i = 0; i < n; i++) {
    		aArr[i] -= b;
    		sum += 1;
    		
    		if (aArr[i] > 0) {
    			if (aArr[i] % c == 0) {
    				sum += aArr[i] / c;
    			}
    			else {
    				sum += aArr[i] / c + 1;
    			}
    		}
    	}
    	
    	return sum;
    }
}