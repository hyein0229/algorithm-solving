package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
문제: 16509 장군
난이도: Gold 5
알고리즘: 구현, bfs
풀이 방법: '최소' 이동 횟수이므로 먼저 bfs 를 떠올렸다. 주의할 것은 이동 경로에 기물이 있으면 안되므로
        한번에 이동할 수 없고 한 칸씩 이동하면서 기물이 있는지 확인해야한다는 것이다.
 */
public class _16509 {

    static int[] dr = {-3, -3, -2, 2, 3, 3, 2, -2};
    static int[] dc = {-2, 2, 3, 3, 2, -2, -3, -3};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        // 상의 위치
        int r1 = Integer.parseInt(line[0]);
        int c1 = Integer.parseInt(line[1]);

        // 왕의 위치
        line = br.readLine().split(" ");
        int r2 = Integer.parseInt(line[0]);
        int c2 = Integer.parseInt(line[1]);

        Queue<int[]> q = new LinkedList<>();
        int answer = -1;
        int[][] visited = new int[10][9];
        q.add(new int[]{r1, c1, 0}); // 상의 좌표, 이동 횟수

        while(!q.isEmpty()) {
            int[] cur = q.remove();
            // 왕에 도달하였다면
            if(cur[0] == r2 && cur[1] == c2) {
                answer = cur[2];
                break;
            }
            // 상의 8가지 방향으로의 이동
            for(int i=0; i<8; i++) {
                // 최종 도착 지점
                int tr = cur[0] + dr[i];
                int tc = cur[1] + dc[i];

                // 상하좌우 이동 방향
                int nr = cur[0];
                int nc = cur[1];
                if(i <= 1){
                    nr -= 1;
                } else if (i <= 3){
                    nc += 1;
                } else if(i <= 5) {
                    nr += 1;
                } else {
                    nc -= 1;
                }

                boolean flag = true; // 최종 도착지점까지 이동할 수 있는지 여부
                while(flag){
                    // 장기판 범위 체크
                    if(nr < 0 || nr >= 10 || nc < 0 || nc >= 9) {
                        flag = false;
                        break;
                    }

                    if(nr == tr && nc == tc) {
                        break;

                    } else if(nr == r2 && nc == c2) { // 이동 경로에 기물이 있는지 확인
                        flag = false;
                        break;
                    }

                    // 다음 한 칸 이동
                    nr += dr[i] / Math.abs(dr[i]);
                    nc += dc[i] / Math.abs(dc[i]);
                }

                if (flag && visited[nr][nc] != 1){
                    q.add(new int[] {nr, nc, cur[2]+1});
                    visited[nr][nc] = 1;
                }
            }
        }
        System.out.println(answer);
    }
}
