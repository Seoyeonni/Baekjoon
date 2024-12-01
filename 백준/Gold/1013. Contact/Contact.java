import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String vega = "(100+1+|01)+";
        
        int t = scanner.nextInt();
        
        for (int i = 0; i < t; i++) {
        	String spread = scanner.next();
        	if(spread.matches(vega)) {
        		System.out.println("YES");
        	}
        	else {
        		System.out.println("NO");
        	}
        }
    }
}