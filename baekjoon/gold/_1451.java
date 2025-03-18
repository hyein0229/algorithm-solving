package baekjoon;

import java.io.*;
/*
문제: 1451 직사각형으로 나누기
난이도: Gold 4
알고리즘: 구현, 브루트포스, 많은 조건 분기
풀이 방법: 직사각형 3가지로 나눌 수 있는 방법은 6가지 이다. 각 경우에 대해 모두 해보는 것
 */
public class _1451 {

    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        board = new int[N][M];
        long ans = 0;

        for(int i=0; i<N; i++) {
            String row = br.readLine();
            for(int j=0; j<M; j++) {
                board[i][j] = row.charAt(j) - '0';
            }
        }

        // 가로 3줄로 나눌 경우
        for(int i=0; i<N-2; i++) {
            for(int j=i+1; j<N-1; j++) {
                long a = calcSum(0, 0, i, M-1);
                long b = calcSum(i+1, 0, j, M-1);
                long c = calcSum(j+1, 0, N-1, M-1);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 세로 3줄로 나눌 경우
        for(int i=0; i<M-2; i++) {
            for(int j=i+1; j<M-1; j++) {
                long a = calcSum(0, 0, N-1, i);
                long b = calcSum(0, i+1, N-1, j);
                long c = calcSum(0, j+1, N-1, M-1);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 세로 1줄, 가로 2줄로 나눌 경우
        for(int i=0; i<N-1; i++) {
            for(int j=0; j<M-1; j++) {
                long a = calcSum(0, 0, N-1, j); // 세로 한 블럭
                long b = calcSum(0, j+1, i, M-1);
                long c = calcSum(i+1, j+1, N-1, M-1);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 가로 2줄, 세로 1줄 나눌 경우
        for(int i=0; i<N-1; i++) {
            for(int j=1; j<M; j++) {
                long a = calcSum(0, 0, i, j-1);
                long b = calcSum(i+1, 0, N-1, j-1);
                long c = calcSum(0, j, N-1, M-1);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 가로 1줄, 세로 2개
        for(int i=0; i<N-1; i++) {
            for(int j=0; j<M-1; j++) {
                long a = calcSum(0, 0, i, M-1); // 가로 한 줄
                long b = calcSum(i+1, 0, N-1, j);
                long c = calcSum(i+1, j+1, N-1, M-1);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 세로 2개, 가로 1줄
        for(int i=1; i<N; i++) {
            for(int j=0; j<M-1; j++) {
                long a = calcSum(0, 0, i-1, j);
                long b = calcSum(0, j+1, i-1, M-1);
                long c = calcSum(i, 0, N-1, M-1);
                ans = Math.max(ans, a * b * c);
            }
        }
        System.out.println(ans);
    }

    public static int calcSum(int x, int y, int x2, int y2) {
        int sum = 0;
        for(int i=x; i<=x2; i++) {
            for(int j=y; j<=y2; j++) {
                sum += board[i][j];
            }
        }
        return sum;
    }
}
