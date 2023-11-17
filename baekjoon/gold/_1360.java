package baekjoon;

import java.io.*;

/*
문제: 1360 되돌리기
난이도: Gold 5
알고리즘: 구현
풀이 방법: 수행된 시간과 그 때의 문자열 상태를 TimeStamp 라는 클래스를 만들어 저장하도록 한다.
        undo 수행 시엔 이제까지 시간과 상태를 저장해놓았으므로 되돌려야 할 마지막 수행 시간을 구하여 그보다 더 빨리 수행된
        마지막 명령의 상태로 돌아가면 된다.
 */
public class _1360 {

    static class TimeStamp {
        String status; // 문자열 상태
        int time; // 수행된 시간
        public TimeStamp(String status, int time) {
            this.status = status;
            this.time = time;
        }
    }

    static TimeStamp[] timeStamps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        timeStamps = new TimeStamp[N+1];
        timeStamps[0] = new TimeStamp("", 0); // 0초 빈 문자열 세팅
        for(int i=1; i<N+1; i++) {
            String[] command = br.readLine().split(" ");

            if(command[0].equals("type")) { // type 명령이면
                // 직전 수행된 명령의 문자열 상태에 문자를 붙여 삽입
                timeStamps[i] = new TimeStamp(timeStamps[i-1].status + command[1], Integer.parseInt(command[2]));

            } else { // undo 명령이면
                // 수행될 시간에서 되돌릴 시간 t 를 빼서 되돌려야 하는 마지막 수행 시간을 찾음
                int range = Integer.parseInt(command[2]) - Integer.parseInt(command[1]);
                undo(i, range, Integer.parseInt(command[2])); // 되돌리기 수행
            }
        }

        System.out.println(timeStamps[N].status);
    }

    // 되돌리기 수행
    public static void undo(int i, int range, int time) {
        boolean back = false; // 되돌릴 상태가 있는 지 여부
        // 되돌아 갈 timestamp 상태를 찾음
        for(int j=i-1; j>=0; j--) {
            // 되돌려야 마지막 수행 시간보다 수행된 시간이 작아지는 timestamp 가 되돌아 갈 곳
            if(timeStamps[j].time < range) {
                timeStamps[i] = new TimeStamp(timeStamps[j].status, time);
                back = true;
                break;
            }
        }
        // 되돌아 갈 곳이 없다면 빈 문자열 삽입
        if(!back) {
            timeStamps[i] = new TimeStamp("", time);
        }
    }
}
