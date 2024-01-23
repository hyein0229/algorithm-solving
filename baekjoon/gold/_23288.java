package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 23288 주사위 굴리기 2
난이도: Gold 3
알고리즘: 구현, 시뮬레이션, 그래프, BFS
풀이 방법: 주사위를 굴리는 구현은 이전에 주사위 굴리기 문제를 풀어봤었기 때문에 쉽게 구현할 수 있었다.
        점수를 구할 땐 연속된 칸에 대한 탐색이 필요하므로 BFS를 이용하여 구현하였다.
 */
public class _23288 {

    static int N;
    static int M;
    static int K;
    static int x, y, d;
    static int score = 0;
    static int[][] board;
    static int[] dice = new int[7];
    static int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);
        board = new int[N+1][M+1];

        for(int i=1; i<=N; i++) {
            line = br.readLine().split(" ");
            for(int j=1; j<=M; j++) {
                board[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        for(int i=1; i<=6; i++) {
            dice[i] = i;
        }

        // K번 이동하기
        x = 1;
        y = 1;
        d = 0; // 처음 방향 동쪽
        for(int i=0; i<K; i++) {
            moveDice();
        }
        System.out.println(score);

    }

    public static void moveDice() {
        // 현재 이동 방향으로 주사위를 굴려서 이동
        x += dx[d];
        y += dy[d];
        if(x < 1 || x > N || y < 1 || y > M) {
            x -= dx[d];
            y -= dy[d];
            d = (d + 2) % 4; // 반대 방향으로 바꾸기
            x += dx[d];
            y += dy[d];
        }

        if(d == 0) { // 동쪽
            int tmp = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = tmp;

        } else if(d == 1) { // 남쪽
            int tmp = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = dice[1];
            dice[1] = tmp;

        } else if(d == 2) { // 서쪽
            int tmp = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = tmp;

        } else { // 북쪽
            int tmp = dice[6];
            dice[6] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[5];
            dice[5] = tmp;
        }

        // 점수 획득하기
        score += getScore();

        // 다음 이동 방향 구하기
        if(dice[6] > board[x][y]) { // 시계 방향 회전
            d = (d + 1) % 4;
        } else if(dice[6] < board[x][y]) { // 반시계 방향 회전
            d = (d == 0) ? 3 : d - 1;
        }

    }

    public static int getScore() {
        // 점수 구하기
        int C = 1;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][M+1];
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] pos = q.poll();
            for(int i=0; i<4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                if(nx < 1 || nx > N || ny < 1 || ny > M) {
                    continue;
                }
                if(!visited[nx][ny] && (board[nx][ny] == board[pos[0]][pos[1]])) {
                    C++;
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return board[x][y] * C;
    }
}
