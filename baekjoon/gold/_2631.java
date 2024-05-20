package baekjoon;

import java.io.*;
/*
문제: 2631 줄세우기
난이도: Gold 4
알고리즘: dp, 최장 증가 부분 수열 (LIS)
풀이 방법: 최장 증가 부분 수열 => 오름차순으로 정렬된 가장 긴 부분 수열
        오름차순으로 서 있는 아이들을 빼고 나머지 아이들을 적절한 위치로 이동시켜 주면 된다.
        따라서 최장 증가 부분 수열 길이를 구해 N에서 빼주면 정답을 구할 수 있다.
 */
public class _2631 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = 1;
        for(int i=0; i<N; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) { // 자신보다 앞에 있는 숫자들 확인
                if(arr[j] < arr[i]) { // 자신이 더 크다면 부분 수열이 증가할 가능성이 있음
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(N-max);
    }
}
