package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17143 낚시왕
난이도: Gold 1
알고리즘: 구현, 시뮬레이션
풀이방법: 상어 이동 부분만 까다로웠다. 상어를 이동시키는 것을 한 칸씩 이동시키면 시간 초과가 일어나기 때문에
       나머지 연산을 이용하여 효율적으로 이동시켜야 한다. 상어가 원래 자신의 상태로 돌아오게 되는 반복된 이동은 다 무시할 수 있다.
       상어가 현재의 자리에서 현재의 방향을 다시 가질 때까지 이동 횟수를 계산하면 세로 방향, 가로 방향 각각 (R-1) * 2, (C-1) * 2 만큼 이동한다.
       속력을 이 값을 나눈 나머지만큼만 한 칸씩 이동시켜 벽에 부딪힐 때마다 방향을 전환시켜 다시 이동시켜 준다.
 */
public class _17143 {

    static class Shark {
        int r, c, s, d, z;

        public Shark (int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int R;
    static int C;
    static int M;
    static Shark[][] board;
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Shark[R+1][C+1];

        // 상어 입력
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            board[r][c] = new Shark(r, c, s, d, z);
        }

        for(int i=1; i<=C; i++) {
            catchShark(i); // 잡기
            moveSharks(); // 이동
        }
        System.out.println(answer);
    }

    public static void catchShark(int c) {
        for(int i=1; i<=R; i++) {
            if(board[i][c] != null) {
                answer += board[i][c].z;
                board[i][c] = null;
                break;
            }
        }
    }

    public static void moveSharks() {
        Queue<Shark> sharks = new LinkedList<>();
        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                if(board[i][j] != null) sharks.add(board[i][j]);
            }
        }
        board = new Shark[R+1][C+1]; // 주의! 꼭 초기화 필요

        while(!sharks.isEmpty()) {
            Shark shark = sharks.poll();

            int speed = shark.s;
            if(shark.d <= 2) {
                speed %= (R-1) * 2;
            } else {
                speed %= (C-1) * 2;
            }

            while(speed > 0) {
                int nr = shark.r + dr[shark.d];
                int nc = shark.c + dc[shark.d];
                // 범위를 벗어는 경우, 즉 벽에 부딪힘
                if(nr < 1 || nr > R || nc < 1 || nc > C) {
                    shark.d = (shark.d == 1 || shark.d == 3) ? shark.d + 1: shark.d - 1; // 방향 바꾸기
                    shark.r += dr[shark.d];
                    shark.c += dc[shark.d];
                } else {
                    shark.r = nr;
                    shark.c = nc;
                }
                speed--;
            }

            if(board[shark.r][shark.c] != null) { // 이미 상어가 있는 자리면
                // 이미 있던 상어의 크기가 더 크면 이동 x
                if(board[shark.r][shark.c].z > shark.z) {
                    continue;
                }
            }
            board[shark.r][shark.c] = shark;
        }
    }
}
