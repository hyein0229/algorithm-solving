package baekjoon;

import java.io.*;
/*
문제: 20437 문자열 게임 2
난이도: Gold 5
알고리즘: 문자열, 슬라이딩 윈도우
풀이 방법: 1. 문자열의 알파벳을 미리 카운트하여 배열에 개수를 저장한다.
          2. 문자열의 첫 알파벳부터 순차적으로 탐색하는데, 만약 해당 알파벳 카운트가 k 미만이면 넘어간다.
          3. k 이상이면 뒷문자부터 해당 알파벳을 별도로 카운트하여 k개가 되는 지점까지의 길이를 구하여
                min, max를 갱신한다.
 */
public class _20437 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());
            int len = w.length();

            if(k == 1) {
                System.out.println("1 1");
                continue;
            }

            // 알파벳 미리 카운트
            int[] alphaCnt = new int[26];
            for(int j=0; j<w.length(); j++) {
                alphaCnt[w.charAt(j)-'a']++;
            }

            int min = len+1;
            int max = -1;
            for(int j=0; j<w.length(); j++) {
                if(alphaCnt[w.charAt(j)-'a'] < k) {
                    continue;
                }

                int cnt = 1;
                for(int l=j+1; l<w.length(); l++) {
                    if(w.charAt(j) == w.charAt(l)) cnt++;
                    if(cnt == k) {
                        min = Math.min(min, l-j+1);
                        max = Math.max(max, l-j+1);
                        break;
                    }
                }
            }
            if(min == len+1 || max == -1){
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }
    }
}
