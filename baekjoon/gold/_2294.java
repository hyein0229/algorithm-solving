package baekjoon;

import java.io.*;
/*
문제: 2294 동전 2
난이도: Gold 5
알고리즘: 다이나믹 프로그래밍
풀이 방법: dp 값을 설정하는 것이 어려웠다. 풀이 방법은 k 만큼의 dp 배열을 생성하고
        인덱스만큼의 가치를 만드는 데 필요한 동전의 최소 개수를 구하는 것이다.
        점화식은 dp[j] = min(dp[j], dp[j-coin[i]] + 1), i는 동전의 인덱스
        j의 가치를 만들 때 현재까지의 동전 최소 개수와 i번 동전을 추가하여 j를 만들었을 때 개수를 비교하는 것이다.
 */
public class _2294 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int[] coins = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k+1];
        for(int i=1; i<k+1; i++) {
            dp[i] = 100001;
        }

        for(int i=0; i<n; i++) {
            // j의 가치를 만들 수 있는 동전 최소 개수 구하기
            for(int j=coins[i]; j<=k; j++) {
                dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
            }
        }

        if(dp[k] == 100001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
