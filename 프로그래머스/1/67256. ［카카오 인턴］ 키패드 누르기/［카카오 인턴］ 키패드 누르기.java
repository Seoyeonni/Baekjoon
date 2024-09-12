import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};
        int[] curL = {3, 0}; // '*'의 좌표 (3, 0)
        int[] curR = {3, 2}; // '#'의 좌표 (3, 2)
        
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            
            // 왼쪽 열 숫자 1, 4, 7
            if(n == 1 || n == 4 || n == 7) {
                answer += "L";
                curL[0] = (n - 1) / 3;
                curL[1] = 0;
            }
            // 오른쪽 열 숫자 3, 6, 9
            else if (n == 3 || n == 6 || n == 9) {
                answer += "R";
                curR[0] = (n - 1) / 3;
                curR[1] = 2;
            }
            // 가운데 숫자 2, 5, 8, 0
            else {
                int targetRow = n == 0 ? 3 : (n - 2) / 3;
                int targetCol = 1; // 2, 5, 8, 0은 항상 가운데 열 (열 인덱스 1)
                
                int distanceL = Math.abs(curL[0] - targetRow) + Math.abs(curL[1] - targetCol);
                int distanceR = Math.abs(curR[0] - targetRow) + Math.abs(curR[1] - targetCol);
                
                if(distanceL < distanceR) {
                    answer += "L";
                    curL[0] = targetRow;
                    curL[1] = targetCol;
                }
                else if(distanceL > distanceR) {
                    answer += "R";
                    curR[0] = targetRow;
                    curR[1] = targetCol;
                }
                else {
                    if (hand.equals("left"))  {
                        answer += "L";
                        curL[0] = targetRow;
                        curL[1] = targetCol;
                    }
                    else {
                        answer += "R";
                        curR[0] = targetRow;
                        curR[1] = targetCol;
                    }
                }
            }
        }
        
        return answer;
    }
}
