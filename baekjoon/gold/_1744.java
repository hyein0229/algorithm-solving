package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1744 수 묶기
난이도: Gold 4
알고리즘: 그리디, 정렬
풀이 방법: 우선순위 큐와 그리디 알고리즘을 이용하여 풀이하였다.
        최대가 되게 하려면 두 수를 곱하여 가장 큰 수가 되는 것부터 합해야 한다.
        양수는 최대힙 우선순위 큐를 이용하여 내림차순 정렬하고, 음수는 최소힙 우선순위 큐를 이용하여
        오름차순 정렬한다. (음수가 오름차순인 이유는 음수는 절댓값이 클수록 작기 때문, 절댓값이 가장 큰 것끼리
        묶어야 한다.) 각 큐에 숫자를 넣은 뒤, 두 개씩 빼어 곱하여 더해준다.
        이때 주의할 것은 양수인 경우, 1과 어떤 수를 곱하여 더하면 손해이므로 다음 값이 1인 경우엔 묶지 말고 따로 더해야 한다.
        음수인 경우 마지막 음수가 하나 남은 경우엔 0이 있으면 0과 묶어준다. 즉, 음수를 더하지 않는다.
 */
public class _1744 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusPq = new PriorityQueue<>();
        int zeroCnt = 0;
        // 수열 입력
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
                zeroCnt++;
                continue;
            }
            if(num > 0) plusPq.add(num);
            else if(num < 0) minusPq.add(num);
        }

        long sum = 0;
        while(!plusPq.isEmpty()) { // 양수 우선순위 큐 
            int cur = plusPq.poll();
            if(plusPq.isEmpty()){
                sum += cur;
                break;
            }
            int next = plusPq.peek();
            if(next != 1) { // 다음 값이 1이 아닌 경우에만 곱하여 더함
                plusPq.poll();
                sum += (cur * next);
            } else {
                sum += cur;
            }
        }

        while(!minusPq.isEmpty()) { // 음수 우선순위 큐
            int cur = minusPq.poll();
            if(minusPq.isEmpty()){
                if(zeroCnt > 0) { // 0이 있으면 0과 묶을 수 있음
                    break;
                }
                sum += cur;
                break;
            }
            int next = minusPq.poll();
            sum += (cur * next); // 음수 * 음수 = 양수이므로 무조건 합
        }
        System.out.println(sum);
    }
}
