package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 16401 과자 나눠주기
난이도: Siver 2
알고리즘: 이분 탐색
풀이방법: 이분 탐색을 사용하며 left, right 변수는 조카에게 줄 막대 과자의 길이로 설정한다.
        따라서 left는 1, right는 최대 과자 길이로 초기화한다. 다음 과자 길이를 mid 로 하였을 때
        나누어 줄 수 있는 조카의 수를 구한다. 과자 길이 배열을 순회하면서 과자 길이가 mid 보다 크면
        mid 길이로 나눌 수 있으므로 나눈 값을 total 에 더한다.
        만약 조카의 수가 M 보다 작다면 과자 길이가 너무 길다는 것이므로 right 를 mid - 1로 감소시키고
        M 이상이면 left 를 mid + 1로 증가시켜 과자 길이를 늘린다.
 */
public class _16401 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        String[] line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        Arrays.sort(arr); // 과자 길이 오름차순 정렬

        int answer = 0;
        int left = 1;
        int right = arr[N-1];
        while(left <= right) {
            int mid = (left + right) / 2;

            int cnt = 0;
            for(int i=N-1; i>=0; i--) {
                if(arr[i] < mid) {
                    break;
                }
                cnt += arr[i] / mid;
            }

            if(cnt >= M) { // M명 이상에게 나눌 수 있다면
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else { // M명에게 나누어 줄 수 없을 때
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
