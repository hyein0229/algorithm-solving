package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 30804 과일 탕후루
난이도: Silver 2
알고리즘: 투포인터
풀이 방법: 앞쪽과 위쪽에서 과일을 뺀다는 것에서 힌트 => 연속되는 과일 중 두 종류만 포함하면서 가장 긴 수열을 찾으면 됨
        따라서, 투포인터로 left = 0, right = 0 부터 시작하여 아직 과일이 두 종류를 초과하지 않으면 right 를 계속
        증가시키고, 만약 right 과일을 포함시켰을 때 두 종류가 초과되는 경우엔 하나의 종류만 가지고 있을
        때까지 left를 증가시키면서 과일을 제거한다.
        right-left+1 이 포함된 과일 개수이기 떄문에 ans 에 계속 max 값을 갱신하면서 답을 구한다.
 */
public class _30804 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 과일의 개수
        int[] fruits = new int[N];

        // 과일 입력
        String[] line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            fruits[i] = Integer.parseInt(line[i]);
        }

        Set<Integer> types = new HashSet<>();
        int left = 0;
        int right = 0;
        int ans = 1;
        int[] cnt = new int[10];
        while(left <= right && right < N) {
            // 두 개의 종류가 꽉찬 경우
            if(types.size() == 2) {
                if(!types.contains(fruits[right])) { // 이미 포함된 종류면 가능
                    while(types.size() != 1) {
                        cnt[fruits[left]]--;
                        if (cnt[fruits[left]] == 0) {
                            types.remove(fruits[left]);
                        }
                        left++;
                    }
                }
            }
            types.add(fruits[right]);
            cnt[fruits[right]]++;
            ans = Math.max(ans, right-left+1);
            right++;
        }
        System.out.println(ans);
    }
}
