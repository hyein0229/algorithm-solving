package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 21611 마법사 상어와 블리자드
난이도: Gold 1
알고리즘: 구현, 시뮬레이션
풀이 방법: 100% 구현문제이다. 골1이지만 그래도 그렇게 어렵지 않게 풀 수 있었다.
        이렇게 점점 큰 사각형을 그리며 이동하는 부분과 구슬 이동 구현이 가장 까다로웠던 것 같다.
        구슬 이동 시에는 빈칸이 있는 경우 앞으로 구슬들을 땡겨주어야 하는데 가장 쉬운 방법은 모든 칸을 탐색하면서
        구슬이 있는 칸은 구슬을 구슬 배열에 집어 넣고 탐색을 마친 후 아예 새로운 보드판에 구슬들을 차례대로 꺼내어 넣어주는 것이다.
        이 부분을 간단히 구현하는 방법을 생각해내는 것이 관건인 문제같다.
 */
public class _21611 {

    static int N;
    static int M;
    static int sx, sy; // 상어 좌표
    static int[][] board;
    static int[] bombCnt = new int[4];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        board = new int[N][N];
        sx = N/2;
        sy = N/2;

        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 블리자드 M번 시전
        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int d = Integer.parseInt(line[0]);
            int s = Integer.parseInt(line[1]);

            destroy(d, s); // 구슬 파괴 단계
            move();

            // 구슬 폭발 단계
            boolean isContinued = true;
            while(isContinued) {
                isContinued = bomb();
                if(isContinued) { // 폭발한 구슬이 있다면 구슬 이동
                    move();
                }
            }
            change(); // 구슬 변화 단계
        }
        System.out.println(1 * bombCnt[1] + 2 * bombCnt[2] + 3 * bombCnt[3]);

    }

    public static void destroy(int d, int s) {
        if(d == 1) {
            d = 3;
        } else if(d == 2) {
            d = 1;
        } else if(d == 3) {
            d = 0;
        } else {
            d = 2;
        }
        // 구슬 파괴하여 빈 칸으로 만들기
        for(int i=1; i<=s; i++) {
            int nx = sx + dx[d]*i;
            int ny = sy + dy[d]*i;
            board[nx][ny] = 0;
        }
    }

    public static boolean bomb() {
        int d = 0; // 현재 방향
        int dist = 1;
        int curNumber = 0;
        int x = sx;
        int y = sy;
        boolean flag = false;
        Queue<int[]> q = new LinkedList<>();
        while(dist < N) {
            int limit = (dist == N-1) ? dist*3 : dist*2;
            for(int step=1; step<=limit; step++) {
                x += dx[d];
                y += dy[d];
                // 구슬 번호가 연속된다면
                if(board[x][y] == curNumber) {
                    q.add(new int[]{x, y});
                } else {
                    if(q.size() >= 4) { // 구슬이 4개 이상 연속하면 폭발
                        flag = true;
                        while(!q.isEmpty()) {
                            int[] pos = q.poll();
                            bombCnt[curNumber]++;
                            board[pos[0]][pos[1]] = 0; // 폭발하여 빈 칸이 됨
                        }
                    } else {
                        q.clear(); // 초기화
                    }
                    q.add(new int[]{x, y});
                    curNumber = board[x][y];
                }
                if(step % dist == 0) {
                    d = (d + 1) % 4; // 방향 꺾기
                }
            }
            // 이동 거리
            dist++;
        }
        return flag;
    }

    public static void change() {
        int[] AB = new int[N*N*2];
        int d = 0; // 현재 방향
        int dist = 1;
        int x = sx;
        int y = sy;
        int curNumber = 0;
        int cnt = 0;
        int idx = 0;
        while(dist < N) {
            int limit = (dist == N-1) ? dist*3 : dist*2;
            for(int step=1; step<=limit; step++) {
                x += dx[d];
                y += dy[d];
                if(board[x][y] == curNumber) { // 같은 구슬이 연속된 경우
                    cnt++;
                } else { // 연속되지 않은 경우
                    if(curNumber != 0) {
                        AB[idx] = cnt;
                        AB[idx+1] = curNumber;
                        idx += 2;
                    }
                    curNumber = board[x][y];
                    cnt = 1;
                }

                if(step % dist == 0) {
                    d = (d + 1) % 4; // 방향 꺾기
                }
            }
            // 이동 거리
            dist++;
        }
        nextBoard(AB);
    }

    public static void move() {
        int[] arr = new int[N*N]; // 구슬 배열
        int d = 0; // 현재 방향
        int dist = 1;
        int x = sx;
        int y = sy;
        int idx = 0; // 구슬 개수 세기
        while(dist < N) {
            int limit = (dist == N-1) ? dist*3 : dist*2;
            for(int step=1; step<=limit; step++) {
                x += dx[d];
                y += dy[d];
                // 구슬이 있다면 구슬 배열에 번호 저장
                if(board[x][y] != 0) {
                    arr[idx] = board[x][y];
                    idx++;
                }
                if(step % dist == 0) {
                    d = (d + 1) % 4; // 방향 꺾기
                }
            }
            // 이동 거리
            dist++;
        }
        nextBoard(arr);
    }

    public static void nextBoard(int[] arr) {
        board = new int[N][N]; // 초기화
        int d = 0;
        int dist = 1;
        int x = sx;
        int y = sy;
        int idx = 0;
        // 구슬을 차례대로 삽입
        while(dist < N) {
            int limit = (dist == N-1) ? dist*3 : dist*2;
            for(int step=1; step<=limit; step++) {
                x += dx[d];
                y += dy[d];
                board[x][y] = arr[idx];
                idx++;
                if(step % dist == 0) {
                    d = (d + 1) % 4; // 방향 꺾기
                }
            }
            // 이동 거리
            dist++;
        }
    }
}
