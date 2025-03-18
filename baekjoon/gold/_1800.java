package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1800 인터넷 설치
난이도: Gold 1
알고리즘: 다익스트라
풀이 방법: 다익스트라 사용, 대신 공짜로 설치한 횟수에 따라 현재까지의 최소의 돈을 나누어 저장하기 위해
        dist 와 visited 배열을 2차원으로 선언하여 사용
        공짜로 연결한 횟수가 K보다 작은 경우엔 현재 선을 공짜로 연결하는 경우까지 추가하여 탐색해야 함
 */
public class _1800 {

    static class Node implements Comparable<Node> {
        int index;
        int maxCost;
        int kCnt;

        public Node(int index, int maxCost, int kCnt) {
            this.index = index;
            this.maxCost = maxCost;
            this.kCnt = kCnt;
        }

        @Override
        public int compareTo(Node o) {
            if(this.maxCost == o.maxCost) {
                return this.kCnt - o.kCnt;
            }
            return this.maxCost - o.maxCost;
        }
    }

    static int N, P, K;
    static boolean[][] check;
    static ArrayList<int[]>[] link;
    static int[][] dist;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        P = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);
        link = new ArrayList[N+1];
        dist = new int[N+1][K+1];
        check = new boolean[N+1][K+1];

        for(int i=1; i<=N; i++) {
            link[i] = new ArrayList<int[]>();
        }

        for(int i=0; i<P; i++) {
            line = br.readLine().split(" ");
            int c1 = Integer.parseInt(line[0]);
            int c2 = Integer.parseInt(line[1]);
            int cost = Integer.parseInt(line[2]);
            link[c1].add(new int[]{c2, cost});
            link[c2].add(new int[]{c1, cost});
        }

        dijkstra();
        if(ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    public static void dijkstra() {
        for(int i=2; i<=N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.index;
            int curCost = cur.maxCost;
            int kCnt = cur.kCnt; // 공짜 쿠폰 사용 횟수

            if(check[curIdx][kCnt]) continue;
            check[curIdx][kCnt] = true;

            for(int[] next : link[curIdx]) {
                int nextIdx = next[0];
                int nextCost = next[1];
                int nextMax = Math.max(curCost, nextCost);

                if(nextMax < dist[nextIdx][kCnt]) {
                    dist[nextIdx][kCnt] = nextMax;
                    pq.add(new Node(nextIdx, nextMax, kCnt));
                }

                // 공짜 쿠폰 사용
                if(kCnt < K) {
                    if(curCost < dist[nextIdx][kCnt+1]) {
                        dist[nextIdx][kCnt+1] = curCost;
                        pq.add(new Node(nextIdx, curCost, kCnt+1));
                    }
                }
            }
        }
        ans = Math.min(ans, dist[N][K]);
    }
}
