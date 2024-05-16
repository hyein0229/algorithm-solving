package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1863 스카이라인 쉬운거
난이도: Gold 4
알고리즘: 자료 구조, 스택
풀이 방법: 1. 높이가 높아지면 stack 에 y를 넣는다.
         2. 높이가 낮아지면 현재 높이보다 높은 빌딩들을 모두 pop 하고 카운트한다.
            높이가 낮아지는 것은 다른 건물의 끝을 의미하므로
         3. 높이가 같으면 같은 건물로 취급하므로 stack 에 넣지 않는다.
         4. 마지막으로 stack 이 빌 때까지 하나씩 pop 하면서 y가 0이 아니면 카운트한다.
         
         주의!!
         2번 동작에서 직전 빌딩만 pop 하지 않고 while 반복문으로 높은 빌딩들을 모두 pop 해야 하는 이유
         반례 (1, 1), (2, 2), (3, 3), (4, 1) 가 입력될 때
         (4, 1)에서 (3, 3) 빌딩만 pop 하면 (2, 2) 때문에 (1, 1) 빌딩과 높이가 같음에도 stack 에 삽입되어 높이 1의 건물이 두 번 카운트된다.
         따라서, (2, 2) 까지 pop 해주어야 (1, 1) 빌딩과 높이를 체크하여 stack 에 삽입되지 않는다.
 */
public class _1863 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for(int i=0; i<n; i++) {
            String[] line = br.readLine().split(" ");
            int y = Integer.parseInt(line[1]);

            // 현재 높이보다 높은 건물들의 높이를 모두 pop 하고 카운트
            // 높이가 낮아졌다는 것은? => 한 건물의 끝을 의미
            while(!stack.isEmpty() && stack.peek() > y) {
                ans++;
                stack.pop();
            }

            // 높이가 같으면 같은 건물임을 의미하므로 스택에 넣지 않음
            if(!stack.isEmpty() && stack.peek() == y) {
                continue;
            }
            stack.add(y);
        }

        while(!stack.isEmpty()) {
            if(stack.peek() > 0) {
                ans++;
            }
            stack.pop();
        }
        System.out.println(ans);
    }
}
