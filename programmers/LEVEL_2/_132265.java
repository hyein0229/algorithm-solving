import java.util.*;
/*
문제: 롤케이크 자르기
난이도: Level 2
풀이방법: 누적합을 이용하였다. 만약 위치 i를 기준으로 잘라 0~i까지, i+1~끝 까지 두 그룹으로 나뉜다고 하면
        두 그룹 안에서의 종류 개수가 필요하다. 따라서, 왼쪽부터 종류를 누적시키면서 카운트하는 leftCnt와
        오른쪽부터 시작하여 카운트하는 rightCnt 두 개의 배열을 선언하여 각 위치까지의 종류 개수를 저장해주면
        각각 두 그룹으로 나누었을 때 특정 위치까지 누적된 종류 개수를 알 수 있다.
        두 수가 같으면 공평하다.
*/
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int n = topping.length;
        int[] leftCnt = new int[n]; 
        int[] rightCnt = new int[n];
        Set<Integer> set = new HashSet<>();
        
        if(n == 1) { // 토핑이 하나면 나눌 수 없음
            return 0;
        }
        
        leftCnt[0] = 1;
        set.add(topping[0]);
        // 왼쪽부터 종류 누적 카운트
        for(int i=1; i<n; i++) {
            if(set.contains(topping[i])) { // 이미 존재하는 타입이면
                leftCnt[i] = leftCnt[i-1];
            } else {
                set.add(topping[i]);
                leftCnt[i] = leftCnt[i-1] + 1; // 종류 하나 증가
            }
        }
        
        set.clear();
        rightCnt[n-1] = 1;
        set.add(topping[n-1]);
        // 오른쪽부터 종류 누적 카운트
        for(int i=n-2; i>=0; i--) {
            if(set.contains(topping[i])) { 
                rightCnt[i] = rightCnt[i+1];
            } else {
                set.add(topping[i]);
                rightCnt[i] = rightCnt[i+1] + 1; // 종류 하나 증가
            }
        }
        
        for(int i=0; i<n-1; i++) {
            // i까지 왼쪽, i+1부터 오른쪽으로 나눔
            int left = leftCnt[i]; // i까지 종류 개수
            int right = rightCnt[i+1]; // i+1부터 끝까지 종류 개수
            if(left == right) { // 두 그룹의 종류 개수가 같아야 공평
                answer++;
            }
        }
        return answer;
    }
}
