import java.io.*;
import java.util.*;
/*
 * 문제: 1600 말이 되고픈 원숭이
 * 난이도: Gold 3
 * 알고리즘: bfs
 * 풀이 방법: 최소한의 동작 -> 최단 경로 bfs 사용
			말처럼 움직이면 3칸씩 -> 움직임 한번, 원숭이는 인접한 칸 1칸씩 -> 움직임 한번
			그리디적으로 말처럼 앞에서 다 움직이면 나중에 말처럼 움직여야만 갈 수 있는 곳을 못감
			따라서, 결국엔 bfs 를 이용한 완전탐색
			주의할 점은 특정 좌표에 몇번의 동작으로 왔는지를 구분해주어야 한다.
			visited 를 2차원으로 선언하는 것이 아니라 지금까지 말처럼 움직인 횟수 k에 따라서도 구분이
			필요하므로 3차원으로 선언하여 visited[i][j][k] = (i, j) 좌표에 k번 움직임을 체크
			3차원 방문 관리가 핵심
 */
public class BOJ_1600 {
	
	static int K, W, H;
	static int[][] board;
	static boolean[][][] visited;
	static int ans = -1;
	static int[][] Direct = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		K = Integer.parseInt(br.readLine());
		line = br.readLine().split(" ");
		W = Integer.parseInt(line[0]); // 가로 길이
		H = Integer.parseInt(line[1]); // 세로 길이
		board = new int[H][W];
		visited = new boolean[H][W][K+1];
		
		// 격자판 정보 입력
		for(int i=0; i<H; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<W; j++) {
				board[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0, 0, 0}); // x, y, moveCnt, k
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int moveCnt = cur[2];
			int curK = cur[3];
			
			if(x == H-1 && y == W-1) {
				ans = moveCnt;
				break;
			}
			
			int nextK;
			int nx, ny;
			for(int d=0; d<12; d++) {
				// 더 이상 말처럼 이동할 수 없다면 말의 이동 방향은 가지 않음
				if(curK == K && d > 3) break;
				// 다음 위치로 이동
				nx = x + Direct[d][0];
				ny = y + Direct[d][1];
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
				if(d <= 3) nextK = curK;
				else nextK = curK + 1; 
				
				if(board[nx][ny] == 0 && !visited[nx][ny][nextK]) {
					visited[nx][ny][nextK] = true;
					q.add(new int[] {nx, ny, moveCnt+1, nextK});
				}
			}
		}
	}
}
