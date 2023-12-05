package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제: 1182 부분수열의 합
난이도: Silver 2
알고리즘: 브루트포스
풀이 방법: dfs를 사용하여 완전 탐색하였다. 인덱스를 하나씩 늘려가며 재귀 호출하는데 현재 인덱스의 원소를
        포함하는 경우와 포함하지 않는 경우로 나누어 포함하는 경우엔 총 합에 현재 원소를 더하여 재귀 호출한다.
        주의할 것은 연속된 수열이 아니어도 된다는 것
 */
public class _1182 {

    static int cnt = 0;
    static int N;
    static int S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        S = Integer.parseInt(line[1]);

        // N개의 정수 입력
        line = br.readLine().split(" ");
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        // 완전 탐색 진행
        dfs(0, 0, 0);
        System.out.println(cnt);
    }

    public static void dfs(int idx, int length, int sum) {

        // 마지막 원소까지 다 봤다면
        if(idx == N) {
            // 크기가 양수인 부분 수열이고 총 합이 S인 경우의 수
            if(length > 0 && sum == S) {
                cnt++;
            }
            return;
        }

        dfs(idx+1, length, sum); // 수열에 포함하지 않는 경우
        dfs(idx+1, length+1, sum + arr[idx]); // 포함하는 경우
    }
}
