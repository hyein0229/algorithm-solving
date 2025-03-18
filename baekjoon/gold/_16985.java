import java.io.*;
import java.util.*;
/*
 * 문제 :16985 Maaaaaaaaaze
 * 난이도: Gold 2
 * 알고리즘: 빡구현, 그래프, 브루트포스, bfs
 * 풀이 방법: dfs + bfs 가 모두 사용되는 빡구현 문제.
 * 			먼저, dfs 를 사용하여 판을 쌓는  순서를 구한 다음, 즉 순열을 사용
 * 			각 판에 대해서도 90도씩 회전하면서 모든 경우의 상태에 대해 최종적으로 bfs 를 사용하여
 * 			입구에서부터 출구까지 최단 이동거리를 구해준다.
 * 			이때, 시간 단축을 위해 bfs 탐색 중 이동거리가 이미 지금까지의 구한 최단 이동거리를 넘으면 바로 탈출하도록 하였다.
 */
public class _16985 {
	static int[][][] maze;
	static int[] seq = new int[5];
	static boolean[] isSelected = new boolean[5];
	static int ans; // 최소 이동 횟수
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		maze = new int[5][5][5];
		ans = Integer.MAX_VALUE;
		
		// 미로 정보 입력
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				line = br.readLine().split(" ");
				for(int k=0; k<5; k++) {
					maze[i][j][k] = Integer.parseInt(line[k]);
				}
			}
		}
		
		buildPlate(0, 0);
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}

	// 판을 쌓는 순열 구하기
	public static void buildPlate(int cnt, int start) {
		if(cnt == 5) {
			int[][][] tmp = new int[5][5][5];
			for(int i=0; i<5; i++) {
				int plate = seq[i]; // 현재 판
				for(int j=0; j<5; j++) {
					for(int k=0; k<5; k++) {
						tmp[i][j][k] = maze[plate][j][k];
					}
				}
			}
			
			rotatePlate(0, tmp); // 각각의 판을 돌리기
			return;
		}
		
		for(int i=0; i<5; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				seq[cnt] = i;
				buildPlate(cnt+1, i);
				isSelected[i] = false;
			}
		}
	}
	
	public static void rotatePlate(int idx, int[][][] tmp) {
		// 다 돌리면
		if(idx == 5) {
			bfs(tmp);
			return;
		}
		
		int[][] rotate = new int[5][5];
		for(int k=0; k<4; k++) {
			// 90도 회전
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					rotate[j][4-i] = tmp[idx][i][j];
				}
			}
			
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					tmp[idx][i][j] = rotate[i][j];
				}
			}
			
			rotatePlate(idx+1, tmp);
		}
	}
	
	public static void bfs(int[][][] tmp) {
		
       if(tmp[0][0][0] == 0 || tmp[4][4][4] == 0) {
            return;
        }
       
        boolean[][][] visited = new boolean[5][5][5];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0, 0, 0});
        visited[0][0][0] = true;


        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int p = pos[0];
            int x = pos[1];
            int y = pos[2];
            int dist = pos[3];
            
            if(dist == ans) {
            	break;
            }

            // 최소 이동 거리 갱신
            if(p == 4 && x == 4 && y == 4) {
                ans = Math.min(ans, dist);
                break;
            }

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                    continue;
                }

                if(!visited[p][nx][ny] && tmp[p][nx][ny] == 1) { 
                    visited[p][nx][ny] = true;
                    q.add(new int[] {p, nx, ny, dist+1});
                }
            }

            if(p-1 >= 0 && !visited[p-1][x][y] && tmp[p-1][x][y] == 1) {
                visited[p-1][x][y] = true;
                q.add(new int[] {p-1, x, y, dist+1});
            }

            if(p+1 < 5 && !visited[p+1][x][y] && tmp[p+1][x][y] == 1) {
                visited[p+1][x][y] = true;
                q.add(new int[] {p+1, x, y, dist+1});
            }
        }
	}
}
