package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17070 파이프 옮기기 1
난이도: Gold 5
알고리즘: dp, bfs/dfs, 브루트포스
풀이 방법: dp 문제지만 bfs 와 비슷한 방식의 브루트포스로 해결하였다.
        원래 bfs 라면 방문 체크를 하지만 여기선 방문 체크를 하지 말아야 한다.
        이동할 수 있는 방법을 구하는 것이기 때문에 가로 -> 가로, 대각선 -> 가로로 같은 위치에 올 수 있음
        또한 방문 체크를 하지 않아도 파이프의 이동 방향을 고려하면 오는 길이 중복될 수 없다.
        따라서 방문 체크 없이 가능한 경우를 큐에 계속 삽입해준 후 끝점이 (N, N)일 때 카운트해주면 끝이다.
 */
public class _17070 {

    static class Pipe {
        int x;
        int y;
        int d;

        public Pipe(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    static int[][] house;
    static int[] dx = {0, 1, 1}; // 가로, 세로, 대각선
    static int[] dy = {1, 0, 1};
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        house = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            String[] row = br.readLine().split(" ");
            for(int j=1; j<=N; j++) {
                house[i][j] = Integer.parseInt(row[j-1]);
            }
        }
        // 최종 목적지가 1인 경우 이동시킬 수 있는 경우의 수가 없음
        if(house[N][N] == 1) {
            System.out.println(0);
            return;
        }

        Queue<Pipe> q = new LinkedList<>();
        q.add(new Pipe(1, 2 ,0)); // 끝점, 방향을 가진 파이프 삽입

        while(!q.isEmpty()) {
            Pipe pipe = q.poll();
            int x = pipe.x;
            int y = pipe.y;
            int d = pipe.d;
            if(x == N && y == N) { // 끝점이 (N, N)에 놓이면
                ans++;
                continue;
            }

            // 가로, 세로, 대각선 방향 파이프 이동
            for(int i=0; i<3; i++) {
                if((d == 0 && i == 1) || (d == 1 && i == 0)) { // 가로 <-> 세로는 전환 불가
                    continue;
                }

                // 이동 후 파이프의 시작점과 끝점
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 1 || nx > N || ny < 1 || ny > N) // 끝점의 범위 확인
                    continue;

                // 파이프가 벽과 겹치지 않는지 확인
                if(house[nx][ny] == 0) {
                    // 대각선인 경우 예외적으로 벽이 있는지 확인
                    if(i == 2 && (house[nx-1][ny] == 1 || house[nx][ny-1] == 1)) {
                        continue;
                    }
                    q.add(new Pipe(nx, ny , i));
                }
            }
        }
        System.out.println(ans);
    }
}
