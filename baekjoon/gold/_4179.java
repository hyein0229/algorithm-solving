package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 4179 불!
난이도: Gold 4
알고리즘: 그래프, BFS
풀이 방법: 불에 대한 BFS, 지훈이에 대한 BFS를 각각 돌린다.
    불은 지훈이의 영향을 받지 않고 전파되므로 먼저 불을 상하좌우로 전파시키면서 최단 전파된 시간을 모두 기록한다.
    그 다음, 지훈이를 BFS를 활용하여 이동시키면서 해당 칸의 불이 있으면 불이 전파된 시간, 즉 도달한 시간이
    지훈이가 도착한 시간보다 작거나 같다면 이동할 수 없도록 하고, 그렇지 않다면 이동시키고 이동시간을 1씩 축적하여 기록한다.
    만약, 배열 범위를 벗어났다면 탈출한 것이므로 정답을 출력한다.
 */
public class _4179 {

    static int R, C;
    static char[][] board;
    static int[][] fireSpread;
    static int[][] dist;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        board = new char[R][C];
        fireSpread = new int[R][C]; // 불의 전파 시간
        dist = new int[R][C]; // 지훈의 이동 시간
        Queue<int[]> fire = new LinkedList<>();
        Queue<int[]> q = new LinkedList<>();

        // 미로 입력
        for(int i=0; i<R; i++) {
            String row = br.readLine();
            for(int j=0; j<C; j++) {
                board[i][j] = row.charAt(j);
                fireSpread[i][j] = -1;
                dist[i][j] = -1;
                if(board[i][j] == 'J') {
                    q.add(new int[]{i, j});
                    dist[i][j] = 0;
                } else if(board[i][j] == 'F') {
                    fire.add(new int[]{i, j});
                    fireSpread[i][j] = 0;
                }
            }
        }
        // 불의 최단 전파 시간 구하기
        while(!fire.isEmpty()) {
            int[] cur = fire.poll();
            int x = cur[0];
            int y = cur[1];
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                // 벽이 아니고 불이 아직 업다면 전파
                if(board[nx][ny] != '#' && fireSpread[nx][ny] == -1) {
                    fireSpread[nx][ny] = fireSpread[x][y] + 1; // 불의 전파 시간 기록
                    fire.add(new int[]{nx, ny});
                }
            }
        }
        // 지훈이 최단 이동
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                // 경계를 벗어나면 탈출에 성공한 것
                if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    System.out.println(dist[x][y] + 1);
                    return;
                }
                if(board[nx][ny] == '#' || dist[nx][ny] >= 0) { // 벽이거나 이미 방문한 곳이면 넘어감
                    continue;
                }
                // 불이 없는 공간이거나 불보다 먼저 도착할 수 있으면 이동
                if(fireSpread[nx][ny] == -1 || dist[x][y] + 1 < fireSpread[nx][ny]) {
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
