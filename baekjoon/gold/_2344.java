package baekjoon;

import java.io.*;
/*
문제: 2344 거울
난이도: Gold 4
알고리즘: 구현, 시뮬레이션
풀이 방법: 빛이 이동할 수 있는 범위는 행 1~N, 열 1~M 로 잡는다.
      왼쪽 구멍이면 오른쪽으로, 하단 구멍이면 위로, 오른쪽 구멍이면 왼쪽으로, 상단 구멍이면 아래로 처음 빛의 방향이 정해진다.
      현재 방향으로 한 칸 이동한 후의 좌표를 확인한 후 좌표가 범위를 벗어나면 구멍이므로 나가는 구멍 번호를 구해 저장한다.
      만약, 거울이 있는 좌표라면 방향을 전환한다. 현재 빛의 방향에 따라 상 -> 우, 하 -> 좌, 좌 -> 하, 우 -> 상
      으로 방향을 전환한다. 빛의 방향이 정해지면 다시 재귀 호출로 다음 좌표와 방향을 넘겨 또 한 칸을 이동시킨다.
 */
public class _2344 {
    static int N, M;
    static int[][] box;
    static int[] answer;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        box = new int[N+1][M+1];
        answer = new int[(2*N) + (2*M) + 1];

        for(int i=1; i<=N; i++) {
            line = br.readLine().split(" ");
            for(int j=1; j<=M; j++) {
                box[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        for(int i=1; i<=(2*N) + (2*M); i++) {
            simulation(i);
        }

        for(int i=1; i<=(2*N) + (2*M); i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();

    }

    public static void simulation(int num) {
        if(num <= N) { // 왼쪽
            move(num, 0, 1, num);
        } else if(num <= N+M) { // 하단
            move(N+1, num - N, 2, num);
        } else if(num <= (2*N) + M) { // 오른쪽
            move(((2*N) + M) - (num-1), M+1, 0, num);
        }else { // 상단
            move(0, ((2*N) + (2*M)) - (num-1), 3, num);
        }
    }

    public static void move(int x, int y, int d, int in) {
        // 빛이 현재 방향으로 이동한 후의 위치
        int nx = x + dx[d];
        int ny = y + dy[d];
        if(nx < 1 || nx >= N+1 || ny < 1 || ny >= M+1) { // 구멍으로 나갔는지 범위 체크
            int out = 0; // 나간 구멍 번호
            if(nx == 0) out = ((2*N) + (2*M)) - (ny-1);
            else if(nx == N+1) out = N + ny;
            else if(ny == 0) out = nx;
            else out = ((2*N) + M) - (nx-1);
            answer[in] = out;
            return;
        }

        if(box[nx][ny] == 1) { // 거울이 있으면 방향 바꾸기
            if(d == 0) d = 3;
            else if(d == 1) d = 2;
            else if(d == 2) d = 1;
            else d = 0;
            move(nx, ny, d, in);
        } else {
            move(nx, ny, d, in);
        }
    }
}
