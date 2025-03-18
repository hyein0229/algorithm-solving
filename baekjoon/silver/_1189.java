package baekjoon;

import java.io.*;
/*
문제: 1189 컴백홈
난이도: Silver 1
알고리즘: 그래프, dfs, 백트래킹
풀이 방법: 왼쪽 아래점을 시작으로 깊이 우선 탐색을 하여 오른쪽 위 집에 도착했을 때 거리 d 가 K가 된 경우 카운트를 해준다.
        이때 (x, y) 좌표로 이동 시 갔다가 다시 돌아와서 다른 길에 대해 탐색해야 하므로 방문 체크에 대해 백트래킹을 해주어야 한다.
 */
public class _1189 {

    static int R;
    static int C;
    static int K;
    static String[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[][] visited;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);

        visited = new boolean[R][C];
        map = new String[R][C];
        for(int i=0; i<R; i++) {
            String row = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = row.substring(j, j+1);
            }
        }

        dfs(R-1, 0, 1);
        System.out.println(answer);
    }

    public static void dfs(int x, int y, int d) {
        // 집에 도착
        if(x == 0 && y == C-1) {
            if(d == K) {
                answer++;
            }
            return;
        }

        if(d == K) {
            return;
        }

        visited[x][y] = true;

        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }
            // 갈 수 있으면
            if(!visited[nx][ny] && !map[nx][ny].equals("T")) {
                dfs(nx, ny, d+1);
            }
        }

        visited[x][y] = false;
    }
}
