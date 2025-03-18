package baekjoon;

import java.io.*;
/*
문제: 16943 숫자 재배치
알고리즘: 브루트포스, 백트래킹
풀이 방법: dfs + 백트래킹을 사용하여 풀었다. 먼저 0~9까지 수를 인덱스로 하는 cnt 배열을 생성하여
        A에 포함된 숫자를 카운트한 결과 값을 저장한다. cnt에 저장된 값이 0보다 크다면 해당 숫자를 사용하여
        순열을 만들 수 있다는 뜻이다. 따라서, 0을 제외하고 1~9 중 cnt 값이 0 보다 큰 숫자로 시작하여 dfs로
        하나씩 슷자를 붙여가며 A의 순열을 구한다. 숫자 i를 사용했다면 cnt[i]을 감소시켜 또 사용할 수 있는 횟수를 줄인다.
        순열이므로 dfs 탐색이 끝나면 cnt 값을 다시 늘려주어, 즉 백트래킹하여 다음에 사용할 수 있도록 해야 한다.
        탐색 종료 조건은 A에 포함된 숫자를 모두 사용했을 때이고 이때 B보다 작다면 C의 최댓값을 갱신하도록 하였다.
 */
public class _16943 {

    static String A;
    static int B;
    static int[] cnt = new int[10];
    static int C = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        A = line[0];
        B = Integer.parseInt(line[1]);
        // 사용된 숫자의 개수 카운트
        for(int i=0; i<A.length(); i++) {
            cnt[A.charAt(i) - '0']++;
        }

        for(int i=1; i<=9; i++) {
            if(cnt[i] > 0) {
                cnt[i]--;
                dfs(String.valueOf(i)); // i로 시작하는 순열 구하기
                cnt[i]++;
            }
        }

        if(C == Integer.MIN_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(C);
        }
    }

    public static void dfs(String numStr) {
        // A의 숫자를 모두 다 썼으면
        if(numStr.length() == A.length()) {
            if(Integer.parseInt(numStr) < B) { // B보다 작으면 C의 최댓값 갱신
                C = Math.max(Integer.parseInt(numStr), C);
            }
            return;
        }

        for(int i=0; i<A.length(); i++) {
            int cur = A.charAt(i) - '0';
            if(cnt[cur] > 0) {
                cnt[cur]--;
                dfs(numStr + cur);
                cnt[cur]++; // 백트래킹
            }
        }
    }
}
