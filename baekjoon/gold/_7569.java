package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
문제: 7569 토마토
난이도: Gold 5
알고리즘: 그래프 탐색, bfs
풀이 방법: "최소" 일수를 구하기 위해선 각 토마토가 익는 최소 일수 중 max 값을 구하면 된다. "최소" 를 구하는 것 이므로 큐를 사용한 bfs 를 이용하여 탐색한다.
        bfs 를 통해 인접한 토마토들을 탐색하면서 영향을 받은 토마토가 익은 일수에 +1 을 하면서 일수를 기록한다.
 */
public class _7569 {
    static int m;
    static int n;
    static int h;
    static int[][] direct = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}}; // 6가지 방향

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        m = Integer.parseInt(line[0]); // 가로칸 수
        n = Integer.parseInt(line[1]); // 세로칸 수
        h = Integer.parseInt(line[2]); // 상자의 수

        int[][][] box = new int[h][n][m];
        int[][][] visited = new int[h][n][m];
        Queue<int[]> q = new LinkedList();
        int tomatoCnt = 0; // 존재하는 토마토 개수
        int done; // 익은 개수

        // 상자의 토마토 정보 입력
        for(int i=0; i<h; i++) {
            for(int j=0; j<n; j++) {
                line = br.readLine().split(" ");
                for(int k=0; k<m; k++) {

                    if(Integer.parseInt(line[k]) != -1) {
                        tomatoCnt++;
                    }
                    // 익은 토마토일 때
                    if(Integer.parseInt(line[k]) == 1) {
                        q.add(new int[]{i, j, k, 0}); // z, y, x, 시간(일) 순
                        visited[i][j][k] = 1;
                    }
                    box[i][j][k] = Integer.parseInt(line[k]);
                }
            }
        }

        done = q.size();
        if(done == tomatoCnt) { // 토마토가 모두 익은 경우
            System.out.println(0);

        } else {
            int days = 0; // 최소 걸리는 일 수
            while(!q.isEmpty()) {
                int[] cur = q.remove();
                days = Math.max(days, cur[3]);

                for(int i=0; i<6; i++) {
                    int nz = cur[0] + direct[i][0];
                    int ny = cur[1] + direct[i][1];
                    int nx = cur[2] + direct[i][2];

                    if(nx < 0 || nx >= m || ny < 0 || ny >= n || nz < 0 || nz >= h) {
                        continue;
                    }

                    // 인접한 토마토가 익지 않은 토마토라면
                    if (box[nz][ny][nx] == 0 && visited[nz][ny][nx] != 1){
                        q.add(new int[]{nz, ny, nx, cur[3]+1});
                        visited[nz][ny][nx] = 1;
                        done++; // 익은 토마토 개수 증가
                    }
                }
            }

            // 토마토가 다 익었다면 최소 걸리는 일 수 출력
            if(done == tomatoCnt) {
                System.out.println(days);
            } else {
                System.out.println(-1);
            }
        }
    }

}
