package baekjoon;

import java.io.*;
/*
문제: 11660 구간 합 구하기 5
난이도: Silver 1
알고리즘: 다이나믹 프로그래밍
풀이 방법: 누적합을 이용한다. dp 에 구간 별 누적합을 저장해놓고 부분 누적합을 이용하여 전체의 누적합을 구하는 것이다.
       dp 점화식은 dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + A[i][j] 이다.
       이때 dp 에 저장되는 누적합 값은 구간 (1, 1) 부터 (i, j)까지 더한 것이다.
       따라서 구간합은 왼쪽 칸인 (i, j-1) 까지의 누적합 (세로 직사각형) 과 윗칸인 (i-1, j) 까지의 누적합(가로 직사각형) 에
       중복되는 (i-1, j-1) 까지의 누적합 (겹치는 사각형 구역) 빼주고 자기 자신 칸(A[i][j])을 더하여 구할 수 있다.
 */
public class _11660 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int[][] dp = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            line = br.readLine().split(" ");
            for(int j=1; j<=N; j++) {
                dp[i][j] = Integer.parseInt(line[j-1]) + dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1];
            }
        }

        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int x2 = Integer.parseInt(line[2]);
            int y2 = Integer.parseInt(line[3]);
            int total = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
            System.out.println(total);
        }
    }
}
