import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] map;
	static LinkedList<Piece>[][] mapList;
	static LinkedList<Piece> pieces = new LinkedList<>();
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		mapList = new LinkedList[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				mapList[r][c] = new LinkedList<>();
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Piece piece = new Piece(r, c, d);
			
			pieces.add(piece);
			mapList[r][c].add(piece);
		}
		
		System.out.println(getAns());
	} // end of Main()
	
	static int getAns() {
		int ans = 0;
		
		while (ans <= 1000) {
			ans++;
			if (!round())
				break;
		}
		
		return ans > 1000 ? -1 : ans;
	}
	
	static boolean round() {
		for (Piece piece : pieces) {
			int nr = piece.r + dr[piece.d];
			int nc = piece.c + dc[piece.d];
			
			// 범위를 벗어나거나 파란색이면
			if (nr <= 0 || nr > N || nc <= 0 || nc > N || map[nr][nc] == 2) {
				// 방향 변경
				piece.d = (piece.d % 2 == 1) ? piece.d + 1 : piece.d - 1;
				
				nr = piece.r + dr[piece.d];
				nc = piece.c + dc[piece.d];
				
				// 범위를 벗어나거나 똑같이 파란색이면
				if (nr <= 0 || nr > N || nc <= 0 || nc > N || map[nr][nc] == 2)
					continue; // 넘어감
			}
			
			if (!move(piece, nr, nc, map[nr][nc]))
				return false;
		}
		
		return true;
	}
	
	static boolean move(Piece piece, int nr, int nc, int color) {
		// 기존 위치에서 꺼내기
		int i = 0;
		for (i = 0; i < mapList[piece.r][piece.c].size(); i++) {
			if (mapList[piece.r][piece.c].get(i) == piece)
				break;
		}
		
		LinkedList<Piece> curPieces = new LinkedList<>();
		
		// 없애보기
		int size = mapList[piece.r][piece.c].size();
		
		// j -> i 해보기
		for (int j = i; j < size; j++) {
			curPieces.add(mapList[piece.r][piece.c].get(j));
		}
		for (int j = size - 1; j >= i; j--) {
		    mapList[piece.r][piece.c].remove(j);
		}
		
		// 다음 위치로 옮기기(객체 위치 수정)
		for (Piece curPiece : curPieces) {
			curPiece.r = nr;
			curPiece.c = nc;
		}
		
		// 빨간색이면
		if (color == 1) {
			Collections.reverse(curPieces);
		}
		
		// 다음 위치로 옮기기(맵에서 이동)
		mapList[nr][nc].addAll(curPieces);
		
		if (mapList[nr][nc].size() >= 4)
			return false;
		
		return true;
	}
	
	static class Piece {
		int r, c, d;
		
		public Piece(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

} // end of Main
