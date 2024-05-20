package baekjoon;

import java.io.*;

/*
문제: 2179 비슷한 단어
난이도: Gold 4
알고리즘: 문자열
풀이 방법: 단어 길이가 100이하로 제한되어 있기 때문에 모든 문자열끼리 다 비교해주는
        완전 탐색 방법을 사용하여 풀이하였다.
 */
public class _2179 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];

        // 영단어 입력
        for(int i=0; i<N; i++) {
            String word = br.readLine(); // 영단어
            arr[i] = word;
        }

        int s = 0;
        int t = 0;
        int maxLen = 0;
        for(int i=0; i<N-1; i++) {
            String word1 = arr[i];
            for(int j=i+1; j<N; j++) {
                String word2 = arr[j];
                int len = Math.min(word1.length(), word2.length());
                if(len < maxLen) {
                    continue;
                }

                int l = 0;
                for(int k=0; k<len; k++) {
                    if(word1.charAt(k) == word2.charAt(k)) {
                        l++;
                    } else break;
                }

                if(maxLen < l) {
                    maxLen = l;
                    s = i;
                    t = j;
                }
            }
        }

        System.out.println(arr[s]);
        System.out.println(arr[t]);
    }
}
