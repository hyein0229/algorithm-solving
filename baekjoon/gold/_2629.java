package baekjoon;

import java.io.*;
/*
문제: 2629 양팔저울
난이도: Gold 3
알고리즘: dp, 냅색
풀이 방법: dp[i][j] = i번 추까지 사용해서 j 무게를 만들 수 있는 지의 여부를 저장
        dp[i-1][j] 가 true 라면 dp[i][j]도 true, i번의 추를 사용하지 않는 경우가 있으므로
        dp[i-1][j] 가 true 라면 세 가지 경우로 j 무게에 연산하여 새로운 무게를 만들 수 있음
        1. i를 사용하지 않는 경우: dp[i][j] = true
        2. i를 구슬이 없는 쪽에 올리는 경우: dp[i][j+weight[i]] = true
        3. i를 구슬이 있는 쪽에 올리는 경우: (왼쪽, 오른쪽 합의 차 = 구슬의 무게)가 되므로 abs(j - weight[i]) 무게를 만드는 것과 같음
 */
public class _2629 {

    static int N, M;
    static int[] weights;
    static boolean[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        StringBuilder sb = new StringBuilder();
        dp = new boolean[31][15001];

        N = Integer.parseInt(br.readLine());
        weights = new int[N+1];
        line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            weights[i+1] = Integer.parseInt(line[i]);
        }

        // dp[i][j] => i개의 추까지 보았을 때 j를 만들 수 있는 지 여부
        dp[0][0] = true;
        for(int i=1; i<=N; i++) {
            // 만들 수 있는 무게
            for(int j=0; j<=15000; j++) {
                if(dp[i-1][j]) {
                    dp[i][j] = true; // 현재 추 사용 x
                    if(j + weights[i] <= 15000)
                        dp[i][j + weights[i]] = true; // 구슬 무게 = 추 무게의 합
//                    if(j - weights[i] >= 0)
//                        dp[i][j-weights[i]] = true;
                    dp[i][Math.abs(j - weights[i])] = true; // 구슬 무게 = 왼쪽, 오른쪽 추 무게 합의 차
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        line = br.readLine().split(" ");
        for(int i=0; i<M; i++) {
            int bead = Integer.parseInt(line[i]);
            if(bead > 15000 || !dp[N][bead]) {
                sb.append("N").append(" ");
            } else  {
                sb.append("Y").append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
