package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 22866 탑 보기
난이도: Gold 3
알고리즘: 자료구조, 스택
풀이 방법: 스택을 사용하여 왼쪽에 보이는 건물의 수, 오른쪽에서 보이는 건물의 수를 따로 구한다.
        왼쪽을 구하기 위해 1번부터 N번까지 순회하면서 Stack 에 있는 건물 중 자기 자신보다
        높이가 작거나 같은 것을 pop 한다. => (작거나 같은 것은 가려져서 볼 수 없음)
        모두 pop 한 다음 남아있는 건물의 수가 i번째에서 보이는 건물의 수가 되며 이때,
        peek한 건물, 즉 가장 가까운 건물과의 거리를 비교하여 가장 가까운 거리와 건물을 갱신한다.
        오른쪽도 같은 방법으로 구하는데 차이는 반대로 N번부터 1번까지 역순으로 건물을 순회해야 한다.
 */
public class _22866 {

    static class Building {
        int num;
        int height;

        public Building(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        Building[] arr = new Building[N+1];
        int[] cnt = new int[N+1];
        int[][] near = new int[N+1][2]; // [가장 가까운 건물 중 작은 번호, 가장 가까운 거리]

        // 건물 높이 입력
        for(int i=1; i<=N; i++) {
            int h = Integer.parseInt(line[i-1]);
            arr[i] = new Building(i, h);
            Arrays.fill(near[i], Integer.MAX_VALUE);
        }

        Stack<Building> stack = new Stack<>();

        // 왼쪽의 보이는 건물 수 구하기
        for(int i=1; i<=N; i++) {
            // 자신보다 작거나 같으면 pop -> 가려져서 볼 수 없는 건물들
            while(!stack.isEmpty() && stack.peek().height <= arr[i].height) {
                stack.pop();
            }
            cnt[i] += stack.size(); // 남아 있는 건물의 수가 보이는 건물의 수

            if(!stack.isEmpty()) {
                int dist = Math.abs(stack.peek().num - i); // 가장 가까운 좌측 건물과의 거리
                if(dist < near[i][1]) {
                    near[i][0] = stack.peek().num;
                    near[i][1] = dist;
                } else if(dist == near[i][1]) {
                    near[i][0] = Math.min(near[i][0], stack.peek().num);
                }
            }
            stack.push(arr[i]);
        }

        stack.clear();

        // 오른쪽의 보이는 건물 수 구하기
        for(int i=N; i>=1; i--) {
            // 자신보다 작거나 같으면 pop
            while(!stack.isEmpty() && stack.peek().height <= arr[i].height) {
                stack.pop();
            }
            cnt[i] += stack.size();

            if(!stack.isEmpty()) {
                int dist = Math.abs(stack.peek().num - i); // 가장 가까운 좌측 건물과의 거리
                if(dist < near[i][1]) {
                    near[i][0] = stack.peek().num;
                    near[i][1] = dist;
                } else if(dist == near[i][1]) {
                    near[i][0] = Math.min(near[i][0], stack.peek().num);
                }
            }
            stack.add(arr[i]);
        }

        for(int i=1; i<=N; i++) {
            if(cnt[i] == 0)
                System.out.println("0");
            else
                System.out.println(cnt[i] + " " + near[i][0]);
        }
    }
}
