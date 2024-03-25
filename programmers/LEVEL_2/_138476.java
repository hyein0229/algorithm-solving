import java.util.*;
/*
문제: 귤 고르기
난이도: Level 2
알고리즘: 우선순위 큐, 그리디 
풀이 방법: 서로 다른 종류가 최소 --> 개수가 가장 많은 종류부터 우선 고르면 됨, 그리디로 해결하였다.
    먼저, 배열을 순회하면서 종류별로 카운트한 수를 Map에 저장해준 후
    PriorityQueue를 사용하여 종류별 귤 개수를 내림차순으로 정렬되도록 하였다.
    pq에서 하나씩 꺼내어 개수를 더해주면서 k개 이상이 되면 종료한다.
*/
class Solution {

    public int solution(int k, int[] tangerine) {
        int typeCnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // 크기 종류별로 나누어 카운트
        for(int i=0; i<tangerine.length; i++) {
            int size = tangerine[i];
            if(!map.containsKey(size)) {
                map.put(size, 1);
            } else {
                map.put(size, map.get(size) + 1); // 카운트 증가
            }
        }
        
        for(Integer size : map.keySet()){ // 종류별 카운트한 과일 수 삽입
            pq.add(map.get(size));
        }
    
        int total = 0; // 총 고른 개수
        while(!pq.isEmpty()) {
            int cnt = pq.poll();
            total += cnt;
            typeCnt++; // 서로 다른 종류 개수
            if(total >= k) { // k개 이상 골랐으면 종료
                break;
            }
        }
        return typeCnt;
    }
}
