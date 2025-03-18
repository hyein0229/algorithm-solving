package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 16953 A -> B
난이도: Silver 2
알고리즘: bfs
풀이 방법: bfs 를 사용하여 큐에 2를 곱한 경우, 1을 오른쪽에 추가한 경우 두 가지의 경우를 삽입해준다.
        큐에서 뺀 수가 B면 탈출. 주의할 점은 int 형이 아닌 long 형으로 해야 한다.
        만약 A가 5억인 경우 1을 오른쪽으로 추가할때 50억 + 1 이 되므로 int 범위를 벗어나기 때문.
 */
public class _16953 {
    //16953
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int A = Integer.parseInt(line[0]);
        int B = Integer.parseInt(line[1]);

        Queue<long[]> q = new LinkedList<>();
        q.add(new long[]{A, 1});
        while(!q.isEmpty()) {
            long[] cur = q.poll();
            long num = cur[0];
            long dist = cur[1];
            if(num == B) {
                System.out.println(dist);
                return;
            }

            if(num * 2 <= B)
                q.add(new long[]{num * 2, dist + 1}); // 2룰 곱한 경우
            if(Long.parseLong(num + "1") <= B)
                q.add(new long[]{Long.parseLong(num + "1"), dist + 1}); // 1을 오른쪽에 추가
        }
        System.out.println(-1);
    }
}
