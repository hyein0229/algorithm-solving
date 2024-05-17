package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 9935 문자열 폭발
난이도: Gold 4
알고리즘: 문자열, 스택
풀이 방법: Stack 을 사용하여 해결한다. 입력받은 문자열을 차례대로 순회하면서 하나씩 삽입하는 데,
        스택의 크기가 폭발 문자열의 크기 이상이 되면 스택 뒤에서의 폭발 문자열의 크기만큼의 문자열과
        폭발 문자열이 같은지 비교한다. 같다면 해당 문자열을 pop 하여 제거한다.
        이 과정을 반복한 후 스택에 남은 문자가 없다면 FRULA 을 출력하고, 아니면 남은 문자를 이어 만든 문자열을 출력한다.
        이 때 문자열 출력 시 String 을 사용하면 메모리 초과가 일어난다. <- 반복된 갱신 없이 마지막 출력에서만
        String 을 사용해도 메모리 초과가 일어났다.
        StringBuilder 를 사용하여 해결해주었다.
 */
public class _9935 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine(); // 현재 문자열
        String target = br.readLine(); // 폭발 문자열
        int strLen = str.length();
        int targetLen = target.length();
        StringBuilder sb = new StringBuilder(); // String 으로 인한 메모리 초과 해결
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<strLen; i++) {
            stack.push(str.charAt(i));

            // 스택 크기가 폭발 문자열의 크기 이상이 되면 문자열 탐색 시작
            if(stack.size() >= targetLen) {
                int count = 0;
                for(int j=0; j<targetLen; j++) {
                    if(stack.get(stack.size() - targetLen + j) == target.charAt(j)) {
                        count++;
                    } else // 문자열이 다르면 바로 탐색 종료
                        break;
                }
                // 폭발 문자열을 찾았으면 스택에서 pop 하여 제거
                if(count == targetLen) {
                    for(int k=0; k<targetLen; k++) {
                        stack.pop();
                    }
                }
            }
        }

        if(stack.isEmpty())
            System.out.println("FRULA");
        else {
            for(char ch : stack) { // 남은 문자열
                sb.append(ch);
            }
            System.out.println(sb);
        }
    }
}
