import java.util.*;
/*
문제: 43165 타겟 넘버
난이도: Level 2
알고리즘: 구현, dfs
풀이 방법: 더하거나, 빼거나 2가지 방법으로 숫자를 만들 수 있다.
        dfs 를 사용하여 numbers 배열의 모든 숫자에 대해 순서대로 결과에 빼거나 더해주면서 재귀 호출한다. 
        배열에서의 인덱스 idx 값이 numbers 배열의 길이의 크기가 되면 모든 숫자를 다 사용하였으므로
        만들어진 숫자가 target 과 같은지 비교 후 return 하여 현재 탐색을 끝낸다.
*/
class Solution {
    int cnt = 0; // 방법의 수 카운트
    int[] numArr;
    public int solution(int[] numbers, int target) {
        numArr = numbers;
        dfs(0, 0, target, numbers.length); 
        return cnt;
    }
    
    public void dfs(int idx, int cur, int target, int limit) {
        // 배열의 모든 숫자를 모두 사용했다면
        if(idx == limit) {
            if(cur == target) cnt++; // 결과가 target과 같은지 확인하고 같다면 정답 카운트
            return;
        }
        dfs(idx+1, cur - numArr[idx], target, limit); // 뺀 경우
        dfs(idx+1, cur + numArr[idx], target, limit); // 더한 경우
    }
}
