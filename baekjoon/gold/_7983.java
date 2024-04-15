package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 7983 내일 할거야
난이도: Gold 5
알고리즘: 그리디
풀이 방법: 최대한 많이 놀아야 하므로 최대한 마감일에 딱 끝나는 방법으로 과제를 수행하도록 하면 된다.
        따라서, 먼저 최대힙 우선순위 큐를 사용하여 마감일이 가장 늦은 순으로 과제가 정렬되도록 한다.
        그리고, 오늘 날짜를 day 변수로 놓는다. MAX_VALUE 로 초기화
        그 다음, 큐에서 가장 마감일이 늦는 과제부터 뽑으면서 과제가 걸리는 시간 d를 빼주는데
        이때, 오늘 day 보다 마감일 t 가 더 작다면 day = t - d로 갱신하고
        만약, 오늘 day 가 더 작거나 같다면 day 에서 d를 뺀 값으로 갱신한다. (다음 과제 마감을 위해 지금 과제를 더 빨리 시작함을 의미)
 */
public class _7983 {

    public static class Task implements Comparable<Task>{
        int d;
        int t;

        public Task(int d, int t) {
            this.d = d;
            this.t = t;
        }

        @Override
        public int compareTo (Task o) {
            return o.t - this.t; // 마감 날짜 내림차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Task> pq = new PriorityQueue<>();
        // 과제 입력
        for(int i=0; i<n; i++) {
            String[] line = br.readLine().split(" ");
            int d = Integer.parseInt(line[0]);
            int t = Integer.parseInt(line[1]);
            pq.add(new Task(d, t));
        }

        int day = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            Task task = pq.poll();
            int d = task.d;
            int t = task.t;
            if(day > t) {
                day = t - d;
            } else {
                day -= d;
            }
        }
        System.out.println(day);
    }
}
