package baekjoon;

import java.io.*;
/*
문제: 2467 용액
난이도: Gold 5
알고리즘: 이분탐색, 투포인터
풀이 방법: 두 수의 합이 특정 k에 가까운 두 수 찾기 => 오름차순 정렬 후 이분 탐색 사용
        1. left = 0, right = n-1 선언 후 left < right 까지 반복문 시작
        2. arr[left] + arr[right] 합과 K의 절댓값 차이 diff 와 최소 차이 minDiff 비교
           2-1 minDiff 보다 작다면 새로운 값으로 정답 갱신
        3. sum < k 인 경우 sum 값을 증가시키기 위해 left++
        4. sum > k 인 경우 sum 값을 감소시키기 위해 right--
        5. 만약, 정답이 2개 이상일 때 정답 카운트가 필요하다면
           sum = k 일 때 즉시 종료하지 않고 left++, right-- 동시에 포인터 조정하여 계속 탐색
 */
public class _2467 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] arr = new int[N];
        // 용액 오름차순 입력
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        int left = 0;
        int right = N-1;
        int minDiff = Integer.MAX_VALUE;
        int[] ans = new int[2];
        while(left < right) {
            int sum = arr[left] + arr[right];
            int diff = Math.abs(sum); // 0과의 거리
            if(minDiff > diff) {
                minDiff = diff;
                ans[0] = arr[left];
                ans[1] = arr[right];
            }

            if(diff == 0) break;

            if(sum > 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}
