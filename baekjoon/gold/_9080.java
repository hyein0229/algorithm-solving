package baekjoon;

import java.io.*;
/*
문제: 9080 PC방 요금
난이도: Gold 4
알고리즘: 구현, 시뮬레이션
풀이 방법: 최소 지불하기 위해선 최대한 야간 정액을 쓸 수 있으면 써야 한다.
        따라서, 현재 시작 시간이 10시부터 8시 사이라면 8시까지 게임할 수 있는 시간을 계산하여 5시간 이상일 때
        5000원을 지불하여 야간 정액을 쓸 수 있도록 한다. 그 외의 시간인 경우엔 모두 한 시간씩 감소시키면서
        1000원을 지불하도록 하였다.
 */
public class _9080 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            String[] line = br.readLine().split(" ");
            String[] time = line[0].split(":");
            int curTime = (60 * Integer.parseInt(time[0])) + Integer.parseInt(time[1]); // 시작 시간
            int gameTime = Integer.parseInt(line[1]);

            int totalCost = 0;
            while(gameTime > 0) {
                // 야간 정액을 쓸 수 있는 시간 밤 10시 ~ 아침 8시면
                if(curTime >= 1320 || curTime < 480) {
                    int usingTime; // 8시까지 사용할 수 있는 시간
                    if(curTime >= 0 && curTime < 480)  usingTime = Math.min(480 - curTime, gameTime);
                    else usingTime = Math.min((1440 - curTime) + 480, gameTime);

                    if(usingTime >= 300) { // 5시간 이상 사용할 수 있다면
                        gameTime -= usingTime;
                        curTime = (curTime + usingTime) % 1440;
                        totalCost += 5000;
                        continue;
                    }
                }
                // 야간 시간대까진 모두 한 시간에 천원씩
                curTime = (curTime + 60) % 1440;
                gameTime -= 60;
                totalCost += 1000;
            }
            System.out.println(totalCost);
        }
    }
}
