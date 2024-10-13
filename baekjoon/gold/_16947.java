package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 16947 서울 지하철 2호선
난이도: Gold 3
알고리즘: 그래프, dfs
풀이 방법: 순환선까지의 거리를 저장할 dist 1차원 배열 선언,
        먼저 각 임의 시작점에 대해 dfs 를 하여 다시 시작점으로 돌아오는 경우를 찾아 순환선인 경우 dist[i] = 0 으로 체크
        (주의, 1-2, 2-1 다시 오는 경우를 방지하여 거리 d >= 3 (prev 의 역할) 으로 조건 처리해주어야 함)
        순환선을 찾은 뒤 dist = INF 인 경우, 즉 아직 경로를 찾지 못한 정점에 대해 dfs 를 하여 dist = 0 으로 체크된
        정점, 즉 순환선에 포함되는 정점까지의 거리를 갱신해준다.
 */
public class _16947 {

    static int N;
    static ArrayList<Integer>[] graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        // 순환선 찾기
        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1];
            findCircular(i, i, 0);
            if(dist[i] == 0) break;
        }

        for(int i=1; i<=N; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                visited = new boolean[N+1];
                dfs(i, 0);
            }
        }
        for(int i=1; i<=N; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    public static boolean dfs(int cur, int d) {
        visited[cur] = true;
        if(dist[cur] != Integer.MAX_VALUE) {
            return true;
        }

        for(Integer b : graph[cur]) {
            if(visited[b]) continue;
            if(dfs(b, d+1)) {
                dist[cur] = dist[b]+1;
                return true;
            }
        }
        return false;
    }

    public static boolean findCircular(int start, int cur, int d) {
        visited[cur] = true;
        // 시작점으로 다시 돌아온 경우
        if(start == cur && d >= 3) {
            dist[cur] = 0; // 순환선 안에 있음
            return true;
        }

        if(dist[cur] == 0) {
            return false;
        }

        // 현재 노드와 연결된 구간
        for(Integer b : graph[cur]) {
            if(start != b && visited[b]) continue;
            if(start == b && d < 2) continue;
            if(findCircular(start, b, d+1)) {
                dist[cur] = 0;
                return true;
            }
        }
        return false;
    }
}
