package baekjoon;

import java.io.*;
/*
문제: 15961 회전 초밥
난이도: Gold 4
알고리즘: 투 포인터, 슬라이딩 윈도우
풀이 방법: 연속 k개를 먹으므로 초밥 k개의 왼쪽 끝 포인터, 오른쪽 끝 포인터 변수를 선언한 후 포인터 위치를
       늘려주면서 포인터 사이의 초밥 가짓수를 세어주면 된다.
       처음엔 반복문 안에서 k개의 초밥을 처음부터 끝까지 다 탐색하면서 가짓수를 세어주었지만 입력 조건에서 알 수 있듯
       최악의 경우 N은 3,000,000 이고 k개는 3,000 이므로 시간 초과가 날 것을 짐작할 수 있다.
       포인터를 하나씩 늘려주면 결국 이전에 먹었던 초밥에서 왼쪽 끝 포인터의 초밥이 빠지고 오른쪽 끝 포인터의 초밥만 추가된다.
       따라서, 다 탐색할 필요 없고 왼쪽, 오른쪽 포인터가 가르키는 초밥의 종류만 고려하여 가짓수를 갱신해주면 된다.
 */
public class _15961 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int d = Integer.parseInt(line[1]);
        int k = Integer.parseInt(line[2]);
        int c = Integer.parseInt(line[3]);
        int[] arr = new int[N];
        // 회전 초밥 입력
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int left = 0;
        int right = left + k - 1;
        int[] eat = new int[d+1];
        // 첫번째 위치부터 k개를 연속 먹을 때 가짓수 구하기
        int cnt = 0;
        for(int i=left; i<=right; i++) {
            if(eat[arr[i]] == 0) {
                cnt++;
            }
            eat[arr[i]]++;
        }

        int maxCnt = (eat[c] == 0) ? cnt+1 : cnt;
        left++;
        right = (right + 1) % N;

        while(left < N) {
            // 이전에 먹은 초밥 하나 제거
            eat[arr[left-1]]--;
            if(eat[arr[left-1]] == 0) { // 하나도 먹지 않은 종류면 가짓수 감소
                cnt--;
            }
            // 다음 초밥을 새로 먹음
            if(eat[arr[right]] == 0) { // 처음 먹는 종류면 가짓수 증가
                cnt++;
            }
            eat[arr[right]]++;

            if(eat[c] == 0) { // 쿠폰의 초밥을 먹지 않았다면 추가
                maxCnt = Math.max(maxCnt, cnt+1);
            } else {
                maxCnt = Math.max(maxCnt, cnt);
            }
            left++;
            right = (right + 1) % N;
        }
        System.out.println(maxCnt);
    }
}
