import java.util.*;
/*
문제: 디펜스 게임
난이도: Level 2
알고리즘: 그리디
풀이 방법: 지금까지 가장 적의 수가 많았던 라운드에 스킬을 사용하는 그리디 알고리즘으로 해결한다.
        최대힙 PriorityQueue 를 사용하여 라운드를 진행하면서 pq에 적의 수를 삽입한다.
        주의할 것은 현재 라운드의 적이 가장 큰 수가 될 수 있으므로 스킬 사용 전에 pq에 삽입하도록 해야 한다.
        만약, 현재 라운드의 적이 병사보다 많다면 큐에서 가장 큰 적의 수를 빼어 스킬을 사용, 즉 적의 수만큼 병사를 더해준다.
        탈출 조건은 병사가 적보다 적은데 스킬이 없다면 탈출하도록 한다.
*/
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 적의 수 내림차순

        for(int i=0; i<enemy.length; i++) {
            // 병사가 더 적고 스킬도 없다면 종료
            if(n < enemy[i] && k == 0) break;
            pq.add(enemy[i]); 
            if(n < enemy[i]) { // 병사가 적다면 가장 적이 많았던 라운드에 무족권을 사용하고 해당 적만큼 병사를 더함
                n += pq.poll();
                k--;
            }
            n -= enemy[i];
            answer++;
        }
        
        return answer;
    }
}
