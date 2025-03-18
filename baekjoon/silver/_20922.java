package baekjoon;

import java.io.*;
/*
문제: 20922 겹치는 건 싫어
난이도: Silver 1
알고리즘: 투 포인터
풀이 방법: 연속 수열이므로 양쪽 끝 포인터 변수를 선언하고, 포인터를 늘려가며 답을 구한다.
        왼쪽, 오른쪽 포인터를 처음에 0으로 모두 초기화한 후, 오른쪽 포인터를 하나씩 늘린다.
        현재 포인터의 원소의 카운트를 증가시킨 후, 해당 원소가 K개를 초과하는 지 확인한다.
        초과한다면, 조건에 맞지 않으므로 K개 이하가 될 때까지 왼쪽 포인터를 증가시킨다.
        위를 오른쪽 포인터가 N이 될 때까지 반복한다.
 */
public class _20922 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        int[] arr = new int[N];
        int[] numCnt = new int[100001];

        line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        int maxLen = 0;
        int l = 0; // 연속 수열의 왼쪽 포인터
        int r = l; // 연속 수열의 오른쪽 포인터
        while(r < N){
            int num = arr[r]; // 현재 원소
            numCnt[num]++;
            // K개를 초과한 경우
            if(numCnt[num] > K) {
                for (int j = l; j <= r; j++) {
                    if (numCnt[num] <= K) { // K개 이하가 되는 구간까지 왼쪽 포인터 이동
                        break;
                    }
                    numCnt[arr[j]]--;
                    l++;
                }
            }
            maxLen = Math.max(maxLen, (r-l)+1); // 최장 길이 갱신
            r++; // 오른쪽 끝 증가
        }
        System.out.println(maxLen);
    }
}
