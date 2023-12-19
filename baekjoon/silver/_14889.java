package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
문제: 14889 스타트와 링크
난이도: Silver 1
알고리즘: 완전 탐색, 백트래킹
풀이 방법: 스타트팀으로 뽑히는 조합을 구한 뒤 스타트와 링크팀의 차이를 구한다.
        이때, 조합을 재귀를 이용하여 구한다.
 */
public class _14889 {

    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[][] S;
    static Stack<Integer> start = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];

        // S 입력 받기
        for(int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                S[i][j] = Integer.parseInt(line[j]);
            }
        }
        pick(0);
        System.out.println(answer);
    }

    public static void pick(int index) {
        // N/2 개를 모두 뽑았으면
        if(start.size() == N/2) {
            List<Integer> link = new ArrayList<>();
            for(int i=0; i<N; i++) {
                if(!start.contains(i)) { // 스타트팀에 들어가지 않았다면
                    link.add(i); // 링크팀에 삽입
                }
            }
            answer = Math.min(answer, diff(start, link));
            return;
        }

        for(int i=index; i<N; i++) {
            // i번째 사람을 group1에 픽
            start.push(i);
            pick(i+1);
            start.pop(); // 다시 빼기
        }
    }

    //  두 그룹 차이 구하기
    public static int diff(List<Integer> start, List<Integer> link) {
        int sum1 = sum(start);
        int sum2 = sum(link);
        return Math.abs(sum1 - sum2); // 두 그룹 차이
    }

    // 그룹에서의 S 합 구하기
    public static int sum(List<Integer> group) {
        int sum = 0;
        for(int i=0; i<group.size(); i++) {
            int x = group.get(i);
            for(int j=0; j<group.size(); j++) {
                int y = group.get(j);
                sum += S[x][y];
            }
        }
        return sum;
    }
}
