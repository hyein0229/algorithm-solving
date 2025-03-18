package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 9024 두 수의 합
난이도: Gold 5
알고리즘: 정렬, 투포인터, 이분 탐색
풀이 방법: 1. 오름차순 정렬을 한 뒤 left = 0, right = n-1 두 개의 포인터를 선언한다.
         2. left < right 일 때까지 arr[left] + arr[right], 즉 두 수의 합을 구하고
            합과 K와의 차이를 구한다.
         3. 차이가 현재 최소의 차이와 같다면? => 정답 카운트 증가
            차이가 현재 최소의 차이보다 작아진다면? => 최소 차이를 갱신하고 카운트는 다시 1부터 시작
         4. 그 다음, 포인터를 조정하는 데 이 때 두 수의 합이 K보다 작다면
            합을 증가시키기 위해 left++, 크다면 합을 감소시키기 위해 right-- 하고
            K와 같다면 left, right 를 모두 1만큼 증가, 감소시킨다.
 */
public class _9024 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++) {
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int K = Integer.parseInt(line[1]);
            int[] arr = new int[n];

            line = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                arr[j] = Integer.parseInt(line[j]);
            }

            Arrays.sort(arr); // 오름차순 정렬
            int minDiff = Integer.MAX_VALUE;
            int ans = 0;
            int left = 0;
            int right = n-1;
            while(left < right) { // 이분 탐색
                int sum = arr[left] + arr[right]; // 두 수의 합
                int diff = Math.abs(K - sum);
                // K와 가장 가까운 두 수의 합 갱신
                if(diff < minDiff) {
                    minDiff = diff;
                    ans = 1;
                } else if(diff == minDiff)
                    ans++;

                if(sum == K) {
                    left++;
                    right--;
                } else if(sum < K) {
                    left++;
                } else {
                    right--;
                }
            }
            System.out.println(ans);
        }
    }
}
