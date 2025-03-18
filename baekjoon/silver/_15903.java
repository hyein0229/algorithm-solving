package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 15903 카드 합체 놀이
난이도: Silver 1
알고리즘: 그리디
풀이 방법: 최종적으로 총합이 가장 작으려면 그때그때 카드에 적혀져 있는 수가 가장 작은 것 두개를 더하면 된다.
        따라서 우선순위 큐 최소힙을 사용하여 카드의 적힌 수가 작은 순대로 정렬하도록 하고,
        큐에서 두 장을 뽑아 더한 수를 두 장 다시 삽입하는 과정을 m 번 시행한다.
        총합은 m번 시행하는 과정에서 뽑은 것을 빼고 더해지는 수를 더하여 동시에 계산하도록 하였다.
 */
public class _15903 {

    public static void main(String[] args) throws Exception {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            long card = Integer.parseInt(st.nextToken());
            sum += card;
            pq.add(card);
        }

        // 카드 합체 m번 시행
        for(int i=0; i<m; i++) {
            long x = pq.poll();
            long y = pq.poll();
            pq.add(x + y);
            pq.add(x + y);
            sum += x + y;
        }
        System.out.println(sum);
    }
}
