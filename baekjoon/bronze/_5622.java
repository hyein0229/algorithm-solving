package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제: 5622 다이얼
난이도: Bronze 2
알고리즘: 구현
풀이 방법: 숫자를 하나를 누르면 처음부터 다시 시작이므로 이 문제는 알파벳에 해당하는 숫자 버튼의 시간만 각각 찾아서
        더하면 된다. (최소 시간이라고 하지만 거는 방법은 하나밖에 없다)
        주의할것은 숫자 버튼에 알파벳이 3개씩 있는 것이 아니라 4개씩 있는 있는 경우도 있다는 점.
        4개가 있는 경우를 고려하여 조건식만 잘 세우면 쉽게 풀 수 있다.
 */
public class _5622 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int total = 0;
        int time;
        for(int i=0; i<str.length(); i++) {
            int distance = str.charAt(i) - 'A';
            if(distance < 15) {
                time = 3 + (int)distance / 3;
            }else if(distance <= 18) {
                time = 8;
            }else if(distance < 22) {
                time = 9;
            } else {
                time = 10;
            }

            total += time;
        }
        System.out.println(total);
    }

}
