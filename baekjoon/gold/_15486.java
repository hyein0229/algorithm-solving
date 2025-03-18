package baekjoon;

import java.io.*;
/*
문제: 15486 퇴사 2
난이도: Gold 5
알고리즘: 다이나믹 프로그래밍
풀이 방법: 날짜를 인덱스로 하는 dp 배열을 생성하여 i일까지의 최대 수익을 저장하도록 하였다.
        dp 배열은 N+2 크기로 생성한다. N일에서 하루 상담했을 때 받는 수익을 N+1 에 저장하기 위해서이다.
        지금까지의 최대 수익을 구하기 위해선 dp[i] 와 dp[i-1] 중 비교하여 최대 수익을 계속 갱신해준다.
        그 다음, i일의 상담을 시작하면 i+t일에 돈을 받으므로 i+t가 N+1을 넘지 않는다면
        현재의 dp[i+t] 와 dp[i]+t 중 최댓값으로 dp[i+t]을 갱신한다.
 */
public class _15486 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][2];

        for(int i=1; i<=N; i++) {
            String[] line = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(line[0]);
            arr[i][1] = Integer.parseInt(line[1]);
        }

        int[] dp = new int[N+2];
        for(int i=1; i<=N; i++) {
            int t = arr[i][0];
            int p = arr[i][1];
            dp[i] = Math.max(dp[i], dp[i-1]);
            if(i+t <= N+1) {
                dp[i+t] = Math.max(dp[i] + p, dp[i+t]);
            }
        }
        System.out.println(Math.max(dp[N], dp[N+1]));
    }
}
