package baekjoon;

import java.io.*;
/*
문제: 9465 스티커
난이도: Silver 1
알고리즘: 다이나믹 프로그래밍
풀이 방법: dp[i][j] = i행 j열까지 스티커를 떼었을 때 얻는 최대 점수로 설정한다.
        스티커를 떼면 상하좌우 인접한 스티커의 점수는 얻지 못한다
        따라서, 왼쪽 대각선 j-1열의 스티커와 왼쪽 대각선의 왼쪽 j-2열의 스티커 점수를 비교하여
        둘 중 최댓값에 자기 자신을 더하면 dp값을 구할 수 있다.
        0행 또는 1행 둘 중 하나만 스티커를 떼어낼 수 있으므로 각각 따로 dp 값을 구해주고 정답은
        dp[0][n] 과 dp[1][n] 값 중 최대값을 출력해주면 된다.
 */
public class _9465 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][n+1];
            int[][] dp = new int[2][n+1];
            // 스티커 점수 입력
            for(int j=0; j<2; j++) {
                String[] line = br.readLine().split(" ");
                for(int k=1; k<=n; k++) {
                    stickers[j][k] = Integer.parseInt(line[k-1]);
                }
            }
            // 첫번째 열은 자기 자신이 최대
            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];
            // 왼쪽 대각선, 왼쪽 대각선의 왼쪽 둘 중 최댓값 비교하여 더함
            for(int j=2; j<=n; j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + stickers[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + stickers[1][j];
            }
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
