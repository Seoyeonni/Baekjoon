import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int i = 0; i < places.length; i++) {
            if (isSafe(places[i])) {
                answer[i] = 1; // 거리두기를 지켰다면 1
            } else {
                answer[i] = 0; // 지키지 않았다면 0
            }
        }
        return answer;
    }
    
    // 각 대기실에서 거리두기를 지키고 있는지 확인하는 메서드
    public boolean isSafe(String[] place) {
        // 5x5 크기의 대기실을 탐색
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (place[r].charAt(c) == 'P') {
                    // 사람(P) 발견 시 주변 확인
                    if (!checkDistance(place, r, c)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    // 특정 위치에서 거리두기를 지키고 있는지 확인
    public boolean checkDistance(String[] place, int r, int c) {
        // 상, 하, 좌, 우, 대각선 방향으로 거리 2 이하인 위치를 확인
        int[] dr = {-1, 1, 0, 0}; // 행 이동 (상, 하, 좌, 우)
        int[] dc = {0, 0, -1, 1}; // 열 이동 (상, 하, 좌, 우)
        
        // 4방향으로 1칸 떨어진 곳을 확인
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (isValid(nr, nc) && place[nr].charAt(nc) == 'P') {
                return false; // 바로 옆에 사람이 있으면 거리두기 실패
            }
            
            // 2칸 떨어진 곳을 확인
            int nnr = r + 2 * dr[i];
            int nnc = c + 2 * dc[i];
            
            // 같은 행 또는 같은 열에서 2칸 떨어진 경우, 그 사이가 빈 테이블이어야 함
            if (isValid(nnr, nnc) && place[nnr].charAt(nnc) == 'P' && place[nr].charAt(nc) != 'X') {
                return false; // 사이에 파티션이 없으면 거리두기 실패
            }
        }
        
        // 대각선 위치 확인 (4방향)
        int[] drDiag = {-1, -1, 1, 1}; // 대각선 행 이동
        int[] dcDiag = {-1, 1, -1, 1}; // 대각선 열 이동
        
        for (int i = 0; i < 4; i++) {
            int nr = r + drDiag[i];
            int nc = c + dcDiag[i];
            
            if (isValid(nr, nc) && place[nr].charAt(nc) == 'P') {
                // 대각선에 사람이 있을 경우, 파티션 확인
                if (!(place[r].charAt(nc) == 'X' && place[nr].charAt(c) == 'X')) {
                    return false; // 대각선에 파티션이 없으면 거리두기 실패
                }
            }
        }
        
        return true;
    }
    
    // 유효한 좌표인지 확인하는 메서드
    public boolean isValid(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}
