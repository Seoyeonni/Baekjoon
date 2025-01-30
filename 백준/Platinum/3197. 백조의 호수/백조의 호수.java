import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] visitedSwan;
    static int[][] swans = new int[2][2];
    static Queue<int[]> waterQueue = new LinkedList<>();
    static Queue<int[]> swanQueue = new LinkedList<>();
    static Queue<int[]> nextSwanQueue = new LinkedList<>();
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visitedSwan = new boolean[R][C];

        int swanIndex = 0;
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = str.charAt(c);
                if (map[r][c] == 'L') {
                    swans[swanIndex][0] = r;
                    swans[swanIndex][1] = c;
                    swanIndex++;
                    map[r][c] = '.'; // 백조 위치도 물로 취급
                }
                if (map[r][c] == '.') {
                    waterQueue.offer(new int[] { r, c });
                }
            }
        }

        System.out.println(getAns());
    } // end of main()

    static int getAns() {
        int days = 0;
        swanQueue.offer(new int[] { swans[0][0], swans[0][1] });
        visitedSwan[swans[0][0]][swans[0][1]] = true;

        while (true) {
            if (judge()) // 두 백조가 만나는지 판단
                return days;
            melt(); // 빙판 녹이기
            days++;
        }
    } // end of getAns()

    static void melt() {
        int size = waterQueue.size();
        for (int i = 0; i < size; i++) {
            int[] node = waterQueue.poll();
            int r = node[0], c = node[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 범위를 벗어난다면
                if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                    continue; // 넘어감

                if (map[nr][nc] == 'X') {
                    map[nr][nc] = '.'; // 얼음이면 녹이기
                    waterQueue.offer(new int[] { nr, nc }); // 다음 날 빙판을 녹일 수 있는 물
                }
            }
        }
    } // end of melt()

    static boolean judge() {
        while (!swanQueue.isEmpty()) {
            int[] node = swanQueue.poll();
            int r = node[0], c = node[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 범위를 벗어나거나 이미 방문한 칸이라면
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visitedSwan[nr][nc])
                    continue;

                visitedSwan[nr][nc] = true;

                if (nr == swans[1][0] && nc == swans[1][1])
                    return true; // 백조가 만남

                if (map[nr][nc] == '.') {
                    swanQueue.offer(new int[] { nr, nc });
                } else { // 빙판이면 다음날 탐색
                    nextSwanQueue.offer(new int[] { nr, nc });
                }
            }
        }

        // 하루가 지나면 다음 탐색 영역을 현재 큐로 변경
        Queue<int[]> temp = swanQueue;
        swanQueue = nextSwanQueue;
        nextSwanQueue = temp;

        return false;
    } // end of judge()
} // end of Main
