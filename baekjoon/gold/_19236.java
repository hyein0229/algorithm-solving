package baekjoon;

import java.io.*;

/*
문제: 19236 청소년 상어
난이도: Gold 2
알고리즘: 구현, 백트래킹, 시뮬레이션
 */
public class _19236 {

    public static class Fish {
        int x;
        int y;
        int d; // 방향
        boolean alive; // 생존 여부

        public Fish(int x, int y, int d, boolean alive) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.alive = alive;
        }
    }

    static int[][] board = new int[4][4];
    static Fish[] fishes = new Fish[17];
    static int answer = 0;
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=0; j<8; j += 2) {
                int num = Integer.parseInt(line[j]); // 번호
                int direct = Integer.parseInt(line[j+1]); // 방향
                board[i][j / 2] = num;
                fishes[num] = new Fish(i, j/2, direct, true);
            }
        }

        // (0, 0) 에 있는 물고기 먹음
        int fNum = board[0][0];
        Fish fish = fishes[fNum];
        fish.alive = false;
        board[0][0] = -1;

        move(0, 0, fNum, fish.d);
        System.out.println(answer);
    }

    public static void move(int sx, int sy, int total, int d) {

        // 물고기 이동
        moveFish(sx, sy);

        // 상태 복구를 위한 복사
        int[][] temp = new int[4][4];
        Fish[] tempFishes = new Fish[17];
        copy(temp, tempFishes, board, fishes);

        int step = 1;
        while(true) {
            int nx = sx + dx[d] * step;
            int ny = sy + dy[d] * step;
            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) { // 경계를 넘으면 이동 불가
                answer = Math.max(answer, total);
                break;
            }
            // 물고기가 없는 칸이면 건너뜀
            if(board[nx][ny] == -1) {
                step++;
                continue;
            }

            // 해당 칸으로 이동하여 물고기를 먹음
            int fNum = board[nx][ny];
            Fish target = fishes[fNum];
            target.alive = false;
            board[nx][ny] = -1;

            move(nx, ny, total + fNum, target.d);

            // 백트래킹
            // 깊은 복사 해야함.. 주의....이것때문에 삽질..
            copy(board, fishes, temp, tempFishes);
            step++; // 한 칸 더 이동
        }
    }

    public static void moveFish(int sx, int sy) {

        for(int i=1; i<=16; i++) {
            Fish fish = fishes[i];
            if(!fish.alive) { // 죽은 물고기인 경우
                continue;
            }

            boolean moved = false; // 이동 여부
            int curd = fish.d; // 현재 방향
            while(!moved) {
                int nx = fish.x + dx[curd];
                int ny = fish.y + dy[curd];
                // 경계를 벗어나거나 상어가 있으면 반시계 회전
                if((nx < 0 || nx >= 4 || ny < 0 || ny >= 4) || (sx == nx && sy == ny)) {
                    curd = (curd + 1 > 8) ? 1 : curd + 1; // 회전한 후의 방향
                    if(curd == fish.d) { // 처음 방향으로 돌아온 경우엔 탈출
                        break;
                    }
                    continue;
                }
                // 물고기 이동 -> 서로의 위치를 바꿈
                switchFish(nx, ny, fish.x, fish.y, board[nx][ny], i, curd);
                moved = true;
            }
        }
    }

    public static void switchFish(int nx, int ny, int x, int y, int num1, int num2, int direct) {
        // 이동할 곳에도 물고기가 있으면 옮겨줌
        if(num1 != -1) {
            fishes[num1].x = x;
            fishes[num1].y = y;
        }

        fishes[num2].x = nx;
        fishes[num2].y = ny;
        fishes[num2].d = direct;
        board[nx][ny] = num2;
        board[x][y] = num1;
    }

    public static void copy(int[][] board, Fish[] fishes, int[][] _board, Fish[] _fishes) {
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                board[i][j] = _board[i][j];
            }
        }
        for(int i=1; i<=16; i++) {
            Fish fish = _fishes[i];
            fishes[i] = new Fish(fish.x, fish.y, fish.d, fish.alive);
        }
    }
}
