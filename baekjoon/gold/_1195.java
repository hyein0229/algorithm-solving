package baekjoon;

import java.io.*;
/*
문제: 1195 킥다운
난이도: Gold 5
알고리즘: 구현, 브루트포스
풀이 방법: 헷갈리지 않기 위해 더 큰 기어를 기준으로 하고 작은 기어로 끼뭐 맞추도록 구현하였다.
        이중 for문을 사용하여 완전탐색하면서 두 기어의 홈과 홈이 만나지 않는지 확인하는데 인덱스 i는
        큰 기어의 작은 기어와 맞물리는 시작 위치가 된다. 여기서 중요한 것은 i 위치부터 작은 기어를 끼워 맞추는데
        작은 기어의 앞쪽부터와 뒤쪽부터 두 가지의 경우를 모두 실험해보아야 한다.
        주의할 것은 2112, 222 인 경우와 같이 아예 사이에 끼워 맞출 수 없을 때가 있다. 이런 경우 정답은
        두 기어의 길이 합이 된다.
 */
public class _1195 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String gear1 = br.readLine();
        String gear2 = br.readLine();
        String bigger = (gear1.length() >= gear2.length()) ? gear1 : gear2;
        String smaller = (gear1.length() < gear2.length()) ? gear1 : gear2;
        int minLen = bigger.length() + smaller.length();

        for(int i=0; i<bigger.length(); i++) {
            boolean flag = true;
            for(int j=0; j<smaller.length(); j++) { // 작은 기어의 앞쪽에서부터 맞춤
                if(i+j >= bigger.length()) {
                    break;
                }
                if(bigger.charAt(i+j) == '2' &&  smaller.charAt(j) == '2') {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                int len = i + smaller.length();
                if(len < bigger.length()) {
                    len = bigger.length();
                }
                minLen = Math.min(minLen, len);
            }

            flag = true;
            for(int j=0; j<smaller.length(); j++) { // 작은 기어의 뒤 끝에서부터 맞춤
                if(i-j < 0) {
                    break;
                }
                if(bigger.charAt(i-j) == '2' && smaller.charAt(smaller.length()-1-j) == '2') {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                int len = (bigger.length() - i - 1) + smaller.length();
                if(len < bigger.length()) {
                    len = bigger.length();
                }
                minLen = Math.min(minLen, len);
            }
        }
        System.out.println(minLen);
    }
}
