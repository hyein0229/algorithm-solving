package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17178 줄서기
난이도: Gold 5
알고리즘: 구현, 시뮬레이션, 자료구조
풀이 방법: 티켓 번호 순대로 정렬이 필요하므로 우선순위 큐와 Comparable 을 사용하여 티켓을
        정렬해주었다. 대기자들은 맨 앞 사람부터 이동하므로 Queue 를 사용하고 대기 공간은
        마지막 사람이 먼저 이동하므로 Stack 을 사용한다.
        1. 우선순위 큐 pq 의 peek가 대기자 큐의 peek 와 같으면 바로 이동
        2. 입장이 불가하면 대기 공간 Stack 으로 삽입하는데, 이때 삽입 전
            대기 공간의 마지막 사람과 pq 의 peek를 비교하여 같으면 계속 입장시킨다.
            그 후, Stack 에 삽입한다.
        3. 주의, 대기자 큐가 empty 상태여도 대기 공간에 대기 중인 사람들이 있으므로
           마지막에 꼭 대기 공간의 마지막 사람과 비교하여 입장 순서가 맞는대로 입장시켜야 한다.
 */
public class _17178 {

    public static class Ticket implements Comparable<Ticket> {
        String alpha;
        int number;

        public Ticket(String alpha, int number) {
            this.alpha = alpha;
            this.number = number;
        }

        @Override
        public int compareTo(Ticket o) {
            if(this.alpha.equals(o.alpha)) { // 번호 작은 순대로
                return this.number - o.number;
            }
            return this.alpha.compareTo(o.alpha); // 알파벳 앞서는 순서대로
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Ticket> pq = new PriorityQueue<>(); // 티켓 번호 정렬
        Queue<Ticket> entry = new LinkedList<>();// 기다리고 있는 사람들

        // 대기자 입력
        for(int i=0; i<N; i++) {
            String[] row = br.readLine().split(" ");
            for(int j=0; j<5; j++) {
                String[] t = row[j].split("-");
                String alpha = t[0];
                int number = Integer.parseInt(t[1]);
                Ticket ticket = new Ticket(alpha, number);
                pq.add(ticket);
                entry.add(ticket);
            }
        }

        Stack<Ticket> waitingLine = new Stack<>(); // 대기 줄
        while(!entry.isEmpty()) {
            Ticket ticket = entry.poll(); // 맨 앞 사람 이동
            if(isSame(ticket, pq.peek())) { // 현재 입장 순서면 바로 입장
                pq.poll();

            } else { // 대기 공간에 다시 줄 섬
                // 대기 공간 마지막 사람이 입장 순서에 맞으면 입장시킴
                while(!pq.isEmpty() && !waitingLine.isEmpty() && isSame(pq.peek(), waitingLine.peek())) {
                    pq.poll();
                    waitingLine.pop();
                }
                waitingLine.push(ticket); // 대기 줄에 대기시킴
            }
        }

        while(!pq.isEmpty() && !waitingLine.isEmpty() && isSame(pq.peek(), waitingLine.peek())) {
            pq.poll();
            waitingLine.pop();
        }

        if(pq.isEmpty()) {
            System.out.println("GOOD");
        } else {
            System.out.println("BAD");
        }
    }

    public static boolean isSame(Ticket t1, Ticket t2) {
        if(t1.alpha.equals(t2.alpha) && t1.number == t2.number) {
            return true;
        }
        return false;
    }
}
