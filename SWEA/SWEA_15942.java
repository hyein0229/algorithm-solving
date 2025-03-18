package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
문제: 15942. 외계인 침공
난이도: D4
알고리즘: 그리디, 우선순위 큐
풀이 방법: 인구 수가 큰 행성부터 정복 => 그 이하의 인구의 행성은 무조건 정복 가능
        따라서, 그리디 => 현재 함선 수 이하에서 정복 가능한 행성 중 가장 인구 수가 큰 행성을 선택해 정복
        현재 함선 수 s 를 기준으로 m이 작은 행성, m이 큰 행성을 따로 관리해줄 우선순위 큐 2개 나누어 관리
        작은 행성 큐를 최대 힙으로 하면 항상 정복 가능한 행성 중 가장 큰 것을 조회할 수 있음
        이때, 행성을 정복 후 '바로 동원'하여 함선의 수를 갱신해주는 것이 시간초과 해결의 핵심!!
        어차피 무조건 큰 인구수의 행성부터 동원해주는 것이 최소 동원을 지킬 수 있으므로,
        따라서 정복 시에 이미 인구 수가 큰 행성부터 정복하므로 바로 동원해주어도 문제 없음
 */
public class SWEA_15942 {

    static int n, k;
    static PriorityQueue<Integer> smallerPq;
    static PriorityQueue<Integer> biggerPq;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] line;

        for (int test_case = 1; test_case <= T; test_case++) {
            line = br.readLine().split(" ");
            n = Integer.parseInt(line[0]);
            k = Integer.parseInt(line[1]);
            smallerPq = new PriorityQueue<>(Collections.reverseOrder());
            biggerPq = new PriorityQueue<>();
            ans = 0;

            long total = 0;
            line = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(line[i]);
                total += num; // 모든 행성의 합 구하기
                if(k < num) {
                    biggerPq.add(num);
                } else {
                    smallerPq.add(num);
                }
            }

            // 처음부터 정복 가능한 행성이 하나도 없다면 -1
            if(k == 0 || smallerPq.isEmpty()) {
                ans = -1;
                System.out.println("#" + test_case + " " + ans);
                continue;
            }

            int conquerCnt = 0; // 정복 횟수 카운트
            long shipCnt = k; // 외계인이 현재 가진 함선
            while (conquerCnt < n) {
                // 현재 모든 행성 인구 합이 현재 함선의 수보다 작다면 정복하는 데 동원 필요 없음
                if(shipCnt >= total) {
                    break;
                }

                // 현재 함선 수 기준으로 인구가 작은 행성, 큰 행성 나눔
                while(!smallerPq.isEmpty() && smallerPq.peek() > shipCnt) {
                    biggerPq.add(smallerPq.poll());
                }

                while(!biggerPq.isEmpty() && biggerPq.peek() <= shipCnt) {
                    smallerPq.add(biggerPq.poll());
                }

                // 정복 가능한 행성이 없는 경우 모든 행성 정복이 불가, 바로 종료
                if (smallerPq.isEmpty()) {
                    ans = -1;
                    break;
                }

                long target = smallerPq.poll(); // 정복할 수 있는 행성 중 가장 인구가 큰 행성
                shipCnt += target; // 침략과 바로 동원 후 함선 수, 어차피 동원해야 됨
                total -= target; // 정복한 행성의 인구만큼 인구 총합에서 감소
                conquerCnt++;
                ans++;
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }
}
