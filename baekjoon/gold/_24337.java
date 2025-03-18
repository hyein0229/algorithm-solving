package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 24337 가희와 탑
난이도: Gold 3
알고리즘: 그리디
풀이 방법: 사전 순으로 가장 앞서는 답을 찾기 위해선 앞쪽을 최대한 1로 채워야 한다.
        따라서 1 1 1 1 2 ... a-1 max(a, b) b-1 b-2... 2 1  이런 순서로 건물을 세워야 한다.
        a 를 만족하기 위해선 1부터 a-1까지의 건물이 필요하다. 따라서 먼저 list 에 해당 높이를 넣는다.
        그 다음, max(a, b) 를 넣는 데 이유는 이 높이가 가희와 단비에게 공통적으로 가장 높은 건물이 된다.
        그리고 b-1 부터 1까지 높이를 list 에 넣는다.
        그리고 남은 빈자리를 모두 1로 채우는 데 list 의 인덱스 1의 자리, 즉 두번째에 넣는다.
            - a = 1이면, 1이 가장 왼쪽에 추가되었을 때 규칙이 깨진다.
            - a > 1이면, 첫 번째 높이는 무조건 1이므로 상관이 없다.
            - 따라서, 두번째 자리부터 1을 삽입한다.
 */
public class _24337 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int a = Integer.parseInt(line[1]);
        int b = Integer.parseInt(line[2]);
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // 필요한 높이의 개수가 N을 초과하면 -1 출력
        if(a+b-1 > N) {
            System.out.println(-1);
            return;
        }

        for(int i=1; i<=a-1; i++) {
            list.add(i);
        }
        list.add(Math.max(a, b)); // 가희와 단비에게 공통적으로 가장 높은 건물

        for(int i=b-1; i>=1; i--) {
            list.add(i);
        }
        // 남은 건물 개수만큼 두 번째 자리에 계속 1을 삽입
        for(int i=0; i<N-(a+b-1); i++) {
            list.add(1, 1);
        }

        for(int h : list) {
            sb.append(h).append(" ");
        }
        System.out.println(sb);
    }
}
