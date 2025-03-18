package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1943 동전 분배
난이도: Gold 2
알고리즘: dp, 배낭 문제
풀이 방법: dp 를 사용하여 해결한다. 동전의 최대 금액이 100000 이므로 100001 길이의 boolean 형 dp 배열을
        선언한다. dp[i] 는 i원의 금액이 만들어 질 수 있는지 여부를 나타낸다.
        먼저, 동전의 금액과 개수를 입력받으면서 해당 동전으로 만들 수 있는 모든 금액에 대해 dp 를 체크한다.
        만약, 이때 모든 동전의 총액이 홀수이면 절대 반으로 나눌 수 없으므로 0을 출력한다.
        그리고 N개의 동전 종류를 순회하면서 dp 값을 갱신하는데,
        현재 동전 값과 이전에 만들어진 동전들과의 합을 계산하여 새롭게 만들어진 액수의 dp를 체크한다.
 */
public class _1943 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<3; t++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] dp = new boolean[100001];
            List<int[]> coinList = new ArrayList<>();
            int total = 0;
            // 동전 정보 입력
            for(int i=0; i<N; i++) {
                String[] line = br.readLine().split(" ");
                int v = Integer.parseInt(line[0]);
                int q = Integer.parseInt(line[1]);
                total += v * q; // 받은 총 금액 구하기
                coinList.add(new int[]{v, q}); // 동전 금액과 개수 삽입
                for(int j=1; j<=q; j++) {
                    dp[v * q] = true; // 현재 동전으로 만들 수 있는 액수 체크
                }
            }

            // 총 금액이 홀수면 반으로 나눌 수 없음
            if(total % 2 != 0) {
                sb.append("0").append("\n");
                continue;

            } else if(dp[total / 2]) { // 이미 총액의 반인 액수가 만들어짐
                sb.append("1").append("\n");
                continue;
            }

            // 동전으로 total 의 반인 액수를 만들 수 있는지 확인
            dp[0] = true;
            for(int i=0; i<N; i++) {
                int[] coin = coinList.get(i);
                int v = coin[0]; // 금액
                int q = coin[1]; // 개수

                for(int j=total/2; j>=v; j--) {
                    if(dp[j-v]) {
                        // j-v 원에 현재 동전을 더하여 새로운 액수를 만들기
                        for(int k=1; k<=q; k++) {
                            if(j-v + (v*k) > total/2)
                                break;
                            dp[j-v + (v*k)] = true;
                        }
                    }
                }
            }
            // total / 2 를 만들 수 있으면 -> 나머지 total / 2 를 다른 사람이 가져가면 됨
            if(dp[total / 2]) {
                sb.append("1").append("\n");
            } else {
                sb.append("0").append("\n");
            }
        }
        System.out.print(sb);
    }
}
