package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17471 게리맨더링
난이도: Gold 4
알고리즘: 브루트포스, dfs, bfs, 그래프
풀이 방법: 모든 N개의 구역은 반드시 하나의 선거구 안에 들어가야 하므로 선거구1, 선거구2 로 나누었을 때
        선거구1에 포함되는가, 포함되지 않는가 두 가지의 선택지로 나눌 수 있다. 따라서, visited 배열을 만들어
        현재 구역을 선거구1에 포함하는 경우 true 로 체크한 후 다음 번호의 구역에 대해 dfs를 진행하고 그 다음엔
        포함하지 않는 경우인 false 로 바꾸어 dfs를 진행하여 두 가지 선택지에 대해 완전 탐색을 한다.
        dfs로 넘긴 인덱스 번호가 N을 넘어가면 visited 가 true 로 체크된 구역들을 선거구1로 묶고 나머지를 선거구2로 묶는다.
        그리고 두 선거구 안에서 bfs 를 하여 각 구역의 인접한 구역을 통해 모든 구역을 방문할 수 있는지를 확인하고
        모두 방문할 수 있다면 정답을 갱신한다.
 */
public class _17471 {
    static int N;
    static int[] people;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    // 17471
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N+1]; // 1~N 구역의 인구수
        visited = new boolean[N+1];

        String[] line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            people[i+1] = Integer.parseInt(line[i]);
        }
        // 각 구역마다 인접한 구역 저장
        for(int i=1; i<=N; i++) {
            line = br.readLine().split(" ");
            int num = Integer.parseInt(line[0]);
            List<Integer> list = new ArrayList<>();
            for(int j=1; j<num+1; j++) {
               list.add(Integer.parseInt(line[j]));
            }
            map.put(i, list);
        }

        gerryMandering(1);
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    public static void gerryMandering(int index) {
        if(index > N) {
            List<Integer> d1 = new ArrayList<>();
            List<Integer> d2 = new ArrayList<>();
            for(int i=1; i<=N; i++) {
                if(visited[i]){
                    d1.add(i);
                } else {
                    d2.add(i);
                }
            }

            if(check(d1) && check(d2)) {
                answer = Math.min(answer, getDiff());
            }
            return;
        }

        visited[index] = true;
        gerryMandering(index+1);
        visited[index] = false;
        gerryMandering(index+1);

    }

    public static boolean check(List<Integer> group) {
        if(group.isEmpty()) {
            return false;
        }
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(group.get(0));
        visited[group.get(0)] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int i=0; i<map.get(cur).size(); i++) {
                int next = map.get(cur).get(i);
                if(!visited[next] && group.contains(next)) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

        for(int i=0; i<group.size(); i++) {
            if(!visited[group.get(i)]) {
                return false;
            }
        }
        return true;
    }

    public static int getDiff() {
        int p1 = 0;
        int p2 = 0;
        for(int i=1; i<=N; i++) {
            if(visited[i]){
                p1 += people[i];
            } else {
                p2 += people[i];
            }
        }
        return Math.abs(p1 - p2);
    }
}
