package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 2116 주사위 쌓기
난이도: Gold 5
알고리즘: 구현, 브루트포스
풀이 방법: dfs + 백트래킹을 사용하여 풀이하였다. 윗면 숫자와 아랫면 숫자가 같으면 주사위를
        쌓을 수 있으므로 1번 주사위를 먼저 고정해둔 후 2번 주사위부터 이전 주사위의 윗면과 숫자가
        같은 모든 경우에 대해 dfs 를 재귀 호출하여 n개가 모두 쌓일 때까지 탐색한다.
        이때, 쌓인 주사위의 아랫면 인덱스는 stack에 넣어 기록할 수 있도록 하였다.
        n개가 모두 쌓이면 사각기둥의 옆면 최대합을 구하는데, 위아래만 고정해두고 개별적으로 옆면을 돌릴 수 있으므로
        정답은 각 주사위에서의 옆면 최댓값들의 합이 된다. 따라서, stack에 저장해둔 아랫면을 통해 위아랫면의 인덱스를
        구하고, 위아랫면에 해당하지 않는 면들의 숫자 중 최댓값을 구하여 모두 더해줌으로써 최대합을 구하고 정답을 갱신하였다.
 */
public class _2116 {
    static int n;
    static int[][] dice;
    static Stack<Integer> stack = new Stack<>();
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dice = new int[n+1][6]; // 주사위 숫자 배열
        // 주사위 입력
        for(int i=1; i<=n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=0; j<6; j++) {
                dice[i][j] = Integer.parseInt(line[j]);
            }
        }
        // 1번 주사위부터 차례대로 올림
        for(int i=0; i<6; i++) {
            stack.add(i); // 아랫면을 i번째 면으로 고정
            dfs(2, 1); // 2번 주사위부터 쌓기
            stack.clear();
        }
        System.out.println(answer);

    }

    public static void dfs(int diceNum, int height) {
        // 모든 주사위를 쌓음
        if(height == n) {
            int maxSum = getMaxSum(); // 최대 옆면의 합 구하기
            answer = Math.max(answer, maxSum);
            return;
        }

        int bottom = stack.peek(); // 이전 주사위의 아랫면 인덱스
        int top; // 이전 주사위의 윗면 인덱스 구하기
        if(bottom == 1 || bottom == 2) top = bottom + 2;
        else if(bottom == 3 || bottom == 4) top = bottom - 2;
        else top = Math.abs(bottom - 5);
        int topNum = dice[diceNum-1][top]; // 이전 주사위의 윗면 숫자

        for(int i=0; i<6; i++) {
            if(topNum == dice[diceNum][i]) { // 윗면 숫자와 같다면 쌓을 수 있음
                stack.add(i);
                dfs(diceNum+1, height+1);
                stack.pop();
            }
        }

    }

    public static int getMaxSum() {
        int diceNum = 1;
        int sum = 0;
        // 각 주사위의 옆면 숫자들 중 최댓값을 구하여 모두 더함
        for(int bottom : stack) {
            int top;
            if(bottom == 1 || bottom == 2) top = bottom + 2;
            else if(bottom == 3 || bottom == 4) top = bottom - 2;
            else top = Math.abs(bottom - 5);

            int maxNum = -1;
            for(int i=0; i<6; i++) {
                if(i == top || i == bottom)
                    continue;
                maxNum = Math.max(dice[diceNum][i], maxNum);
            }
            sum += maxNum;
            diceNum++;
        }
        return sum;
    }
}
