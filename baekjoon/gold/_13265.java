package baekjoon;

import java.io.*;
import java.util.*;

/*
문제: 13265 색칠하기
난이도: Gold 5
알고리즘: dfs, 그래프
풀이 방법: dfs를 이용하여 풀이. 각 정점마다 개별적으로 dfs를 수행하여 직선으로 연결된
        정점들을 반대 색으로 색칠하는 데, 이때 만약 해당 정점이 이미 같은 색으로 색칠되어 있다면
        2가지 색으로 색칠이 불가능한 경우이므로 possible 을 false 로 바꾸고 탐색을 종료한다.
 */
public class _13265 {

    static int[] circles;
    static List<List<Integer>> graph;
    static boolean possible;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            // 초기화
            circles = new int[n+1];
            graph = new ArrayList<>();
            for(int j=0; j<=n+1; j++) {
                graph.add(new ArrayList<>());
            }
            // 연결된 직선 입력
            for(int j=0; j<m; j++) {
                line = br.readLine().split(" ");
                int c1 = Integer.parseInt(line[0]);
                int c2 = Integer.parseInt(line[1]);
                graph.get(c1).add(c2);
                graph.get(c2).add(c1);
            }
            // dfs 를 통해 색칠하기
            possible = true;
            Arrays.fill(circles, -1); // 색칠이 안된 상태 -1로 초기화
            for(int j=1; j<=n; j++) {
                if(circles[j] == -1) {
                    dfs(j, 0);
                }
            }

            if(possible) {
                System.out.println("possible");
            } else {
                System.out.println("impossible");
            }
        }
    }

    public static void dfs(int cur, int color) {
        circles[cur] = color; // 현재 색으로 색칠
        for(int next : graph.get(cur)) { // 연결된 정점 탐색
            // 이미 같은 색으로 칠해져 있으면 불가능한 경우
            if(circles[next] == color) {
                possible = false;
                return;
            }
            // 색칠이 안되어 있으면 반대 색으로 색칠
            if(circles[next] == -1) {
                dfs(next, (color+1) % 2);
            }
        }
    }
}
