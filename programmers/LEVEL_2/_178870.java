/*
문제: 178870 연속된 부분 수열의 합
난이도: Level 2
알고리즘: 투포인터
풀이 방법: 두 인덱스 사이의 원소 합을 구해야 하므로 왼쪽, 오른쪽 투포인터를 사용하여 해결한다. 
        처음 left, right 인덱스를 0으로 초기화한 후 right를 하나씩 늘려가며 누적합 sum 을 구한다.
        이때, 만약 합이 k이면 그대로 minLen 을 갱신한다.
        합이 k를 초과하기 시작하면 k보다 작거나 같아질 때까지 left를 계속 늘려준다.
*/

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int len = sequence.length;
        
        int left = 0;
        int right = 0;
        int sum = 0; 
        int minLen = Integer.MAX_VALUE;
        while(right < len) {
            sum += sequence[right];
            if(sum > k) { // k를 초과하면 왼쪽 포인터 간격을 줄이기
                while(sum > k) {
                    sum -= sequence[left];
                    left++;
                }
            }
            // k이면 정답 갱신
            if(sum == k) {
                if(minLen > right - left + 1) {
                    minLen = right - left + 1;
                    answer = new int[]{left, right};
                }
            } 
            right++;
        }
        
        return answer;
    }
}
