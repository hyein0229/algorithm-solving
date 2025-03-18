package baekjoon;

import java.io.*;
/*
문제: 14499 주사위 굴리기
난이도: Gold 4
알고리즘: 구현, 시뮬레이션
풀이 방법: 주사위 굴릴 때의 규칙을 알아내는 것이 어려웠다. 주사위 면 별로 번호를 고정해놓고 동, 서, 북, 남으로 이동 시
        바닥 면이 몇번으로 바뀌는지의 관점으로 접근하다보니 규칙을 찾을 수 없었다.
        주사위를 어떤 방향으로 한 번 굴리면 면이 한 방향으로 한 칸씩 밀리는 것과 같다.
        주사위를 펴놓은 상태의 그림을 그린 후 동, 서, 남, 북으로 굴렸을 때 바뀐 상태를 보면 쉽게 규칙을 찾을 수 있다.
 */
public class _14499 {

    static int N;
    static int M;
    static int[][] map;
    static int[] dice = new int[7];
    static int x, y;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        x = Integer.parseInt(line[2]);
        y = Integer.parseInt(line[3]);
        int K = Integer.parseInt(line[4]);
        map = new int[N][M];

        // 지도 입력
        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        line = br.readLine().split(" "); // 명령 입력

        // 주사위 굴리기
        for(int i=0; i<K; i++) {
            rollDice(Integer.parseInt(line[i]));
        }
    }

    public static void rollDice(int d) {
        // 이동할 위치
        int nx = x + dx[d-1];
        int ny = y + dy[d-1];
        if(nx < 0 || nx >= N || ny < 0 ||ny >= M) { // 지도의 바깥으로 벗어남
            return;
        }

        x = nx;
        y = ny;
        if(d == 1) { // 동쪽
            int tmp = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = tmp;

        } else if(d == 2) { // 서쪽
            int tmp = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = tmp;

        } else if(d == 3) { // 북쪽
            int tmp = dice[6];
            dice[6] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[5];
            dice[5] = tmp;

        } else { // 남쪽
            int tmp = dice[6];
            dice[6] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[2];
            dice[2] = tmp;
        }

        if(map[nx][ny] == 0) {
            map[nx][ny] = dice[6];
        } else {
            dice[6] = map[nx][ny];
            map[nx][ny] = 0;
        }
        System.out.println(dice[1]); // 마주보는 면 숫자 출력
    }
}
