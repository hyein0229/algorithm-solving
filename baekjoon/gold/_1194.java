import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
 * 문제: 1194 달이 차오른다, 가자
 * 난이도: Gold 1
 * 알고리즘: bfs, 비트마스킹
 * 풀이 방법: 이동 횟수의 최솟값 -> bfs 사용한 완전 탐색
 * 		핵심은 현재 가지고 있는 열쇠 조합을 비트마스킹으로 표현하여 visit 배열을 3차원으로 관리하는 것
 * 		현재 자리에 여러 열쇠 조합으로 올 수 있기 때문에 구분이 필요.
 * 		만약, 1번 키, 4번 키를 가지고 있다면 열쇠 꾸러미는 (1 << 1 ) | (1 << 4) 로 표현 가능
 * 		다음 자리에 문이 있을 땐 문의 번호만큼 shift 연산한 값과 or 연산하여 원래의 열쇠 조합이 나온다면
 * 		해당 문의 열쇠가 있다는 것으로 판단하고 이동할 수 있다.
 */
public class _1194 {
	
	static int N, M;
	static char[][] board;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans = -1;
	static int sx, sy;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); // 세로 크기
		M = Integer.parseInt(line[1]); // 가로 크기
		board = new char[N][M];
		visited = new boolean[N][M][(1 << 6)];
		
		for(int i=0; i<N; i++) {
			String row = br.readLine();
			for(int j=0; j<M; j++) {
				board[i][j] = row.charAt(j);
				if(board[i][j] == '0') { // 출발지 설정
					sx = i;
					sy = j;
				}
			}
		}	
		
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy, 0, 0}); // x, y, moveCnt, key 조합
		visited[sx][sy][0] = true; // 시작 위치, 아무 열쇠도 없는 상태
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int moveCnt = cur[2];
			int keyComb = cur[3];

			// 출구를 찾았다면 미로를 탈출
			if(board[x][y] == '1') {
				ans = moveCnt;
				break;
			}
			
			// 인접한 곳으로 이동
			for(int k=0; k<4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '#') continue;
				
				// 빈 곳인 경우
				if(board[nx][ny] == '.' || board[nx][ny] == '0' || board[nx][ny] == '1') {
					if(!visited[nx][ny][keyComb]) {
						visited[nx][ny][keyComb] = true;
						q.add(new int[] {nx, ny, moveCnt+1, keyComb});
					}
				// 열쇠가 있는 자리인 경우
				} else if(board[nx][ny] - 'a' >= 0){
					int keyNum = board[nx][ny] - 'a';
					int newKeyComb = keyComb | (1 << keyNum); // 해당 키 추가
					if(!visited[nx][ny][newKeyComb]) {
						visited[nx][ny][newKeyComb] = true; 
						q.add(new int[] {nx, ny, moveCnt+1, newKeyComb});
					}
				// 문이 있는 자리인 경우
				} else {
					int doorNum = board[nx][ny] - 'A';
					if((((1 << doorNum) | keyComb) == keyComb) && !visited[nx][ny][keyComb]) {
						visited[nx][ny][keyComb] = true;
						q.add(new int[] {nx, ny, moveCnt+1, keyComb});
					}
				}
			}
		}
	}
}
