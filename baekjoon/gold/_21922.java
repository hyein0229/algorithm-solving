package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 21922 학부 연구생 민상
난이도: Gold 5
알고리즘: 구현, 그래프, 시뮬레이션
풀이 방법: 어렵지 않은 구현 문제였다. 에어컨마다 상하좌우로 바람을 움직여서 지나가는 곳을 체크해주면 된다.
        현재 좌표와 방향을 함수에 넘겨주어 해당 좌표의 칸이 체크가 안되어 있다면 체크해주고, 물건이 있다면
        주어진 규칙에 따라 방향을 바꾸어 준 후 해당 방향으로 움직인 후의 좌표와 방향을 넘겨주어
        함수를 재귀호출하는 방식으로 풀어주었다.
 */
public class _21922 {

    static int N, M;
    static int[][] lab;
    static List<int[]> airCon = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int cnt = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        lab = new int[N][M];
        visited = new boolean[N][M];

        // 연구실 내부 구조 입력
        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                lab[i][j] = Integer.parseInt(line[j]);
                if(lab[i][j] == 9) { // 에어컨 위치 삽입
                    airCon.add(new int[]{i, j});
                }
            }
        }

        for(int[] a : airCon) {
            for(int i=0; i<4; i++) { // 상하좌우 바람 이동
                airConditioner(a[0], a[1], i);
            }
        }
        System.out.println(cnt);

    }

    public static void airConditioner(int x, int y, int d) {

        if(!visited[x][y]) {
            visited[x][y] = true;
            cnt++;
        }
        // 물건이 있으면 바람 방향을 바꿈
        if(lab[x][y] >= 1 && lab[x][y] <= 4) {
            d = changeDirect(lab[x][y], d);
        }
        // 바람 방향대로 이동
        int nx = x + dx[d];
        int ny = y + dy[d];
        // 벽이 아니고 에어컨이 아니면
        if(nx >= 0 && nx < N && ny >= 0 && ny < M && lab[nx][ny] != 9) {
            airConditioner(nx, ny, d);
        }
    }

    public static int changeDirect(int type, int d) {
        int newDirect;
        if(type == 1) {
            newDirect = (d == 1 || d == 3) ? (d + 2) % 4 : d;
        }else if(type == 2) {
            newDirect = (d == 0 || d == 2) ? (d + 2) % 4 : d;
        }else if(type == 3) {
            newDirect = (d == 1 || d == 3) ? (d + 1) % 4 : d - 1;
            if(newDirect == -1) newDirect = 3;
        } else {
            newDirect = (d == 0 || d == 2) ? (d + 1) % 4 : d - 1;
        }
        return newDirect;
    }
}
