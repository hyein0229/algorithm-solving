package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 문제: 22254 공정 컨설턴트 호석
 * 난이도: Gold 3
 * 알고리즘: 이분 탐색, 우선순위 큐
 * 풀이 방법: 최소 공정 라인 개수를 구하기 위해 공정 라인 수를 정해놓고 해당 개수일 때 시뮬레이션을 돌려 가능한 지 확인해본다.
 *          이때 공정 라인 개수를 완전 탐색이 아닌 이분 탐색을 적용하여 시간복잡도를 줄인다.
 *          시뮬레이션의 경우 최소힙 pq 에 공정 라인 개수만큼 초기 제작 시간 총합 0을 삽입하고 1번부터 선물을 배정할 때 가장 시간이 작은
 *          공정 라인의 시간에 더해 다시 삽입하는 방식으로 다 배정한 다음, 가장 마지막에 제작이 완료되는 공정 라인의 시간을 기준으로 X와 비교하여
 *          X 이하이면 가능하다고 판단한다.
 */
public class _22254 {

    static int N, X;
    static int[] presents;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        X = Integer.parseInt(line[1]);
        presents = new int[N+1];

        line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            presents[i+1] = Integer.parseInt(line[i]);
        }

        int left = 1;
        int right = N;
        while(left <= right) {
            int mid = (left + right) / 2;
            boolean possible = simulate(mid);
            if(possible) {
                right = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean simulate(int limit) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 사용 시간이 가장 적음
        for(int i=0; i<limit; i++) {
            pq.add(0);
        }

        for(int i=0; i<N; i++) {
            int target = pq.poll(); // 가장 제작 시간의 총합이 적은 공정라인의 시간
            pq.add(presents[i+1] + target);
        }

        // 가장 마지막에 끝나는 공정라인 시간
        int maxTime = Integer.MIN_VALUE;
        while(!pq.isEmpty()) {
            maxTime = Math.max(maxTime, pq.poll());
        }

        if(maxTime > X) {
            return false;
        }
        return true;
    }
}
