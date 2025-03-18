package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 13975 파일 합치기 3
난이도: Gold 4
알고리즘: 그리디, 우선순위 큐
풀이 방법: 최소 비용을 만들기 위해선 현재 파일들 중에 가장 크기가 작은 파일끼리 계속 합쳐나가면 된다.
        최소힙 우선순위 큐를 사용하여 먼저 현재 파일 크기들을 모두 넣어준 후 큐에 하나의 파일만
        남을 때까지 파일을 두 개씩 꺼내고, 두 개를 합친 파일의 크기를 다시 넣어주는 것을 반복해 나간다.
 */
public class _13975 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            int K = Integer.parseInt(br.readLine());
            PriorityQueue<Long> q = new PriorityQueue<>(); // 최소힙

            String[] line = br.readLine().split(" ");
            for(int j=0; j<K; j++) {
                q.add(Long.parseLong(line[j]));
            }

            long totalCost = 0;
            while(q.size() > 1) {
                // 최소 크기 두개
                long c1 = q.poll();
                long c2 = q.poll();
                totalCost += c1 + c2;
                q.add(c1 + c2);
            }
            System.out.println(totalCost);
        }
    }
}
