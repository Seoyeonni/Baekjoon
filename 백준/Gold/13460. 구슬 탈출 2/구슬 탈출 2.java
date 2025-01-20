import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] board;
	static int rx, ry, bx, by, ox, oy;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			
			for (int c = 0; c < M; c++) {
				board[r][c] = line.charAt(c);
				
				if (board[r][c] == 'R') {
					rx = r;
					ry = c;
					board[r][c] = '.';
				}
				else if (board[r][c] == 'B') {
					bx = r;
					by = c;
					board[r][c] = '.';
				}
				else if (board[r][c] == 'O') {
					ox = r;
					oy = c;
				}
			}
		}
		
		move(rx, ry, bx, by, 1);
		
		System.out.println(min > 10 ? -1 : min);
	} // end of main()
	
	static void move(int crx, int cry, int cbx, int cby, int count) {
		// 10번 넘게 움직였다면
		if (count > 10)
			return; // 실패
		
		for (int d = 0; d < 4; d++) {
			int nrx = crx + dx[d];
			int nry = cry + dy[d];
			int nbx = cbx + dx[d];
			int nby = cby + dy[d];
			
			boolean rmove = canMove(nrx, nry);
			boolean bmove = canMove(nbx, nby);
			// 모든 구슬이 움직일 수 없다면
			if (!rmove && !bmove)
				continue; // 넘어감
			
			// 빨간 구슬 중력 작용
			if (rmove) {
				while (board[nrx + dx[d]][nry + dy[d]] != '#') {
					nrx = nrx + dx[d];
					nry = nry + dy[d];
				}
			}
			// 파란 구슬 중력 작용
			if (bmove) {
				while (board[nbx + dx[d]][nby + dy[d]] != '#') {
					nbx = nbx + dx[d];
					nby = nby + dy[d];
				}
			}
			
			// 빨간 구슬이 움직이려는 곳에 파란 구슬이 있다면
			if (!bmove && nrx == cbx && nry == cby) {
				// 전칸까지만 움직임
				nrx = nrx - dx[d];
				nry = nry - dy[d];
				
				// 원위치라면
				if (nrx == crx && nry == cry)
					continue; // 넘어감
			}
			// 파란 구슬이 움직이려는 곳에 빨간 구슬이 있다면
			if (!rmove && nbx == crx && nby == cry) {
				// 전칸까지만 움직임
				nbx = nbx - dx[d];
				nby = nby - dy[d];
				
				// 원위치라면
				if (nbx == cbx && nby == cby)
					continue; // 넘어감
			}
			// 모든 구슬이 같은 칸으로 움직인다면
			if (rmove && bmove && nrx == nbx && nry == nby) {
				// 파란 구슬이 구멍에 빠진다면
				if (goal(d, cbx, cby, nbx, nby))
					continue; // 넘어감
				
				if ((d == 0 && crx < cbx) || (d == 1 && cry > cby) ||
						(d == 2 && crx > cbx) || (d == 3 && cry < cby)) {
					// 전칸까지만 움직임
					nbx = nbx - dx[d];
					nby = nby - dy[d];
				}
				else {
					// 전칸까지만 움직임
					nrx = nrx - dx[d];
					nry = nry - dy[d];
				}
			}
			
			if (bmove) {
				// 파란 구슬이 구멍에 빠진다면
				if (goal(d, cbx, cby, nbx, nby))
					continue; // 넘어감
			}
			
			if (rmove) {
				// 빨간 구슬이 구멍에 빠진다면
				if (goal(d, crx, cry, nrx, nry)) {
					min = Math.min(min, count);
					return; // 성공
				}
			}
			
			// 빨간 구슬이 움직이지 않았다면
			if (!rmove)
				move(crx, cry, nbx, nby, count + 1);
			// 파란 구슬이 움직이지 않았다면
			else if (!bmove)
				move(nrx, nry, cbx, cby, count + 1);
			// 모든 구슬이 움직였다면
			else
				move(nrx, nry, nbx, nby, count + 1);
		}
	} // end of move()
	
	static boolean goal(int d, int cx, int cy, int nx, int ny) {
		if (d == 0 && ny == oy && nx <= ox && ox < cx)
			return true;
		else if (d == 1 && nx == ox && cy < oy && oy <= ny)
			return true;
		else if (d == 2 && ny == oy && cx < ox && ox <= nx)
			return true;
		else if (d == 3 && nx == ox && ny <= oy && oy < cy)
			return true;
		return false;
	} // end of goal()
	
	static boolean canMove(int x, int y) {
		// 이동하려는 칸이 벽이라면
		if (x <= 0 || x >= N - 1 || y <= 0 || y >= M - 1 || board[x][y] == '#')
			return false;
		return true;
	} // end of canMove()

} // end of Main
