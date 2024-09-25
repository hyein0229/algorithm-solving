package baekjoon;

import java.util.*;
import java.io.*;
/*
문제: 1986 체스
난이도: Silver 1
알고리즘: 구현
 */
public class _1986 {
    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    static int ans;
    static int[][] knightDirect = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
    static int[][] queenDirect = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}, {-1, -1}, {1, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]); // 세로 크기
        m = Integer.parseInt(line[1]); // 가로 크기
        board = new int[n+1][m+1];
        visited = new boolean[n+1][m+1];
        ans = 0;

        StringTokenizer st;
        for(int i=1; i<=3; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int j=0; j<num; j++) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                board[r][c] = i;
            }
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(board[i][j] == 0) continue;
                visited[i][j] = true;
                if(board[i][j] == 2) {
                    moveKnight(i, j);
                } else if(board[i][j] == 1) {
                    moveQueen(i, j);
                }
            }
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(!visited[i][j]) ans++;
            }
        }

        System.out.println(ans);
    }

    private static void moveQueen(int r, int c) {
        for(int k=0; k<8; k++) {
            int nr = r + queenDirect[k][0];
            int nc = c + queenDirect[k][1];

            while(!(nr < 1 || nr > n || nc < 1 || nc > m) ) {
                if(board[nr][nc] > 0) break;
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                }
                nr += queenDirect[k][0];
                nc += queenDirect[k][1];
            }
        }
    }

    public static void moveKnight(int r, int c) {
        for(int k=0; k<8; k++) {
            int nr = r + knightDirect[k][0];
            int nc = c + knightDirect[k][1];
            if(nr < 1 || nr > n || nc < 1 || nc > m) continue;
            if(visited[nr][nc]) continue;
            visited[nr][nc] = true;
        }
    }
}
