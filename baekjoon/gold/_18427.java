package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 18427 함께 블록 쌓기
난이도: Gold 4
알고리즘: dp, 배낭 문제
풀이 방법: dp[i][j] = i번 학생까지 블록을 쌓았을 때 높이가 j인 탑을 만드는 경우의 수
        1번 학생부터 차례대로 순회하면서 높이 1~H를 만드는 경우에 대해 dp 값을 갱신하는데,
        이때 현재 i번 학생의 블록을 사용하지 않는 경우 이전 i-1 번 학생의 dp 값을 그대로 누적시키고,
        만약 사용하는 경우 i번 학생이 가지고 있는 모든 블럭에 대해 높이 h 이고 만들 높이를 j라 할 때
        높이 h 블럭 + 높이 j-h 블럭 = 높이 j
        dp[i][j] += dp[i-1][j-h] 하여 이전까지 j-h 를 만들었던 경우의 수를 더한다.
 */
public class _18427 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int H = Integer.parseInt(line[2]);
        List<Integer>[] blocks = new ArrayList[N+1];
        int[][] dp = new int[N+1][1001]; // 각 높이 별 만들 수 있는 경우의 수

        // 학생들 블록 입력
        for(int i=1; i<=N; i++) {
            blocks[i] = new ArrayList<>();
            line = br.readLine().split(" ");
            for(int j=0; j<line.length; j++) {
                blocks[i].add(Integer.parseInt(line[j]));
            }
        }
        // 0을 만들 수 있는 경우의 수는 항상 1
        for(int i=0; i<=N; i++) {
            dp[i][0] = 1;
        }

        // 1번부터 차례대로 블록 높이 만들기
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=H; j++) {
                dp[i][j] = dp[i-1][j]; // i의 블록을 사용하지 않음, 이전까지 만든 경우의 수 더함
                for(int h : blocks[i]) { // i의 블록을 하나씩 사용
                    if(j >= h) { // h 높이의 블록과 이전까지 만든 높이 j-h 블록을 더하여 경우의 수 만들기
                        dp[i][j] += dp[i-1][j-h];
                        dp[i][j] %= 10007;
                    }
                }
            }
        }
        System.out.println(dp[N][H]);
    }
}
