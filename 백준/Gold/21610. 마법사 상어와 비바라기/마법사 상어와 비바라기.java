import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] A;
    static int[] d;
    static int[] s;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] cloud; // 0: 구름 X, 1: 구름 O, 2: 구름 사라짐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        d = new int[M];
        s = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            d[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 구름 위치 설정
        cloud = new int[N + 1][N + 1];
        cloud[N][1] = 1;
        cloud[N][2] = 1;
        cloud[N - 1][1] = 1;
        cloud[N - 1][2] = 1;

        run();

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum += A[i][j];
            }
        }

        System.out.println(sum);
    } // end of Main()

    static void run() {
        for (int k = 0; k < M; k++) {
            // 구름 이동
            int[][] newCloud = new int[N + 1][N + 1];
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (cloud[r][c] == 1) {
                        int nr = (r + dr[d[k]] * (s[k] % N) + N - 1) % N + 1;
                        int nc = (c + dc[d[k]] * (s[k] % N) + N - 1) % N + 1;
                        newCloud[nr][nc] = 1;
                    }
                }
            }
            cloud = newCloud;

            // 구름에서 비 내리기
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (cloud[r][c] == 1) {
                        A[r][c]++;
                        cloud[r][c] = 2; // 구름 사라짐
                    }
                }
            }

            // 물복사버그 마법 시전
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (cloud[r][c] == 2) {
                        for (int d = 2; d <= 8; d += 2) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && A[nr][nc] > 0) {
                            	A[r][c]++;
                            }
                        }
                    }
                }
            }

            // 새로운 구름 생성
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (A[r][c] >= 2 && cloud[r][c] != 2) {
                        A[r][c] -= 2;
                        cloud[r][c] = 1;
                    } else {
                        cloud[r][c] = 0;
                    }
                }
            }
        }
    } // end of run()
} // end of Main
