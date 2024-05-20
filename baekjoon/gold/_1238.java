package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1238 파티
난이도: Gold 3
알고리즘: 다익스트라
풀이 방법: 단방향 경로를 map 에 저장해 준 후 각 학생들이 사는 N개의 마을을 순서대로 출발점으로
        지정하여 다익스트라를 수행하여 최단 시간 비용을 cost 배열에 저장한다.
        따라서, X 마을까지의 최단 거리는 각 다익스트라 수행 후 cost[X] 가 된다.
        마을로 간 후 돌아오는 최단 거리를 구하기 위해서 X를 출발점으로 하여 다익스트라를 한번 더
        수행한 후 위에서 구한 가는 최단 거리 + 오는 최단 거리를 합한 수의 최댓값을 구하면 정답이 된다.
 */
public class _1238 {

    static Map<Integer, List<int[]>> map = new HashMap<>();
    static int[] cost;
    static int N, M, X;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        X = Integer.parseInt(line[2]);
        cost = new int[N+1];

        for(int i=1; i<=N; i++) {
            map.put(i, new ArrayList<>());
        }

        // 도로 입력
        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int t = Integer.parseInt(line[2]);
            map.get(a).add(new int[]{b, t}); // a -> b 단방향
        }
        // N개의 마을을 각각 출발점으로 하여 다익스트라 수행
        int[] minCost = new int[N+1];
        for(int i=1; i<=N; i++) {
            Arrays.fill(cost, Integer.MAX_VALUE);
            minCost[i] = dijst(i); // X 까지의 최단 시간 구하기
        }
        // X에서 각 마을로 돌아가는 최단 시간 구하기
        Arrays.fill(cost, Integer.MAX_VALUE);
        dijst(X);

        // 가는 시간 + 오는 시간의 최대 구하기
        for(int i=1; i<=N; i++) {
            ans = Math.max(minCost[i] + cost[i], ans);
        }
        System.out.println(ans);
    }

    public static int dijst(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 시간 오름차순 정렬
        pq.add(new int[]{start, 0}); // 현재 위치, 시간 비용
        cost[start] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int curCost = cur[1];
            // v 에서 가는 도로 탐색
            for(int[] next : map.get(v)) {
                // v 를 거쳐 가는 것이 더 비용이 적게 든다면
                if(cost[next[0]] > curCost + next[1]) {
                    cost[next[0]] = next[1] + curCost;
                    pq.add(new int[]{next[0], cost[next[0]]});
                }
            }
        }
        return cost[X];
    }
}
