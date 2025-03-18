package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 22251 빌런 호석
난이도: Gold 5
알고리즘: 구현, 브루트포스
풀이 방법: 디스플레이 모양을 어떻게 구현할까 고민하는 것이 관건. 7개의 표시등에 각각 0부터 6까지 인덱스를 매기고
        0~9의 숫자를 인덱스로 하는 배열을 선언하여 숫자마자 불이 들어오는 표시등을 1로 표현하였다.
        이 배열을 기반으로 숫자를 다른 숫자로 바꿀 때 표시등의 상태가 반전되어야 한다면 0 -> 1 또는 1 -> 0으로
        반전 횟수를 카운트한다.
        dfs + 백트래킹을 사용해 완전 탐색하여 각 자릿수를 0~9까지 바꾸어본다. 이때 변화하는 디스플레이 숫자를
        문자열 타입으로 넘겨주도록 하여 반전 횟수가 최소 1 이상이면 문자열을 정수로 바꾼 숫자가 1이상 N 이하인지를
        확인하여 Set 에 넣어준다. Set 은 중복되는 경우를 제외시키기 위해 사용
 */
public class _22251 {

    static int N, K, P, X;
    static int[][] display;
    static Set<String> set = new HashSet<>();
    static int[][] shapeArr = {{1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 0, 0, 1, 0},
            {1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1, 0},
            {1, 1, 0, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 1, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        P = Integer.parseInt(line[2]);
        X = Integer.parseInt(line[3]);
        display = new int[K][7]; // K 자리 수의 디스플레이

        String str = String.valueOf(X);
        if(str.length() != K) {
            while(str.length() < K) {
                str = "0" + str;
            }
        }

        // 초기 X 값으로 디스플레이 초기화
        for(int i=0; i<K; i++) {
            int num = str.charAt(i) - '0';
            switchDisplay(i, num);
        }

        dfs(0, 0, str);
        System.out.println(set.size());
    }

    public static void dfs(int displayIdx, int totalCnt, String numStr) {
        // 반전 횟수가 P번을 넘어가면 더 이상 탐색하지 않음
        if(totalCnt > P) {
            return;
        }

        // 1개 이상 반전시켰으면
        if(totalCnt >= 1) {
            if(Integer.parseInt(numStr) >= 1 && Integer.parseInt(numStr) <= N) { // 1 이상 N 이하 숫자인지 확인
                set.add(numStr);
            }
        }

        if(displayIdx == K) {
            return;
        }

        // 복구를 위한 현재 상태 복사
        int[] tmp = new int[7];
        for(int j=0; j<7; j++) {
            tmp[j] = display[displayIdx][j];
        }

        for(int i=0; i<=9; i++) {
            int cnt = switchDisplay(displayIdx, i);
            dfs(displayIdx+1, totalCnt + cnt, numStr.substring(0, displayIdx) + i + numStr.substring(displayIdx+1));
            for(int j=0; j<7; j++) {
                display[displayIdx][j] = tmp[j];
            }
        }
    }

    public static int switchDisplay(int idx, int num) {
        int cnt = 0;
        for(int i=0; i<7; i++) {
            if(shapeArr[num][i] == display[idx][i]) {
                continue;
            }
            display[idx][i] = shapeArr[num][i]; // 반전시키기
            cnt++;
        }
        return cnt;
    }
}
