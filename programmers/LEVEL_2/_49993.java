import java.util.*;
/*
문제: 49993 스킬트리
난이도: Level 2
알고리즘: 구현
풀이 방법: 먼저, 선행 스킬 순서 skill 의 스킬을 순회하면서 순서대로 <스킬, 순서번호> 를 map 에 저장한다.
        스킬트리 배열을 돌면서 각 스킬트리에 대해 skill과 비교하며 가능 여부를 확인하는 데,
        첫 선행 순서의 스킬부터 배워야 하므로 skill_idx = 0 부터 시작하여
        현재 스킬트리의 문자가 skill에 있는 문자면 skill_idx와 비교하여 같다면, 즉 현재 배워야할 순서가 맞다면
        skill_idx 를 증가시켜 계속 탐색하고, 아니면 순서가 틀린 것이므로 possible 을 false로 바꾸고
        바로 탈출한다. 현재 스킬트리의 탐색이 끝난 후에 possible 이 여전히 true 라면 cnt 를 증가시킨다.
*/
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int cnt = 0;
        Map<Character, Integer> map = new HashMap<>();
        // <스킬명, 순서> 를 키, 값으로 하여 map 에 저장
        for(int i=0; i<skill.length(); i++) {
            map.put(skill.charAt(i), i);
        }
        // 입력 받은 모든 스킬트리에 대해 가능 여부 확인
        for(String st : skill_trees) {
            int skill_idx = 0; // 현재 배워야 할 스킬 순서 번호
            boolean possible = true; // 스킬트리의 가능 여부
            for(int i=0; i<st.length(); i++) {
                char cur = st.charAt(i);
                // 현재 스킬이 선행 순서 스킬에 포함된 스킬이면
                if(map.containsKey(cur)) {
                    if(map.get(cur) == skill_idx) { // 선행 순서가 맞는지 확인
                        skill_idx++;
                    } else { // 순서가 틀리면 불가능하므로 바로 탈출
                        possible = false;
                        break;
                    }
                }
            }
            if(possible) cnt++;
        }
        return cnt;
    }
}
