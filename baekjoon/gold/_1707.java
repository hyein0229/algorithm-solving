package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1707 이분 그래프
난이도: Gold 4
알고리즘: 그래프, dfs, 이분 그래프
풀이 방법: 이분 그래프에 대한 이해를 하고 풀어야 한다. 이분 그래프는 인접한, 즉 이어진 정점끼리
        서로 다른 색으로 칠하여 모든 정점을 두 가지의 색으로만 칠할 수 있는 그래프이다. 즉, 모든 정점이
        두 가지 색의 그룹으로 나누어 지고, 같은 색의 그룹 내에선 서로 인접하지 않아야 하는 그래프이다.
        따라서, 먼저 인접한 정점을 저장해주기 위해 리스트 배열을 생성하여 저장해주었다. 간선이 입력될 때 양방향 저장을 해주어야 한다.
        인접한 정점을 따라 dfs 하면서 방문하지 않는 정점이면 서로 다른 색 1, 2로 칠해준다.
        모두 방문을 한 후엔 각 정점마다 인접한 정점들을 보면서 서로 같은 색으로 칠해져 있는지 확인한다.
        같은 색으로 칠해져 있다면 이분 그래프가 아니므로 NO를 출력한다.
 */
public class _1707 {

    static int K, V, E;
    static ArrayList<Integer>[] arr;
    static int[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        for(int i=0; i<K; i++) {
            String[] line = br.readLine().split(" ");
            V = Integer.parseInt(line[0]);
            E = Integer.parseInt(line[1]);
            visited = new int[V+1];
            arr = new ArrayList[V+1];
            for(int j=1; j<=V; j++) {
                arr[j] = new ArrayList<>();
            }
            // 간선 입력
            for(int j=0; j<E; j++) {
                line = br.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                arr[u].add(v);
                arr[v].add(u);
            }

            for(int j=1; j<=V; j++) {
                if(visited[j] == 0) {
                    dfs(j, 1);
                }
            }

            boolean check = true;
            for(int j=1; j<=V; j++) {
                for(int k : arr[j]) {
                    if(visited[j] == visited[k]) {
                        check = false;
                        break;
                    }
                }
                if(!check) break;
            }
            if(check) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static void dfs(int u, int groupNum) {

        visited[u] = groupNum;
        for(int v : arr[u]) {
            if(visited[v] == 0) {
                dfs(v, 3 - groupNum);
            }
        }
    }
}
