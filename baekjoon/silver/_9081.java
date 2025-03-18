package baekjoon;

import java.io.*;
import java.util.*;

/*
문제: 9081 단어 맞추기
난이도: Silver 1
알고리즘: 구현, 수학, 문자열
 */
public class _9081 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            String word = br.readLine();
            char[] charArr = word.toCharArray();

            int len = charArr.length;
            int idx1 = -1;
            int idx2 = 0;
            // 처음 알파벳이 작아지는 (사전순 앞임을 의미) 위치
            for(int j=len-1; j>0; j--) {
                if(charArr[j-1] < charArr[j]) {
                     idx1 = j-1;
                     break;
                }
            }
            // 주어진 단어가 마지막 단어라면 그대로
            if(idx1 == -1) {
                System.out.println(word);
                continue;
            }

            // idx1 원소보다 큰 원소 찾기
            for(int j=len-1; j>idx1; j--) {
                if(charArr[idx1] < charArr[j]){
                    idx2 = j;
                    break;
                }
            }

            // 알파벳 위치 바꾸기
            char temp = charArr[idx2];
            charArr[idx2] = charArr[idx1];
            charArr[idx1] = temp;
            Arrays.sort(charArr, idx1+1, len); // 오름차순 정렬

            System.out.println(new String(charArr));
        }
    }
}
