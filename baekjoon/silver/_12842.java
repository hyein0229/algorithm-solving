package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 12842 튀김 소보루
난이도: Silver 1
알고리즘: 구현, 브루트포스, 이분탐색
풀이 방법: 우선순위 큐를 사용하여 풀었다. 먼저, 빨리 먹는 순, 작은 번호 순을 우선 기준으로 하는 우선순위 큐를
        만든다. 그 다음, 큐에 사람들을 작은 번호 순대로 집은 빵을 집어 넣는다. 주의할 것은 이때도 카운트를 해서
        중간에 s가 된다면 빵 집는 것을 멈추어야 한다.
        빵을 다 집었다면 그 다음 부턴 빨리 먹는 순대로 큐에서 꺼낸다. 이때, 동시에 집는 경우를 고려해야 하므로
        같은 시간에 다 먹는 것은 모두 다 빼주어야 한다.
        큐에서 빵을 뺀 후엔 다시 빵을 집으므로 큐에 사람 번호와 다 먹게 되는 시간인 (현재 시간 + 먹는 시간 t) 을
        다시 집어 넣는다. 빵을 뺼 떄마다 카운트하여 s가 될 때까지 반복한다.
 */
public class _12842 {

    static class Bread implements Comparable<Bread>{
        int person;
        int time;

        public Bread(int person, int time) {
            this.person = person;
            this.time = time;
        }

        @Override
        public int compareTo(Bread o) {
            if(this.time == o.time){ // 동시에 다 먹으면
                return this.person - o.person; // 작은 번호 순
            }
            return this.time - o.time; // 빨리 먹는 시간 순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int s = Integer.parseInt(line[1]);
        int m = Integer.parseInt(br.readLine());
        int[] people = new int[m+1];
        int answer = -1;

        for(int i=1; i<=m; i++) {
            people[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Bread> pq = new PriorityQueue<>();

        int cnt = n; // 처음 소보루의 개수
        for(int i=1; i<=m; i++) {
            pq.add(new Bread(i, people[i]));
            cnt--;
            if(cnt == s) {
                answer = i;
                break;
            }
        }

        if(answer == -1) {
            int num = -1;
            while(cnt > s) {
                Bread cur = pq.poll();
                num = cur.person;
                int finishedTime = cur.time;
                pq.add(new Bread(num, finishedTime + people[num]));
                cnt--;
                // 동시에 빵을 다 먹은 사람들을 작은 번호 순대로 빵 집기
                while(cnt > s && finishedTime == pq.peek().time) {
                    cur = pq.poll();
                    num = cur.person;
                    pq.add(new Bread(num, finishedTime + people[num]));
                    cnt--;
                }
            }
            answer = num;
        }
        System.out.println(answer);
    }
}
