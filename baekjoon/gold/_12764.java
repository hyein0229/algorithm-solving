package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 12764 싸지방에 간 준하
난이도: Gold 3
알고리즘: 구현, 자료 구조, 시뮬레이션, 우선순위 큐
풀이 방법: 사람이 빨리 들어오는 대로 컴퓨터를 차지하므로 우선순위 큐를 이용하여 시작 시각을 오름차순으로 정렬한 뒤
        한 명씩 꺼내면서 컴퓨터를 배치해주어야 겠다고 생각했다. 각 컴퓨터 사용 종료 시각을 저장하기 위해 배열을 선언하고
        배열을 앞에서부터 순회하면서 종료 시각보다 현재 사람의 시작 시각이 크다면 컴퓨터가 사용 시각이 겹치지 않는 것이므로
        해당 컴퓨터에 그 사람을 배치시키고 종료 시각을 갱신해주었다.
        만약 이때 컴퓨터에 배치하지 못할 경우 새로운 컴퓨터를 증설한다. 이때, X의 값을 증가시킨다.
 */
public class _12764 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> Q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 시작 시각 오름차순
            }
        });

        for(int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            Q.add(new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])});
        }

        int[] useCnt = new int[N];
        int[] computers = new int[N];
        int X = 0; // 컴퓨터 개수
        while(!Q.isEmpty()) {
            int[] person = Q.poll();

            boolean flag = false;
            for(int i=0; i<X; i++) {
                if(computers[i] <= person[0]) {
                    computers[i] = person[1];
                    useCnt[i]++;
                    flag = true;
                    break;
                }
            }
            // 컴퓨터 증설
            if(!flag) {
                computers[X] = person[1];
                useCnt[X]++;
                X++;
            }
        }

        System.out.println(X);
        for(int i=0; i<X; i++) {
            System.out.print(useCnt[i] + " ");
        }
    }
}
