package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17255 N으로 만들기
난이도: Gold 4
알고리즘: 백트래킹
풀이 방법: dfs + 백트래킹을 사용하여 오른쪽 숫자를 붙었을 때, 왼쪽 숫자를 붙었을 때 각 경우에 대해 탐색한다.
        왼쪽 오른쪽 끝 투포인터 변수 s, e 를 선언하여 e+1 이나 s-1 위치의 숫자를 붙여주었다.
        주의할 점은 중복되는 방법을 제외시켜 주어야 한다는 것이다.
        예를 들어 예제 2의 9111 의 경우 중복되는 경로가 나온다. 따라서 이를 제외시키기 위해
        경로를 누적시킨 문자열을 set 에 삽입하여 중복을 제외시켜주도록 한다.
        예를 들어 1 -> 11 -> 111 -> 9111 이면
        1, 111, 111111, 1111119111 문자열이 만들어지고 최종 경로인 1111119111이 set 에 삽입된다.
 */
public class _17255 {

    static int len;
    static String number;
    static Set<String> set = new HashSet<>(); // 중복된 방법을 제외시키기 위해 set 사용
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = br.readLine();
        len = number.length();

        for(int i=0; i<len; i++) {
            dfs(i, i,""+number.charAt(i), ""+number.charAt(i));
        }
        System.out.println(set.size());
    }

    public static void dfs(int s, int e, String str, String path) {
        if(s == 0 && e == len-1) {
            set.add(path);
            return;
        }

        if(e+1 < len) { // 오른쪽 숫자 붙이기
            dfs(s, e+1,str + number.charAt(e+1), path + str + number.charAt(e+1));
        }
        if(s-1 >= 0) { // 왼쪽 숫자 붙이기
            dfs(s-1, e,number.charAt(s-1) + str, path + number.charAt(s-1) + str);
        }
    }
}
