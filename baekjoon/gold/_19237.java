package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 19237 어른 상어
난이도: Gold 2
알고리즘: 구현, 시뮬레이션
풀이 방법: 문제에 주어진 조건에 따라 100% 구현에만 신경써주면 되는 문제이다.
        가장 주의할 것은 상어를 이동 시킨 후, 원래 있던 냄새의 남은 시간을 감소시킬 때 구현 순서이다.
        상어가 이동할 칸의 좌표를 찾은 후 바로 해당 좌표에 냄새를 생성해준다면 이후 냄새의 남은 시간을
        감소시킬 때 직전에 생성된 냄새의 남은 시간까지 감소할 수 있다는 것(k -> k-1)에 주의해야 한다.
        따라서, 원래 있던 냄새의 남은 시간을 감소시켜 준 후에 상어가 이동한 칸의 냄새를 생성해주어야 한다.
 */
public class _19237 {

    static class Node {
        int smell;
        int time;

        public Node(int smell, int time) {
            this.smell = smell;
            this.time = time;
        }
    }

    static class Shark {
        int x;
        int y;
        int d; // 방향
        boolean alive;

        public Shark(int x, int y, boolean alive) {
            this.x = x;
            this.y = y;
            this.alive = alive;
        }
    }

    static int N, M, k;
    static Node[][] board;
    static Shark[] sharks;
    static int aliveCnt;
    static Map<Integer, int[][]> priorites = new HashMap<>(); // 방향 우선순위
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        k = Integer.parseInt(line[2]);
        board = new Node[N][N];
        sharks = new Shark[M+1];
        aliveCnt = M;
        // 상어 입력
        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(line[j]);
                if(num > 0) {
                    sharks[num] = new Shark(i, j, true);
                    board[i][j] = new Node(num, k);
                } else {
                    board[i][j] = new Node(0, 0);
                }
            }
        }

        // 상어 현재 방향 입력
        line = br.readLine().split(" ");
        for(int i=0; i<M; i++) {
            sharks[i+1].d = Integer.parseInt(line[i])-1;
        }

        // 상어의 방향 우선순위 입력
        for(int num=1; num<=M; num++) {
            int[][] direct = new int[4][4];
            for(int i=0; i<4; i++) {
                line = br.readLine().split(" ");
                for(int j=0; j<4; j++) {
                    direct[i][j] = Integer.parseInt(line[j])-1;
                }
            }
            priorites.put(num, direct);
        }

        int time = 0;
        while(time < 1000 && aliveCnt > 1) {
            moveSharks(); // 상어 이동시키기
            time++;
        }

        if(aliveCnt > 1) {
            System.out.println(-1);
        }else {
            System.out.println(time);
        }

    }

    public static void moveSharks() {
        // 상어를 작은 번호부터 이동시키기
        for(int num=1; num<=M; num++) {
            if(!sharks[num].alive) {
                continue;
            }

            Shark shark = sharks[num];
            int x = shark.x;
            int y = shark.y;
            int d = shark.d; // 방향
            int[][] priority = priorites.get(num); // 현재 방향에서의 방향 우선순위

            // 냄새가 없는 칸 찾기
            boolean findMine = false;
            int tx = 0;
            int ty = 0;
            int td = 0;
            for(int i=0; i<4; i++) {
                int nd = priority[d][i]; // 움직일 방향
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if(board[nx][ny].smell == 0) { // 냄새가 없는 칸이면
                    // 상어 이동
                    tx = nx;
                    ty = ny;
                    td = nd;
                    break;
                }

                if(!findMine && board[nx][ny].smell == num) { // 자신의 냄새가 있는 칸이면
                    tx = nx;
                    ty = ny;
                    td = nd;
                    findMine = true;
                    continue;
                }
            }
            shark.x = tx;
            shark.y = ty;
            shark.d = td;
        }
        // 원래 있던 냄새는 남은 시간이 1만큼 사라짐
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j].smell > 0) {
                    board[i][j].time--;
                    if(board[i][j].time == 0) { // 냄새가 아예 없어짐
                        board[i][j].smell = 0;
                    }
                }
            }
        }
        // 상어 이동으로 새로운 냄새 생성
        for(int num=1; num<=M; num++) {
            if(sharks[num].alive) {
                int x = sharks[num].x;
                int y = sharks[num].y;
                if(board[x][y].smell == 0 || board[x][y].smell == num) {
                    board[x][y].smell = num;
                    board[x][y].time = k;
                }  else {
                    sharks[num].alive = false; // 쫓겨남
                    aliveCnt--;
                }
            }
        }
    }
}
