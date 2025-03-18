package baekjoon;

import java.util.Scanner;

/*
문제: 2661 좋은수열
난이도: Gold 4
알고리즘: dfs, 백트래킹
풀이 방법: dfs + 백트래킹을 이용하여 풀었다. dfs 로 1, 2, 3을 차례대로 붙여가면서 완전탐색을 하되
        숫자를 붙일 때마다 나쁜 수열인지 체크하여 나쁜 수열이라면 더 이상 탐색하지 않도록 가지치기를 한다.
        숫자는 1, 2, 3 오름차순으로 붙이므로 처음으로 만들어지는 N 자리 수열이 좋은 수열들 중 가장 작은 수라는 것이 보장된다.
        따라서, answer 이 나온 경우 곧바로 탐색을 멈춘다.
 */
public class _2661 {
    static int N;
    static String answer = "";
    static String[] numbers = {"1", "2", "3"};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dfs("", 0); // dfs 탐색
        System.out.println(answer);
    }

    // dfs 탐색
    public static void dfs(String str, int len) {

        if(!answer.isEmpty()) {
            return;
        }
        // 나쁜 수열인지 확인하고, 나쁜 수열이면 탐색을 멈춤(가지치기 조건)
        if(len > 3 && !check(str)) {
            return;
        }

        // N 자리 좋은 수열을 만든 경우
        if(len == N) {
            answer = str;
            return;
        }

        for(int i=0; i<3; i++) {
            // 앞의 숫자와 같지 않은 숫자만 붙임
            if(len == 0 || (len > 0 && str.charAt(len-1) != numbers[i].charAt(0))) {
                dfs(str + numbers[i], len+1);
            }
        }
    }

    // 나쁜 수열인지 체크
    public static boolean check(String str) {
        int len = str.length(); // 현재 문자열 길이
        int size = (int)len / 2; // 부분수열의 최대 길이
        for(int l=2; l<=size; l++) { // 가능한 부분수열의 크기마다 유효한지 체크
            // 두 개의 연속된 부분수열이 같으면
            if(str.substring(len-l, len).equals(str.substring(len-2*l, len-l))) {
                return false;
            }
        }
        return true;
    }
}
