package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 3687 성냥개비
난이도: Gold 2
알고리즘: dp, 그리디
풀이 방법: dp[i] = i 개의 성냥개비를 사용해서 만들 수 있는 가장 작은 수
        먼저 dp 배열을 생성하고 초기화하는 데 이때, 2~8개까지 해당 개수로 만들 수 있는
        가장 작은 숫자로 dp 값을 초기화한다. 그리고 2~7개로 만들 수 있는 가장 작은 수 배열
        minAdd 를 생성한다. 9~100개로 만들 수 있는 가장 작은 수를 구하는데
        이때 dp 점화식은 dp[i] = min(dp[i], dp[i-k] + minAdd[k]) 이 된다.
        가장 큰 수는 가장 긴 자릿수를 구하는 방법이다.
        현재 성냥개비 2개로 1을 만들 때 가장 성냥개비를 적게 사용하므로
        최대한 2개씩 사용하면서 111... 자릿수를 늘린다.
        이때, 짝수인 경우엔 n/2 만큼 1을 붙이면 되지만 홀수인 경우엔 1만으로
        모든 성냥개비를 다 사용할 수 없으므로 그 다음 3개로 적게 사용되는 7을 이용한다.
        즉, 예를 들어 9일 때 7111이 된다. 따라서, 큰 수는 1과 7만으로 만들 수 있다.
 */
public class _3687 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 2~100개의 성냥개비 개수로 만들 수 있는 가장 작은 수 구하기
        long[] minDp = new long[101];

        Arrays.fill(minDp, Long.MAX_VALUE);
        // dp 값 초기화
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;
        String[] minAdd = {"0", "0", "1", "7", "4", "2", "0", "8"};

        for(int j=9; j<=100; j++) {
            for(int k=2; k<=7; k++) {
                String str = "" + minDp[j-k] + minAdd[k];
                minDp[j] = Math.min(minDp[j], Long.valueOf(str));
            }
        }

        for(int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine()); // 성냥개비 개수

            // n개로 만들 수 있는 가장 큰 숫자 구하기
            StringBuilder sb = new StringBuilder();
            if(n % 2 != 0) {
                sb.append("7");
            } else {
                sb.append("1");
            }

            for(int j=1; j<n/2; j++) {
                sb.append("1");
            }
            System.out.println(minDp[n] + " " + sb.toString());
        }
    }
}
