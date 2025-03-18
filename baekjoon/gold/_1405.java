package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 문제: 1405 미친 로봇
 * 난이도: Gold 3
 * 알고리즘: dfs
 * 풀이 방법: N이 최대 14이므로 로봇이 위치한 좌표를 나타낼 수 있은 보드판을 30x30 크기로 2차원 배열 생성
 * 	  	정가운데 위치를 시작으로 재방문하지 않는 경로로 이동하면서 N번의 이동이 끝났을 때의 확률을 모두 더해 출력
 * 		
 *
 */
public class _1405 {
	
	static final int MAX_BOARD_SIZE = 30;
	
	static int N;
	static int[] p; // 동, 서, 남, 북
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static double ans = 0;
	static int[][] board;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String[] line = br.readLine().split(" ");
	    p = new int[4];
	    N = Integer.parseInt(line[0]);
	    board = new int[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	    visited = new boolean[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	    
	    for(int i=1; i<=4; i++) {
	    	p[i-1] = Integer.parseInt(line[i]); // 확률
	    }
	    
	    visited[MAX_BOARD_SIZE/2][MAX_BOARD_SIZE/2] = true; 
	    dfs(MAX_BOARD_SIZE/2, MAX_BOARD_SIZE/2, 0, 1.0);
	    System.out.println(ans);
	}
	
	public static void dfs(int x, int y, int cnt, double perc) {
		if(cnt == N) {
			ans += perc;
			return;
		}

		for(int k=0; k<4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(nx < 0 || nx >= MAX_BOARD_SIZE || ny < 0 || ny >= MAX_BOARD_SIZE) continue;
			
			if(!visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny, cnt+1, perc * (p[k]/100.0));
				visited[nx][ny] = false;
			}	
		}
	}
}
