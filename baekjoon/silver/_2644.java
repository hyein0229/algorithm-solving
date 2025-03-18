package baekjoon;

import java.io.*;
import java.util.*;

/*
문제: 2644 촌수계산
난이도: Silver 2
알고리즘: 그래프, dfs/bfs
풀이 방법: BFS를 이용하여 풀었다. 먼저, map 에 사람을 key 로 하고 일촌관계 사람들의 리스트를 value 로 하여
        각 사람마다 일촌들을 기록한다. 촌수를 계산해야하는 두 사람 a, b 중 a부터 시작하여 번호, a와의 촌수를
        큐에 배열로 집어넣고, 큐에 있는 사람들을 순서대로 꺼내어 일촌들을 똑같이 탐색하는데 이때 촌수를 현재 촌수에 +1을 하여 집어넣는다.
        이것을 반복하다가 큐에서 꺼낸 사람 번호가 b 가 나오면 현재까지의 촌수를 정답으로 출력한다.
 */
public class _2644 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int a = Integer.parseInt(line[0]);
        int b = Integer.parseInt(line[1]);
        int m = Integer.parseInt(br.readLine());
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=1; i<=n ;i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            // x와 y는 양방향 일촌 관계
            map.get(x).add(y);
            map.get(y).add(x);
        }

        int answer = -1;
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        q.add(new int[]{a, 0}); // 사람의 번호, 촌 수
        visited[a] = true;

        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int person = arr[0];
            int degree = arr[1]; // 촌 수
            if(person == b) {
                answer = degree;
                break;
            }
            // 일촌 관계인 사람들 탐색
            for(Integer num : map.get(person)) {
                // 방문하지 않은 사람이면 큐에 삽입
                if(!visited[num]) {
                    visited[num] = true;
                    q.add(new int[]{num, degree + 1});
                }
            }
        }
        System.out.println(answer);
    }
}
