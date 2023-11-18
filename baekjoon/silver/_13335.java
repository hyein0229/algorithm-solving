package baekjoon;

import java.io.*;
import java.util.*;

/*
문제: 13335 트럭
난이도: Silver 1
알고리즘: 구현, 시뮬레이션, 자료구조, 큐
풀이 방법: 큐를 이용한다. 대기 중인 트력의 큐를 만들고 다리 위를 건너는 트럭의 큐를 만들고 대기열에는 입력되는 모든 트력을 삽입한다.
        다리 위에 있는 트럭들을 매초마다 앞으로 1씩 이동시킨다. 다리 뒤에 있는 맨 앞 트럭이 다리를 다 건넜다면 제거한다.
        대기열에서 맨 앞 트럭이 다리 위를 올라갈 수 있는지 확인(최대하중, 다리 길이 비교)하여 조건을 만족한다면 대기열에서 빼내어 다리 위 트럭에 추가한다.
        시간을 증가시키면서 트럭이 모두 건널때까지 반복한다.
 */
public class _13335 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lines = br.readLine().split(" ");
        int n = Integer.parseInt(lines[0]); // 다리를 건너는 트럭의 수
        int w = Integer.parseInt(lines[1]); // 다리의 길이
        int l = Integer.parseInt(lines[2]); // 다리의 최대하중
        Queue<Integer> readyTrucks = new LinkedList<>(); // 대기 중인 트럭
        Queue<int[]> moveTrucks = new LinkedList<>(); // 다리 위를 건너는 트럭

        lines = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            readyTrucks.add(Integer.parseInt(lines[i])); // 대기 중인 트럭 큐에 삽입
        }

        int time = 0; // 총 걸린 시간
        int totalWeight = 0; // 다리 위 트럭들의 총 무게 합
        while(!readyTrucks.isEmpty() || !moveTrucks.isEmpty()) { // 모든 트럭이 다 건널 때까지
            // 다리 위 트럭의 위치를 1만큼 앞으로 옮김
            for(int[] truck : moveTrucks) {
                truck[1] -= 1;
            }

            // 맨 앞 트럭이 다리를 다 건너면 제거
            if(!moveTrucks.isEmpty() && moveTrucks.peek()[1] == 0) {
                totalWeight -= moveTrucks.poll()[0];
            }

            // 맨 앞 대기 중인 트럭이 다리를 건널 수 있는 지 최대하중과 다리의 길이 비교
            if(!readyTrucks.isEmpty() && readyTrucks.peek() + totalWeight <= l && moveTrucks.size() < w) {
                int weight = readyTrucks.poll(); // 현재 트럭의 무게
                totalWeight += weight; // 다리 위 트럭의 무게에 추가
                moveTrucks.add(new int[]{weight, w}); // 다리 위 트럭에 삽입
            }
            time++;

        }
        System.out.println(time);
    }
}
