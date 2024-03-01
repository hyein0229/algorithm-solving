package baekjoon;

import java.io.*;
/*
문제: 1082 방 번호
난이도: Gold 3
알고리즘: 그리디
풀이 방법: 1. 입력받는 비용 중에 최소 비용과 최소 비용에 해당하는 숫자를 구한다.
         2. M을 최소 비용으로 나누어 최대 만들 수 있는 자릿수 len 을 구한다.
         3. digits 를 len 크기 배열로 선언한 후 최소 비용을 주고 해당 숫자를 사서 채운다.
         4. 구하는 답의 시작 위치 인덱스를 start로 두고 처음 start=0부터 시작한다.
         5. digits 배열을 0부터 순회하면서 해당 자리에 현재 비용에 최소 비용 숫자를 다시 되팔고 더 큰 숫자를 살 수 있는지
            보고, 살 수 있다면 해당 숫자를 사서 바꾼다.
         6. 만약, 5번에서 더 큰 숫자를 사지 못했다면 start를 1 증가시키고 최소 비용만큼 받아 자릿수를 감소시킨다.
         7. 다음 자릿수의 숫자에 대해 다시 반복한다.
 */
public class _1082 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cost = new int[N];

        String[] line = br.readLine().split(" ");
        int minCost = 50;
        int num = -1;
        for(int i=0; i<N; i++) {
            cost[i] = Integer.parseInt(line[i]);
            if(minCost >= cost[i]) { // 최소 비용 구하기
                minCost = cost[i];
                num = i;
            }
        }
        int M = Integer.parseInt(br.readLine());

        int maxLen = M / minCost; // 최대 나올 수 있는 자릿수
        int[] digits = new int[maxLen];
        for(int i=0; i<maxLen; i++) { // 최소 비용이 드는 번호로 채우기
            digits[i] = num;
            M -= minCost;
        }

        int start = 0; // 구하는 답의 자릿수가 시작하는 위치
        for(int i=0; i<maxLen; i++) {
            for(int j=N-1; j>=0; j--) {
                // 최소 비용만큼 되팔고 해당 번호를 살 수 있다면 바꿈
                if(cost[j] <= M + minCost) {
                    digits[i] = j;
                    M += minCost - cost[j];
                    break;
                }
            }
            // 남아 있는 돈으로는 현재 자릿수의 더 큰 숫자를 만들 수 없음
            // 따라서, 최소 비용만큼 돌려 받고 자릿수를 하나 감소시킴
            if(digits[start] == 0) {
                start++;
                M += minCost;
            }
        }

        if(start == maxLen) {
            System.out.println(0);
        } else {
            String answer = "";
            for(int i=start; i<maxLen; i++) {
                answer += digits[i];
            }
            System.out.println(answer);
        }
    }
}
