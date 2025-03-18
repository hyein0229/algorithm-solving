package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제: 17828 문자열 화폐
난이도: Gold 5
알고리즘: 그리디, 문자열
풀이 방법: X 가치를 만족하면서 사전 순으로 앞서는 N자리 문자열을 찾으려면, 작은 숫자의 알파벳을 최대한 앞에 많이 붙여야 한다.
        따라서 'A' 부터 자례대로 최대 가능한 길이를 구하여 붙여나간다. 이때 주의할 것은 X 가치를 만족하려면 현재 알파벳을 i 길이만큼
        붙이고 남은 가치 (X - after) 가 남은 길이를 Z 로 다 채웠을 때의 가치보다 같거나 작아야 한다는 것이다.
        만약 크다면 Z로 남는 부분을 다 채워도 X를 만들 수 없다는 것이므로 현재 알파벳의 길이를 i보다 줄이고 더 큰 숫자를 붙여야 한다는 걸 의미한다.
 */
public class _17828 {

    static String answer = "";

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]); // 문자열의 길이
        int X = Integer.parseInt(line[1]); // 문자열의 가치

        // 사전 순 앞서는 문자열 -> 앞 순서의 알파벳, 즉 작은 숫자부터 최대한 많이 붙임
        int alpha = 65; // 'A'
        int len = 0; // 현재까지 만들어진 문자열 길이
        int price = 0; // 현재까지 더해진 가치
        while(true) {
            int num = alpha - 64; // 알파벳에 해당하는 숫자
            // 남은 길이를 현재 알파벳으로 모두 채울 때 X 값의 N 문자열이 만들어지면 정답
            if(price + num * (N-len) == X) {
                answer += String.valueOf((char)alpha).repeat(N-len);
                break;
            }
            // Z 라면 모든 알파벳을 탐색했으므로 종료
            if(num == 26) {
                break;
            }

            // 최대한 붙일 수 있는 개수만큼 현재 알파벳을 붙이기
            for(int i=N-len-1; i>=0; i--) { // i는 붙일 개수
                int after = price + num*i; // 현재 알파벳을 i번 붙인 후 총 가치
                if(X-after > num && X-after <= 26*(N-(len+i))) {
                    answer += String.valueOf((char)alpha).repeat(i);
                    len += i;
                    price = after;
                    break;
                }
            }
            alpha++;
        }

        // N자리 문자열이 완성되었다면
        if(answer.length() != N) {
            System.out.println("!");
        } else{
            System.out.println(answer);
        }
    }
}
