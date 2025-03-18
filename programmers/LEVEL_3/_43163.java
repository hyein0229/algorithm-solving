import java.util.*;
/*
문제: 43163 단어 변환
난이도: Level 2
알고리즘: 구현, bfs
풀이 방법: bfs 를 사용하여 풀이하였다. 단어와 변환 횟수를 저장할 수 있는 Transition 객체를 Queue 에 넣어준다.
        큐가 빌때까지 poll 하면서 현재 단어에서 words 배열 단어 중 변환이 가능한 단어를 찾아 변환 횟수를 1 증가
        시킨 Transition 객체를 다시 큐에 넣는 것을 반복한다.
        현재 단어가 target 이 되면 정답을 갱신하고 탐색을 종료한다.
*/
class Solution {
    
    class Transition {
        String word; // 현재 단어
        int cnt; // 변환 횟수
        
        public Transition(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Queue<Transition> q = new LinkedList<>();
        Set<String> set = new HashSet<>(); // 방문 체크를 위한 set 선언
        q.add(new Transition(begin, 0)); // 시작 단어부터 큐에 삽입
        set.add(begin);  
        
        while(!q.isEmpty()) {
            Transition t = q.poll();
            String curWord = t.word;
            int curCnt = t.cnt;
            // 타켓에 도달했다면 탈출
            if(curWord.equals(target)) {
                answer = curCnt;
                break;
            }
            // 현재 단어에서 변환할 수 있는 단어 찾기
            for(String word : words) {
                int diffCnt = 0; // 서로 틀린 문자 개수 카운트
                boolean possible = true; // 변환 가능 여부
                for(int i=0; i<word.length(); i++) {
                    if(word.charAt(i) != curWord.charAt(i)) {
                        if(diffCnt == 1) { // 이미 한 문자가 다르다면 변환 불가
                            possible = false;
                            break;
                        } else diffCnt++;
                    }
                }
                // 변환이 가능하다면
                if(possible) {
                    if(!set.contains(word)) {
                        q.add(new Transition(word, curCnt+1));
                        set.add(word); // 방문 체크
                    }
                }
            }
        }
        return answer;
    }
}
