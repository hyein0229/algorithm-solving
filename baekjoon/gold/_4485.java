package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 4485 녹색 옷 입은 애가 젤다지?
난이도: Gold 4
알고리즘: 다익스트라
풀이 방법: (0, 0) 에서 출발하여 (N-1, N-1) 에 도착할 때 최소 루피 비용을 구하면 되므로
        다익스트라를 사용하여 해결하였다.
        일반 큐를 사용해도 답이 나오지만 우선순위 큐를 사용하는 이유는???
        우선순위 큐를 사용하여 최소 비용 정렬을 하면 최소 비용부터 추출된다.
        일반 큐는 경우에 따라 중복 갱신이 많아져 이전 계산한 작업이 무의미한 값이 되지만 우선순위 큐는
        최소 비용인 것부터 탐색하므로 이전의 계산이 최소값임이 보장되어 시간 상의 이점이 발생한다.
 */
public class _4485 {

    static int N;
    static int[][] cave;
    static int[][] cost;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t=1;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            cave = new int[N][N];
            cost = new int[N][N];

            // 동굴 입력
            for(int i=0; i<N; i++) {
                String[] line = br.readLine().split(" ");
                for(int j=0; j<N; j++) {
                    cave[i][j] = Integer.parseInt(line[j]);
                }
                Arrays.fill(cost[i], Integer.MAX_VALUE);
            }
            cost[0][0] = cave[0][0];

            dijkstra();
            System.out.println("Problem " +t + ": " + cost[N-1][N-1]);
            t++;
        }
    }

    public static void dijkstra() {
        // 우선순위 큐 이용하여 최소 비용으로 정렬
        PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2) -> o1[2] - o2[2]);
        q.add(new int[]{0, 0, cost[0][0]});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int curCost = cur[2];

            for(int d=0; d<4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;

                if(curCost + cave[nx][ny] < cost[nx][ny]) {
                    cost[nx][ny] = curCost + cave[nx][ny];
                    q.add(new int[]{nx, ny, cost[nx][ny]});
                }
            }
        }
    }
}
