package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 20665 독서실 거리두기
난이도: Gold 4
알고리즘: 구현, 시뮬레이션
풀이 방법: 우선순위 큐를 사용하여 더 빠른 시간을 예약한 순, 이용시간이 짧은 순으로 정렬 한뒤 에약자를 한 명씩 꺼내어 배정한다.
        자리를 배정할 땐 좌석 번호를 오름차순으로 정렬하여 가장 작은 번호에 앉아있는 사람과 1번과의 거리, 두 좌석 사이에 앉을 때의 거리,
        가장 큰 번호에 앉아있는 마지막 사람과 N번과의 거리 3가지를 고려하여 그 중 가장 먼 거리가 나오는 좌석에 배정하도록 하였다.
        배정한 자리는 Map 을 하나 생성하여 key를 자리 번호, value 를 시작 시간, 끝 시간으로 하여 저장해주도록 하였다.
        오히려 이상하게 관리자의 이용시간을 구하는 방법이 더 헷갈렸다..
        가장 쉬운 방법은 운영 시간 9~21시 까지 총 720 분에서 P자리가 사용될 때마다 사용되는 시간을 빼주는 것이다.
 */
public class _20665 {

    static class Reservation implements Comparable<Reservation>{
        int start;
        int end;

        public Reservation(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Reservation o) {
            if(this.start == o.start) {
                return this.end - o.end; // 같은 시간에 예약했다면 짧은 이용 시간을 가진 순
            }
            return this.start - o.start; // 더 빨리 예약한 순
        }
    }

    static int N, T, P;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        T = Integer.parseInt(line[1]);
        P = Integer.parseInt(line[2]);
        PriorityQueue<Reservation> pq = new PriorityQueue<>();

        for(int i=0; i<T; i++) {
            line = br.readLine().split(" ");
            int sh = Integer.parseInt(line[0].substring(0, 2));
            int sm = Integer.parseInt(line[0].substring(2));
            int eh = Integer.parseInt(line[1].substring(0, 2));
            int em = Integer.parseInt(line[1].substring(2));
            pq.add(new Reservation((sh * 60) + sm, (eh * 60) + em)); // 분 단위로 바꾸어 삽입
        }

        Map<Integer, Reservation> usingSeats = new HashMap<>(); // 사용 중인 좌석
        int adminTime = 720; // 관리자 사용 시간
        while(!pq.isEmpty()) {
            Reservation reservation = pq.poll();
            int curTime = reservation.start;

            // 예약 시간이 끝난 예약자의 좌석은 제거
            List<Integer> seatNums = new ArrayList<>(usingSeats.keySet());
            for(Integer num : seatNums) {
                int start = usingSeats.get(num).start;
                int end = usingSeats.get(num).end;
                if(curTime >= end) {
                    if(num == P) { // p번 좌석인 경우
                        adminTime -= (end - start); // p번 예약자의 이용 시간만큼 뺌
                    }
                    usingSeats.remove(num);
                }
            }
            // 독서실을 이용하는 사람이 없는 경우 1번 자리 앉음
            if(usingSeats.isEmpty()) {
                usingSeats.put(1, reservation);
            } else { // 있는 경우 좌석 골라서 앉기
                int selectNum = selectSeat(usingSeats);
                usingSeats.put(selectNum, reservation);
            }
        }

        if(usingSeats.containsKey(P)) {
            adminTime -= (usingSeats.get(P).end - usingSeats.get(P).start);
        }
        System.out.println(adminTime);
    }

    public static int selectSeat(Map<Integer, Reservation> usingSeats) {
        int select = 0;
        int maxDist = Integer.MIN_VALUE;
        List<Integer> seats = new ArrayList<>(usingSeats.keySet());
        Collections.sort(seats); // 좌석 번호를 오름차순으로 정렬
        int firstSeat = seats.get(0);
        int lastSeat = seats.get(seats.size()-1);

        // 1번의 앉았을 때 가장 가까운 사람과의 거리
        if(firstSeat != 1) {
            int dist = firstSeat - 1;
            if(dist > maxDist) {
                maxDist = dist;
                select = 1;
            }
        }
        // 사람과 사람 사이에 앉을 때의 거리
        for(int i=1; i<seats.size(); i++) {
            int dist = (seats.get(i) - seats.get(i-1)) / 2;
            if(dist > maxDist) {
                maxDist = dist;
                select = seats.get(i-1) + dist;
            }
        }

        // N번에 앉았을 때 가장 가까운 사람과의 거리
        if(lastSeat != N) {
            int dist = N - lastSeat;
            if(dist > maxDist) {
                select = N;
            }
        }
        return select;
    }
}
