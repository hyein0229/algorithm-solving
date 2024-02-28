package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 23843 콘센트
난이도: Gold 5
알고리즘: 그리디, 우선순위 큐, 정렬
풀이 방법: 최소힙 우선순위 큐를 사용하여 콘센트에 충전 중인 전자 기기의 충전이 가장 빨리 끝나는 순대로
    꺼낼 수 있도록 한다. 이때, 주의할 것은 전자기기를 충전에 필요한 시간이 큰 순대로 내림차순 정렬을 해야 한다.
    예제 1을 예로 들면 정렬을 안할 경우 두번째 전자 기기가 끝난 자리에 네번째 전자기기를 충전하게 되어
    최소 시간이 4 + 8 = 12가 된다. 즉, 충전 시간이 큰 것이 충전 시간을 많이 기다린 후 충전하게 되어 시간이 커진다.
    따라서, 충전 시간이 큰 것부터 우선으로 충전한 후 작은 것을 충전하게 해야 한다.
 */
public class _23843 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        Long[] arr = new Long[N];
        PriorityQueue<Long> sockets = new PriorityQueue<>(); // 충전이 빨리 끝나는 순

        line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(line[i]);
        }
        Arrays.sort(arr, Collections.reverseOrder());

        int idx = 0;
        while(idx < N) {
            long t = arr[idx]; // 현재 전자기기
            if(sockets.size() < M) { // 아직 콘센트가 남아 있으면
                sockets.add(t);
            } else {
                long endTime = sockets.poll(); // 끝난 충전을 뺌
                sockets.add(endTime + t); // 다시 새로운 것을 충전
            }
            idx++;
        }

        long answer = 0;
        while(!sockets.isEmpty()) {
            answer = sockets.poll(); // 가장 마지막에 끝나는 충전이 정답
        }
        System.out.println(answer);
    }
}
