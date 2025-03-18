package baekjoon;

import java.io.*;
import java.util.*;
/*;
문제: 1446 지름길
난이도: Silver 1
알고리즘: dp, 다익스트라
풀이 방법: 먼저, 입력 받은 지름길를 우선순위 큐에 삽입하여 시작점을 기준으로 오름차순 정렬한다. 정주행이기 때문
        그 다음, dist[i] 위치 i까지의 최단 이동 거리를 저장할 배열을 선언하고 Integer.MAX_VALUE 로 초기화한다.
        현재 위치 0부터 D까지 순회하면서 dist 값을 갱신하는 데, 이때 현재 위치와 우선순위 큐의 peek 한 지름길의
        start (시작점) 이 같다면, dist[end] = min(dist[end], dist[start] + 지름길 길이) 로 비교하여 dist[end]을 갱신한다.
        그리고 dist[i+1] = min(dist[i+1], dist[i] + 1) 을 하여 다음 위치의 값을 갱신해주고
        현재 위치를 +1 하면서 D까지 이동시켜 주면 된다.
 */
public class _1446 {

    static class Path implements Comparable<Path>{
        int start;
        int end;
        int len;

        public Path(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }

        @Override
        public int compareTo(Path o1) {
            if(this.start == o1.start) {
                return this.end - o1.end;
            }
            return this.start - o1.start; // 시작점을 기준으로 오름차순
        }
    }

    static int N, D;
    static PriorityQueue<Path> pq;
    static int[] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        D = Integer.parseInt(line[1]);
        dist = new int[D+1];
        pq = new PriorityQueue<>();

        // 지름길 입력
        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int len = Integer.parseInt(line[2]);
            // 지름길 종료지점이 고속도로 거리를 넘거나 지름길이 더 길 때는 삽입하지 않음
            if(end > D || end - start <= len)
                continue;
            pq.add(new Path(start, end, len));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        int cur = 0; // 현재 위치
        dist[0] = 0;

        while(cur < D) {
            // 현재 위치와 시작점이 같은 지름길이 있다면 탐색
            while(!pq.isEmpty() && cur == pq.peek().start) {
                Path path = pq.poll();
                dist[path.end] = Math.min(dist[path.end], dist[cur] + path.len);
            }
            dist[cur+1] = Math.min(dist[cur+1], dist[cur] + 1);
            cur++;
        }
        System.out.println(dist[D]);
    }
}
