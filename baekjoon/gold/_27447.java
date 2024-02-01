package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 27447 주문은 토기입니까?
난이도: Gold 5
알고리즘: 구현, 그리디 알고리즘, 시뮬레이션
풀이 방법: 1. 손님이 도착하는 시간에는 무조건 서빙 -> 커피가 준비되어 있으면 서빙하고, 아니면 실패이다.
         2. 그 다음 손님들을 위해 미리 커피를 준비해 놓을 수 있는지 확인
            2-1 손님이 t시간에 도착 한다고 하면 t에 서빙하여 서빙완료 시간은 t+1
            2-2 i 시간에 커피를 담는다고 하면 i+1에 커피 담기 완료, M초 후인 i+1+M 안에 서빙이 완료되어야 한다.
            2-3 따라서, t+1 <= i+M+1 여야 하므로 t <= i+M 이면 i시간에 커피를 미리 담아 놓을 수 있다.
            2-4 토기가 없다면 토기 수 증가, 토기가 있다면 커피 수 증가, 토기를 감소시키고 그 다음 손님 인덱스로 증가시킨다.
         3. 위에 해당하지 않는다면 할게 없으므로 토기나 만들기
 */
public class _27447 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] customers = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int t = Integer.parseInt(st.nextToken());
            customers[i] = t;
        }

        int togi = 0; // 현재 만들어져 있는 토기 수
        int coffee = 0; // 현재 토기에 담아놓은 커피 수
        int servingTarget = 0; // 현재 시점에서 가장 먼저 도착하는 손님
        int customer_idx = 0;
        boolean success = true;
        for(int t=0; t<customers[N-1]+1; t++) {
            // 손님이 도착하면 서빙해야 함
            if(customers[servingTarget] == t) {
                if(coffee > 0) { // 커피가 담긴 토기가 있다면
                    servingTarget++;
                    coffee--;
                } else {
                    success = false;
                    break;
                }
                // 지금 커피를 담아놓을 수 있으면 미리 커피 준비
            } else if(customer_idx < N && customers[customer_idx] <= t+M) {
                // 토기가 없으면 토기 만들기
                if(togi == 0) {
                    togi++;
                } else { // 토기가 있으면 커피 담기
                    coffee++;
                    togi--; // 토기 사용
                    customer_idx++;
                }
            } else {
                togi++; // 뭐 없으면 토기 만들기
            }

        }

        if(success) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }
}
