import java.util.Scanner;

class Solution {
    public String solution(String X, String Y) {
        int[] xArr = {0,0,0,0,0,0,0,0,0,0};
        int[] yArr = {0,0,0,0,0,0,0,0,0,0};
        int[] answerArr = {0,0,0,0,0,0,0,0,0,0};
        
        // char값에서 48을 빼면 int값으로 변환
        for(int i = 0; i < X.length(); i++) 
            xArr[X.charAt(i)-'0']+= 1;
        for(int i = 0; i < Y.length(); i++)
            yArr[Y.charAt(i)-'0']+= 1;
        
        for(int i = 0; i < 10; i++) {
            answerArr[i] = Math.min(xArr[i], yArr[i]);
        }
        
        StringBuilder answer = new StringBuilder();
        
        boolean hasResult = false;
        for (int i = 1; i < 10; i++) {
            if (answerArr[i] != 0) {
                hasResult = true;
                break;
            }
        }
        if (!hasResult) {
            if (answerArr[0] > 0) {
                return "0"; // 공통으로 0만 있는 경우
            } else {
                return "-1"; // 공통된 숫자가 없는 경우
            }
        }
        
        for(int i = 9; i >= 0; i--) {
            while(answerArr[i] != 0) {
                answer.append(i);
                answerArr[i]--;
            }
        }
        
        return answer.toString();
    }
}