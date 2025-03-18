package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제: 19942 다이어트
난이도: Gold 4
알고리즘: 백트래킹
풀이 방법: 삽질 중 삽질을 하여 틀렸습니다의 늪에 빠진 문제...백트래킹 알고리즘은 문제 보자마자 생각이 났고 알고리즘 자체도 맞게 풀었다.
        i번째 식재료를 선택하는 경우, 선택하지 않는 경우 2가지에 따라 dfs 로 탐색하고 조건에 따라 백트래킹하는 전형적인 백트래킹 문제이다.
        삽질했던 곳은 바로 출력이다.. 현재까지 선택된 조합을 어떻게 저장할 것인가에 대해 문자열로 하는 것이 좋겠다 싶어 comb 라는 string
        변수에 i번째를 선택하는 경우 i를 붙여주고, 선택하지 않는 경우 붙이지 않고 재귀 호출한다. 이와 같은 경우엔
        백트래킹을 위해 앞서 추가한 요소에 대한 삭제가 따로 필요하지 않다. 그러나 string 출력 시 index 로 하나씩 접근하여
        출력 할 경우 만약 번호가 두자리인 경우, 즉 예시로 10 12 일 때 1 0 1 2 이렇게 잘못 출력된다는 것이다..이걸 거의 한시간동안 삽질했다..
        그래서 comb 변수에 i를 붙일 때 아예 공백을 사이에 두고 붙이고 마지막 정답 출력 시 trim 으로 양쪽 공백만 지우고 출력하였다.
        string 출력 시 이렇게 두자리 출력이 있는 경우 꼭 주의하자!!!!
 */
public class _19942 {
    static int N;
    static int minCost = 987654321;
    static String answer = "";
    static int[] standard;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        standard = new int[4];

        // 최소 영양성분 입력
        String[] ingredient = br.readLine().split(" ");
        for(int i=0; i<4; i++) {
            standard[i] = Integer.parseInt(ingredient[i]);
        }

        // N개의 식재료의 영양성분 입력
        arr = new int[N+1][5];
        for(int i=1; i<=N; i++){
            ingredient = br.readLine().split(" ");
            for(int j=0; j<5; j++) {
                arr[i][j] = Integer.parseInt(ingredient[j]);
            }
        }

        backtracking("", 1, 0, 0, 0, 0, 0);

        if(answer.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(minCost);
            System.out.println(answer.trim());
        }
    }

    public static void backtracking(String comb, int i, int p, int f, int s, int v, int c) {

        // 더 이상 탐색할 필요 없으므로 리턴
        if(c >= minCost) {
            return;
        }
        // 최소 영양성분을 모두 맞췄다면 최소 비용 갱신
        if(p >= standard[0] && f >= standard[1] && s >= standard[2] && v >= standard[3]) {
            minCost = c;
            answer = comb;
            return;
        }

        if(i > N){
            return;
        }

        backtracking(comb + " " + i, i+1, p+arr[i][0], f+arr[i][1], s+arr[i][2], v+arr[i][3], c+arr[i][4]);
        backtracking(comb, i+1, p, f, s, v, c);
    }
}
