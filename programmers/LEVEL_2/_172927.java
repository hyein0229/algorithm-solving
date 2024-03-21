import java.util.*;
/*
문제: 광물 캐기
난이도: Level 2
알고리즘: 백트래킹
풀이 방법: 백트래킹을 활용하여 완전탐색으로 해결하였다. 곡괭이를 아무거나 선택할 수 있으므로
    picks 값이 현재 0보다 큰 존재하는 곡괭이를 골라 선택한 곡괭이의 picks 값은 하나 감소시킨다.
    해당 곡괭이로 5개의 광물을 캔 후 피로도를 누적시켜가며 다시 함수를 재귀 호출하여 반복한다. 
    호출이 끝난 후엔 picks 값을 다시 증가시켜 백트래킹하고 다른 경우의 수로 또 탐색하도록 한다.
    매개변수로 현재 캐야할 첫번째 광물 인덱스 idx를 넘겨 idx가 배열 크기 이상이거나 곡괭이가
    모두 없으면 깊이 탐색을 끝낸다.
*/
class Solution {
    int answer = Integer.MAX_VALUE;
    Map<String, Integer> map = new HashMap<>();
    public int solution(int[] picks, String[] minerals) {
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        pick(picks, minerals, 0, 0);
        return answer;
    }

    public void pick(int[] picks, String[] minerals, int idx, int total) {
        boolean exist = false;
        for(int i=0; i<3; i++) {
            if(picks[i] > 0) { // 곡괭이가 존재하면
                exist = true;
                break;
            }
        }
        // 광산에 있는 모든 광물을 캐거나, 더 사용할 곡괭이가 없을 때
        if(!exist || idx >= minerals.length) {
            answer = Math.min(answer, total);
            return;
        }
        
        for(int i=0; i<3; i++) {
            if(picks[i] > 0) { 
                picks[i]--; // 해당 곡괭이 사용
                int sum = 0; // 광물을 5개 연속 캔 후 피로도 합
                for(int j=idx; j<idx+5; j++) {
                    if(j >= minerals.length) {
                        break;
                    }
                    sum += Math.pow(5, Math.max(0, i - map.get(minerals[j])));
                }
                pick(picks, minerals, idx+5, total+sum);
                picks[i]++;
            }
        }
    }
}
