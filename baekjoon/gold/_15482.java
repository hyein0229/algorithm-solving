package baekjoon;

import java.util.Scanner;
/*
문제: 15482 한글 LCS
난이도: Gold 5
알고리즘: dp, 문자열
풀이 방법: LCS 알고리즘은 dp가 사용되는 대표적인 문제이다. 알아두자.
 */
public class _15482 {

    public static void main(String[] args) {
        // 두 문자열 입력 받기
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        int[][] LCS = new int[a.length()+1][b.length()+1];
        for(int i=1; i <=a.length(); i++){
            for(int j=1; j<=b.length(); j++) {
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    LCS[i][j] = LCS[i-1][j-1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
                }
            }
        }
        System.out.println(LCS[a.length()][b.length()]);
    }
}
