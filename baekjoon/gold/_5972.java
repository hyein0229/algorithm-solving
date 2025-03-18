package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 5972 택배 배송
난이도: Gold 5
알고리즘: 다익스트라, 최단 경로
풀이 방법: 다익스트라 -> 특정 하나의 정점에서 다른 모든 정점으로 가는 최단 경로를 알 수 있음
        현재 문제는 1에서 출발한다고 하였으으로 N으로 가는 최소 비용을 다익스트라를 이용해 구할 수 있다.
 */
public class _5972 {

    static class Edge {
        int v;
        int cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static int N, M;
    static Map<Integer, List<Edge>> map = new HashMap<>();
    static int[] dist; // 최단거리 배열
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        visited = new boolean[N+1];
        dist = new int[N+1];

        for(int i=1; i<=N; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);
            map.get(a).add(new Edge(b, c));
            map.get(b).add(new Edge(a, c));
        }

        // 최단거리 배열 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0; // 출발 노드는 0으로

        dijst();
        System.out.println(dist[N]);

    }

    // 다익스트라
    public static void dijst() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Edge(1, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(visited[cur.v]) continue;
            visited[cur.v] = true;

            for(Edge next : map.get(cur.v)) {
                if(dist[next.v] > dist[cur.v] + next.cost) {
                    dist[next.v] = dist[cur.v] + next.cost;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }
    }
}
