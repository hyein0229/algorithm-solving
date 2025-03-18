package baekjoon;

import java.io.*;
/*
문제: 9519 졸려
난이도: Gold 5
알고리즘: 구현, 문자열, 시뮬레이션
풀이 방법: 입력되는 단어가 X번 깜박인 후의 단어이므로 거꾸로 X번을 진행해야 한다.
        직접 써본 결과 짝수위치의 문자는 앞으로, 홀수위치의 문자는 붙인 후 거꾸로 하여 뒷부분에 붙이면 깜박이기 전의
        단어를 구할 수 있다. 여기서 X가 최대 1,000,000,000번까지 수행될 수 있으므로 이것을 하나하나
        다 수행하면 시간 초과가 일어난다. 따라서, 깜박임을 수행할 때 처음 입력받은 단어로 다시 돌아오는 횟수를
        구하여 그 만큼으로 나누어준 나머지만큼만 수행하도록 하였다.
 */
public class _9519 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        String word = br.readLine();

        String copyWord = word;
        int i=0; // 깜박인 횟수 카운트
        while(i < X) {
            StringBuilder frontSb = new StringBuilder();
            StringBuilder backSb = new StringBuilder();

            for(int j=0; j<word.length(); j+=2) {
                frontSb.append(word.charAt(j));
            }
            for(int j=1; j<word.length(); j+=2) {
                backSb.append(word.charAt(j));
            }
            backSb.reverse();
            word = frontSb.append(backSb).toString();
            i++;
            // 처음 입력받았던 상태로 돌아오면
            if(word.equals(copyWord)) {
                X = (X-i) % i; // 반복되는 횟수로 나눔
                i=0;
            }
        }
        System.out.println(word);
    }
}
