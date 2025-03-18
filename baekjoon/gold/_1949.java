package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
문제: 1949 우수 마을
난이도: Gold 2
알고리즘: 트리 dp
풀이 방법: dp[node][good] -> 현재 노드가 우수 마을인 경우와 우수 마을이 아닌 경우의 최적해를 저장
       두 경우에 대해 인접한 자식 노드들을 dfs 를 사용하여 top-down 방식으로 탐색한다.
       현재 노드가 우수 마을이 아닌 경우, 자식 노드가 우수 마을인 경우와 우수 마을이 아닌 경우의 최댓값의 합이 최적해
       현재 노드가 우수 마을인 경우, 자식 노드가 모두 우수 마을이 아닌 경우의 총합이 최적해
       dp[node][0] = sum(max(dp[node][0], dp[node][1]))
       dp[node][1] = sum(dp[node][0])
 */
public class _1949 {

    static int N;
    static int[] arr; // 마을 주민 수
    static ArrayList<Integer>[] graph;
    static int ans;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] line;
        graph = new ArrayList[N+1];
        dp = new int[N+1][2];
        arr = new int[N+1];
        ans = 0;

        line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i+1] = Integer.parseInt(line[i]);
            graph[i+1] = new ArrayList();
        }

        for(int i=0; i<N-1; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);
        ans = Math.max(dp[1][0], dp[1][1]);
//        for(int i=1; i<=N; i++) {
//            System.out.println("node = " + i + " " + dp[i][0] + " " + dp[i][1]);
//        }
        System.out.println(ans);
    }

    public static void dfs(int node, int parent) {
        for(int next : graph[node]) {
            if(next == parent)
                continue;
            dfs(next, node);
            dp[node][0] += Math.max(dp[next][0], dp[next][1]);
            dp[node][1] += dp[next][0];
        }
        dp[node][1] += arr[node];
    }
}
