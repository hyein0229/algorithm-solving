package baekjoon;

import java.io.*;
/*
문제: 15989 1,2,3 더하기 4
난이도: Gold 5
알고리즘: 다이나믹 프로그래밍
풀이 방법: 처음에 그냥 dp[i-1] + dp[i-2] + dp[i-3] 으로 누적시켰으나, 이런 경우엔
        순서만 다른 것, 즉 중복되는 조합을 제외시킬 수 없다. 따라서, 2차원 배열로 선언하여
        1로 끝나는 경우, 2로 끝나는 경우, 3으로 끝나는 경우의 dp 값을 각각 구해주어야 한다.
        dp[n][m] 이면 n은 만들 숫자, m은 마지막 수를 의미한다.
        j-k에 k를 더해주면 j가 된다는 것을 이용,
        이때, dp[j-k]에서 마지막 수가 k 이하인 경우의 dp 값만 더해주어야 한다.
        dp[j][1] = dp[j-1][1]
        dp[j][2] = dp[j-2][1] + dp[j-2][2]
        dp[j][3] = dp[j-3][1] + dp[j-3][2] + dp[j-3][3]
 */
public class _15989 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] dp = new int[10001][4]; // 1, 2, 3 각각으로 끝나는 경우를 2차원으로 따로 저장
        // 초기화
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for(int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            for(int j=4; j<=n; j++) {
                dp[j][1] = dp[j-1][1];
                dp[j][2] = dp[j-2][1] + dp[j-2][2];
                dp[j][3] = dp[j-3][1] + dp[j-3][2] + dp[j-3][3];
            }
            System.out.println(dp[n][1] + dp[n][2] + dp[n][3]);
        }
    }
}
