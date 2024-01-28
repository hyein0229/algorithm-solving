package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 19638 센티와 마법의 뿅망치
난이도: Silver 1
알고리즘: 구현, 자료 구조, 우선순위 큐
풀이 방법: 센티는 매번 가장 키가 큰 거인을 때려야 하므로 뿅망치를 때릴 때마다 최대 키를 알아내야 한다.
        따라서, 최대힙 우선순위 큐를 생성하여 항상 가장 큰 키의 거인을 꺼내어 키가 1이 아니면 줄이고
        줄인 새로운 키를 다시 큐에 삽입하는 방식으로 구현하였다.
 */
public class _19638 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int H = Integer.parseInt(line[1]);
        int T = Integer.parseInt(line[2]);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<N; i++) {
            int height = Integer.parseInt(br.readLine());
            pq.add(height);
        }

        int time = 0;
        while(time < T && !pq.isEmpty()) {
            int height = pq.peek();
            if(height < H || height == 1) {
                break;
            }
            pq.add(pq.poll() / 2); // 뿅망치로 맞은 후 키 삽입
            time++;
        }

        if(pq.peek() < H) {
            System.out.println("YES");
            System.out.println(time);
        } else {
            System.out.println("NO");
            System.out.println(pq.peek());
        }
    }
}
