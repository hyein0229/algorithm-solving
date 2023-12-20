package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제: 14501 퇴사
난이도: Silver 3
알고리즘: 브루트포스, dp
풀이 방법: dfs 를 이용하여 완전 탐색을 하였다. dfs 호출 시 i일의 상담을 포함하는 경우와 포함하지 않는 경우로 두 가지로 나누어서
        호출한다. 이때 i+t 가 N+1를 넘지 않는 경우에만 상담을 포함하는 경우를 탐색하고 dfs 호출 시 다음 이동할 날짜는 i+t가 되며
        현재 이익에 해당 p를 더하여 넘겨준다. 포함하지 않는 경우의 dfs 호출 시엔 다음 날 i+1 과 현재 이익을 그대로 인자로 넘겨준다.
        N+1까지 모든 날짜를 탐색하였으면 최댓값을 갱신하고 탐색을 종료한다.
 */
public class _14501 {

    static int N;
    static int[][] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][2];

        for(int i=1; i<=N; i++) {
            String[] line = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(line[0]);
            arr[i][1] = Integer.parseInt(line[1]);
        }

        dfs(1, 0);
        System.out.println(answer);

    }

    public static void dfs(int day, int total) {
        if(day == N+1) {
            answer = Math.max(answer, total);
            return;
        }

        int t = arr[day][0]; // 기간
        int p = arr[day][1]; // 이익
        if(day+t <= N+1) { // N+1 일을 넘지 않는다면 포함 o
            dfs(day+t, total+p);
        }
        dfs(day+1, total); // 포함 x
    }
}
