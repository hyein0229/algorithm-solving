package baekjoon;

import java.io.*;
/*
문제: 1149 RGB 거리
난이도: Silver 1
알고리즘: 다이나믹 프로그래밍
풀이 방법: dp bottom up 방식으로 푼다. i번 집을 칠할 수 있는 경우가 빨강, 초록, 파랑 3가지 경우이므로
        각 경우마다 dp 값을 저장해주기 위해 dp 배열을 2차원 배열로 선언하여 3가지 경우를 따로 저장할 수 있도록 한다.
        dp 점화식: i번째 집이 빨강인 경우의 dp 값은 i-1번째 집이 초록이거나 파랑인 경우의 dp 값 중 최솟값에
        i가 빨강으로 칠할 때의 비용을 더하면 된다. 정답은 dp[N] 이 빨강, 초록, 파랑인 경우 중 최솟값이 된다.
 */
public class _1149 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N+1][3];

        for(int i=1; i<=N; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=0; j<3; j++) {
                cost[i][j] = Integer.parseInt(line[j]);
            }
        }

        int[][] dp = new int[N+1][3];
        for(int i=1; i<=N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
        }
        System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
    }
}
