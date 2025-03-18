package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제: 기업투자 2662
 * 난이도: Gold 2
 * 알고리즘: dp, 냅색
 * 풀이 방법: dp[투자금액][기업] = 0번부터 현재 기업까지 투자금액만큼 투자했을 때 최대 이익금
 *          invest[투자금액][기업] = 0번부터 현재 기업까지 투자금액만큼 투자했을 때 최대 이익금이 되는 경우의 현재 기업의 투자 액수 저장
 */
public class _2662 {

    static int N, M;
    static int[][] arr;
    static int[][] dp;
    static int[][] invest;
    static int[] path;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        arr = new int[N+1][M+1];
        dp = new int[N+1][M+1];
        invest = new int[N+1][M+1];
        path = new int[N+1];

        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            int money = Integer.parseInt(line[0]); // 투자 액수

            // 투자했을 경우 얻게 되는 이익
            for(int j=1; j<=M; j++) {
                arr[money][j] = Integer.parseInt(line[j]); // 베네핏
            }
        }

        for(int i=1; i<=M; i++) {
            for(int j=0; j<=N; j++) { // i번 기업에 j 원을 투자하는 경우
                for(int k = N-j; k>=0; k--) { // 총 투자 금액 - j
                    if(dp[k+j][i] < dp[k][i-1] + arr[j][i]) {
                        dp[k+j][i] = dp[k][i-1] + arr[j][i];
                        invest[k+j][i] = j;
                    }
                }
            }
        }

        getPath(N, M);

        System.out.println(dp[N][M]);

        for(int i=1; i<=M; i++) {
            System.out.print(path[i] + " ");
        }

    }

    public static void getPath(int n, int m) {
        if(m == 0) {
            return;
        }
        path[m] = invest[n][m];
        getPath(n-path[m], m-1);
    }
}
