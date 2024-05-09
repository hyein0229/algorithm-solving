package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1174 줄어드는 수
난이도: Gold 5
알고리즘: 브루트포스, 백트래킹
풀이 방법: 가장 큰 수가 9876543210 이므로 브루트포스를 통해 만들어질 수 있는 줄어드는 수를 모두 구하는
        방식으로 풀 수 있다. 숫자를 만들 땐 어떤 숫자를 선택하느냐, 안하느냐 두 가지로 나눌 수 있다.
        따라서, 배열에 큰 수 9부터 0까지 거꾸로 저장한 후 인덱스 0부터 늘려가면서, 선택한 경우와 안한 경우
        두 가지로 나누어 숫자를 만들고 만들어진 수는 list에 저장한다.
        줄어드는 수를 모두 저장한 list를 sort하면 list.get(N-1)의 수가 정답이 된다.
        만약 list가 크기가 N보다 작다면 답이 없으므로 -1을 출력한다.
 */
public class _1174 {
    static int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dfs(0, 0);
        Collections.sort(list); // 정렬

        if(list.size() >= N) {
            System.out.println(list.get(N-1));
        } else {
            System.out.println(-1);
        }
    }

    public static void dfs(long num, int idx) {
        // 만들어진 모든 줄어드는 수를 저장
        if(!list.contains(num)) {
            list.add(num);
        }

        if(idx == 10) {
            return;
        }

        // 현재 숫자를 선택하지 않고 다음 인덱스로 넘어감
        dfs(num, idx+1);
        // 현재 숫자를 선택하고 넘어간다.
        dfs((num*10) + arr[idx], idx+1);
    }
}
