package baekjoon;

import java.io.*;
/*
문제: 1912 연속합
난이도: Silver 2
알고리즘: 다이나믹 프로그래밍
풀이 방법: '연속된 수' 선택이고 음수가 포함된 것이 정답이 될 수 있다는 것을 주의해야 한다.
         단순히 합이 커져야 하므로 음수인 경우를 제외시키면 안된다.
         dp[i-1] + arr[i] 값과 arr[i] 값을 비교하여 최대 값을 dp[i]로 저장한다.
         전자는 현재까지 연속된 합에 현재 값을 더한 수이고 후자는 현재 값부터 다시 합을 시작하겠다는 의미이다.
         정답은 dp 값 중 가장 큰 값이 된다.
 */
public class _1912 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        String[] line = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        dp[0] = arr[0];
        int answer = dp[0];
        for(int i=1; i<n; i++) {
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);

    }
}
