package baekjoon;

import java.io.*;
/*
문제: 14891 톱니바퀴
난이도: Gold 5
알고리즘: 구현, 시뮬레이션
풀이 방법: 주어진 대로 구현하는 문제이므로 쉬운 문제였다.
        신경써야 할 부분은 톱니바퀴 회전 구현 시 시계 회전인 경우엔 끝에서부터 배열 값을 복사해주어야 하고
        반시계 회전인 경우엔 앞에서부터 값을 복사해주어야 한다.
 */
public class _14891 {

    static int[][] wheel = new int[5][8];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=1; i<=4; i++) {
            String status = br.readLine();
            for(int j=0; j<8; j++) {
                wheel[i][j] = Integer.parseInt(status.substring(j, j+1));
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            String[] line = br.readLine().split(" ");
            int num = Integer.parseInt(line[0]); // 톱니바퀴 번호
            int d = Integer.parseInt(line[1]); // 방향
            int leftPole = wheel[num][6]; // 왼쪽 극
            int rightRole = wheel[num][2]; // 오른쪽 극
            rotate(num, d);

            // 왼쪽 톱니바퀴
            int curD = -d;
            for(int j=num-1; j>=1; j--) {
                if(leftPole == wheel[j][2]) {
                    break;
                }
                leftPole = wheel[j][6];
                rotate(j, curD);
                curD = -curD;
            }

            // 오른쪽 톱니바퀴 회전
            curD = -d;
            for(int j=num+1; j<=4; j++) {
                if(rightRole == wheel[j][6]) {
                    break;
                }
                rightRole = wheel[j][2];
                rotate(j, curD);
                curD = -curD;
            }
        }

        int sum = 0;
        for(int i=1; i<=4; i++) {
            if(wheel[i][0] == 1) {
                sum += Math.pow(2, i-1);
            }
        }
        System.out.println(sum);

    }

    public static void rotate(int num, int d) {
        if(d == 1) { // 시계 방향 회전
            int tmp = wheel[num][7];
            for(int i=7; i>=1; i--) {
                wheel[num][i] = wheel[num][i-1];
            }
            wheel[num][0] = tmp;
        } else { // 반시계 방향
            int tmp = wheel[num][0];
            for(int i=0; i<7; i++) {
                wheel[num][i] = wheel[num][i+1];
            }
            wheel[num][7] = tmp;
        }
    }
}
