package baekjoon;

import java.io.*;
/*
문제: 1106 호텔
난이도: Gold 5
알고리즘: 다이나믹 프로그래밍
풀이방법: dp[i] 를 i명의 고객 수를 모을 때 드는 최소 비용이라고 설정하였다.
        이때, 주의할 점은 dp 배열의 크기를 C+100 으로 선언하여야 한다. 적어도 C명을 늘리기 위한 최소 비용이므로
        C보다 더 큰 값에서 최소 비용이 나올 수 있으며 입력되는 홍보비용은 100보다 작거나 같은 수만 입력되므로
        최대 길이를 C+100 으로 선언해주어야 한다.
        예) dp[C] = 50 이지만 dp[C-10] = 1, dp[C+50] = 2 이면 dp[C+40] = 3 이 최소 비용이 된다.

        N개의 홍보를 순회하면서 현재 홍보로 만들 수 있는 모든 고객 수에 대해 dp 값을 차례대로 갱신해준다.
        점화식은 dp[j] = Math.min(dp[j], dp[j - num] + cost)
 */
public class _1106 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int C = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);
        int[][] arr = new int[N][2];
        int[] dp = new int[C+100]; // C+100까지의 범위가 중요

        for(int i=0; i<C+100; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(line[0]); // 홍보 비용
            arr[i][1] = Integer.parseInt(line[1]); // 고객 수
        }

        dp[0] = 0;
        for(int i=0; i<N; i++) {
            int cost = arr[i][0];
            int num = arr[i][1];
            for(int j=num; j<C+100; j++) {
                if(dp[j-num] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - num] + cost);
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for(int i=C; i<C+100; i++) {
            minCost = Math.min(dp[i], minCost);
        }
        System.out.println(minCost);
    }
}
