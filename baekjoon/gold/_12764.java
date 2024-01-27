package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 12764 싸지방에 간 준하
난이도: Gold 3
알고리즘: 구현, 자료 구조, 시뮬레이션, 우선순위 큐
풀이 방법: 우선순위 큐를 3개 사용한다.
       1. 대기하는 사용자 큐 -> 시작 시각이 빠른 순 기준
       2. 사용 중인 컴퓨터 큐 -> 종료 시각이 빠른 순 기준
       3. 비어 있는 컴퓨터 큐 -> 컴퓨터 번호가 작은 기준
       사용자 큐에서 사용자를 꺼낸 다음 사용 중인 컴퓨터 큐에서 현재 사용자의 시작 시각보다 종료 시각이 더 작은
       사용 가능한 컴퓨터들을 찾아 비어 있는 컴퓨터 큐에 삽입한다.
       그 다음 비어 있는 컴퓨터 큐가 empty 라면 사용 가능한 컴퓨터가 없다는 것이므로 X값을 늘려 컴퓨터를 증설한다.
       empty 가 아니면 큐에서 꺼낸 컴퓨터 번호가 사용자가 사용할 컴퓨터 번호가 된다.
 */
public class _12764 {

    static class User {
        int start;
        int end;

        public User(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<User> users = new PriorityQueue<>(Comparator.comparing(user -> user.start)); // 사용자 시작 시각 빠른 순
        PriorityQueue<int[]> usingComs = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // 종료 시각 빠른 순
            }
        });
        PriorityQueue<Integer> emptyComs = new PriorityQueue<>(); // 컴퓨터 번호 작은 순

        for(int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            users.add(new User(start, end));
        }

        int X = 0; // 컴퓨터 개수
        int[] useCnt = new int[N+1];
        while(!users.isEmpty()) {
            User user = users.poll();
            // 지금 사용할 수 있는 컴퓨터 찾기
            while(!usingComs.isEmpty()) {
                int[] com = usingComs.peek();
                int num = com[0];
                int endTime = com[1];
                if(endTime <= user.start) {
                    emptyComs.add(num);
                    usingComs.poll();
                } else {
                    break;
                }
            }

            // 비어있는 컴퓨터가 없다면 컴퓨터 증설
            if(emptyComs.isEmpty()) {
                usingComs.add(new int[]{++X, user.end});
                useCnt[X]++;

            } else {
                int num = emptyComs.poll();
                useCnt[num]++;
                usingComs.add(new int[]{num, user.end});
            }
        }

        System.out.println(X);
        for(int i=1; i<=X; i++) {
            System.out.print(useCnt[i] + " ");
        }
    }
}
