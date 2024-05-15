package baekjoon;

import java.util.*;
import java.io.*;
/*
문제: 2493 탑
난이도: Gold 5
알고리즘: 자료 구조, 스택
풀이 방법: 왼쪽으로만 레이저가 나가므로 왼쪽에 있는 탑만 보면 된다.
        따라서, 입력받으면서 stack 에 순차적으로 삽입한다.
        어느 탑에서 수신하는지를 알기 위해선 stack의 peek 탑, 즉 바로 옆에 있는 탑부터
        왼쪽으로 탑을 탐색하는 데 이때, 자신의 높이보다 더 크면 정답이 되므로 바로 break
        한다. 그렇지 않다면 stack.pop 을 하고 다음 탑을 본다.
        pop 을 해도 되는 이유는?
        1. 더 나중에 입력받는 탑 높이 < 현재 탑 높이 -> 최소 현재 탑에서 레이저가 수신된다.
        2. 더 나중에 입력받은 탑 높이 > 현재 탑 높이 -> 현재 탑 높이보다 pop 한 높이가 무조건 더 작으므로 절대 정답이 될 수 없다.
 */
public class _2493 {

    static class Top{
        int height;
        int num;

        public Top (int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] ans = new int[N+1];
        Stack<Top> stack = new Stack<>();

        // 탑의 높이 입력
        for(int i=1; i<=N; i++) {
            int height = Integer.parseInt(line[i-1]);
            while(!stack.isEmpty()) {
                if(stack.peek().height > height) {
                    ans[i] = stack.peek().num;
                    break;
                }
                stack.pop();
            }
            if(stack.isEmpty()) {
                ans[i] = 0;
            }
            stack.add(new Top(i, height));
        }

        for(int i=1; i<=N; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
