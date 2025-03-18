package baekjoon;

import java.io.*;
/*
문제: 11052 카드 구매하기
난이도: Silver 1
알고리즘: 다이나믹 프로그래밍
풀이 방법: dp 배열에는 구매한 개수를 인덱스로 하여 각각 구매한 개수 별 지불한 최댓값을 저장하도록 설정하였다.
        즉, 2개 구매할 때 최댓값이 10원이라고 하면 dp[2] = 10이 된다.
        i번 카드팩을 구매했을 때, 구매하지 않았을 때의 최댓값을 비교해주어야 한다.
        따라서, 총 구매한 개수가 j일 때 점화식은 dp[j] = max(dp[j-i] + p[i], dp[j]) 가 된다.
 */
public class _11052 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] P = new int[N+1];

        // 카드팩 입력
        String[] line = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            P[i] = Integer.parseInt(line[i-1]);
        }

        for(int i=1; i<=N; i++) {
            for(int j=i; j<=N; j++) {
                dp[j] = Math.max(dp[j-i] + P[i], dp[j]);
            }
        }
        System.out.println(dp[N]);
    }
}
