package baekjoon;

import java.io.*;
/*
문제: 13144 List of Unique Numbers
난이도: Gold 4
알고리즘: 투 포인터
풀이 방법: 왼쪽 포인터 l은 수열의 시작 인덱스가 되므로 그대로 두고 오른쪽 포인터 r 을 중복이 나올 때까지
      하나씩 늘려간다. 이때, 주의할 것은 r+1 인덱스의 범위와 값의 중복을 미리 확인한 후 늘리는 것이다.
      중복이 발견되고 while 을 빠져나왔을 때 l, r 값은 l부터 시작되는 가능한 수열의 범위를 의미하며
      따라서 정답에 수열의 길이인 r-l+1을 더해주면 된다. 그 다음 l을 늘려 다음 시작 위치부터 다시 중복이 나올 때까지의 수열을 구하는 것을 반복한다.
 */
public class _13144 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] arr = new int[N];
        boolean[] visited = new boolean[100001];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        int l = 0;
        int r = -1;
        long ans = 0;
        while(l < N) {
            // 먼저 중복 확인을 한 후 인덱스를 늘림
            while(r+1 < N && !visited[arr[r+1]]) {
                visited[arr[++r]] = true;
            }
            ans += r - l + 1;
            visited[arr[l++]] = false;
        }
        System.out.println(ans);
    }
}
