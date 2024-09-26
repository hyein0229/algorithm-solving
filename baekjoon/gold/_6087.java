package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
문제: 6087 레이저 통신
난이도: Gold 3
알고리즘: 그래프, bfs
풀이 방법: dfs 를 사용하여 현재 위치에서 거울을 설치하지 않고(방향 바꿈 x) 이동한 경우와
        거울 두 개를 각각 설치하고(방향 바꿈 o) 이동한 경우에 대해 재귀 탐색하여 C에 도달할 때까지 진행한다.
        이때, 각 위치에서의 거울 설치 개수를 minCnt 배열에 저장하여 현재 위치에서의 최소의 거울 설치 개수보다
        현재 탐색 경로에서 설치 개수가 더 많아지면 탐색을 하지 않아도 되므로 백트래킹해주도록 하였다.
        주의할 것! 처음 시작점에서 상하좌우 각각 시작 방향을 달리하여 dfs 호출할 때 minCnt 초기화 꼭 필요
 */
public class _6087 {
    static int H, W;
    static char[][] board;
    static int[][] c;
    static int[][] changeDirect = {{2, 3}, {2, 3}, {0, 1}, {0, 1}};
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static int[][] minCnt;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        W = Integer.parseInt(line[0]);
        H = Integer.parseInt(line[1]);
        board = new char[H][W];
        minCnt = new int[H][W];
        c = new int[2][2];

        int cnt = 0;
        for(int i=0; i<H; i++) {
            String row = br.readLine();
            for(int j=0; j<W; j++) {
                board[i][j] = row.charAt(j);
                if(board[i][j] == 'C') {
                    c[cnt][0] = i;
                    c[cnt++][1] = j;
                }
            }
        }
        // 현재 위치에서 상, 하, 좌, 우 모든 방향으로 레이저를 쏨
        for(int k=0; k<4; k++) {
            for(int i=0; i<H; i++) {
                Arrays.fill(minCnt[i], Integer.MAX_VALUE);
            }
            minCnt[c[0][0]][c[0][1]] = 0;
            dfs(c[0][0], c[0][1], k, 0);
            ans = Math.min(minCnt[c[1][0]][c[1][1]], ans);
        }
        System.out.println(ans);
    }

    public static void dfs(int x, int y, int direct, int mirrorCnt) {
        // 해당 방향으로 이동
        int nx = x + dx[direct];
        int ny = y + dy[direct];
        if(nx < 0 || nx >= H || ny < 0 || ny >= W) return;
        if(board[nx][ny] == '*') return;
        if(minCnt[nx][ny] <= mirrorCnt) {
            return;
        }

        minCnt[nx][ny] = mirrorCnt; // 최솟값 갱신

        if(x == c[1][0] && y == c[1][1]) {
            return;
        }

        //거울 설치 x
        dfs(nx, ny, direct, mirrorCnt);
        // 거울 설치 o
        dfs(nx, ny, changeDirect[direct][0], mirrorCnt+1);
        dfs(nx, ny, changeDirect[direct][1], mirrorCnt+1);
    }
}
