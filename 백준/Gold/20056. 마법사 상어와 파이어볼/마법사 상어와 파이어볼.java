import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static LinkedList<Fireball> fireballList = new LinkedList<>();
    static LinkedList<Fireball>[][] grid;
    static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Fireball fireball = new Fireball(r, c, m, s, d);

            fireballList.add(fireball);
        }
        
        grid = new LinkedList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                grid[i][j] = new LinkedList<>();
            }
        }

        run();
    } // end of Main()

    static void run() {
        while (K-- > 0) {
            moveFireball(); // 파이어볼 이동
            afterMove(); // 이동 후 변화
        }

        printMSum();
    } // end of run()

    static void moveFireball() {
        for (Fireball fireball : fireballList) {
            // 이동 후 위치 저장
        	fireball.r = (fireball.r + dr[fireball.d] * (fireball.s % N) + N - 1) % N + 1;
        	fireball.c = (fireball.c + dc[fireball.d] * (fireball.s % N) + N - 1) % N + 1;
        }
    } // end of moveFireball()

    static void afterMove() {
        // 그리드 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
            	if (grid[i][j].size() > 0)
            		grid[i][j] = new LinkedList<>();
            }
        }

        // 모든 파이어볼을 그리드에 배치
        for (Fireball fireball : fireballList) {
            grid[fireball.r][fireball.c].add(fireball);
        }

        LinkedList<Fireball> newFireballList = new LinkedList<>();

        // 각 칸을 확인하며 처리
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (grid[r][c].isEmpty()) continue;

                // 현재 칸에 있는 파이어볼 리스트
                LinkedList<Fireball> cellFireballs = grid[r][c];

                // 파이어볼이 1개라면 그대로 유지
                if (cellFireballs.size() == 1) {
                    newFireballList.add(cellFireballs.getFirst());
                    continue;
                }

                // 파이어볼 합치기
                int totalM = 0, totalS = 0, count = cellFireballs.size();
                boolean allEven = true, allOdd = true;

                for (Fireball fireball : cellFireballs) {
                    totalM += fireball.m;
                    totalS += fireball.s;

                    if (fireball.d % 2 == 0) allOdd = false;
                    else allEven = false;
                }

                // 나누어진 파이어볼의 질량
                int newM = totalM / 5;
                if (newM == 0) continue; // 질량이 0이면 소멸

                // 나누어진 파이어볼의 속력
                int newS = totalS / count;

                // 방향 설정
                int[] newDirs = allEven || allOdd ? new int[] {0, 2, 4, 6} : new int[] {1, 3, 5, 7};

                // 새로운 파이어볼 추가
                for (int d : newDirs) {
                    newFireballList.add(new Fireball(r, c, newM, newS, d));
                }
            }
        }

        fireballList = newFireballList;
    } // end of afterMove()

    static void printMSum() {
        int sum = 0;

        for (Fireball fireball : fireballList) {
            sum += fireball.m;
        }

        System.out.println(sum);
    } // end of printMSum()

    static class Fireball {
        int r, c, m, s, d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        } // end of Fireball()
    } // end of Fireball
} // end of Main
