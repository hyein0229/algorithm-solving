package baekjoon;

import java.io.*;
/*
문제: 17609 회문
난이도: Gold 5
알고리즘: 문자열, 투포인터
풀이 방법: 왼쪽, 오른쪽 투 포인터를 사용하여 왼쪽 포인터는 하나씩 증가시키면서 오른쪽 포인터는 감소시키면서
        문자가 같은지 확인한다. 현재 문자가 같다면 다음 문자로 넘어간다. 그러나 현재 문자가 다르다면 왼쪽 문자만
        삭제하거나(포인터를 증가시킴) 오른쪽 문자만 삭제했을 때(포인터를 감소시킴) 회문이 되는지 확인한다.
        만약 왼쪽이나 오른쪽을 삭제했을 때 두 경우 다 회문이 안된다면 일반 문자열임을 의미한다.
 */
public class _17609 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            String str = br.readLine();

            int result = 2;
            if(isPalindrome(str, 0, str.length()-1)) { // 회문이면
                result = 0;

            } else {
                // 유사회문인지 체크
                int left = 0;
                int right = str.length()-1;
                while(left < right) {
                    // 현재 문자가 서로 다르면 왼쪽, 오른쪽 하나씩 지워보고 회문인지 체크
                    if(str.charAt(left) != str.charAt(right)) {
                        boolean removeLeft = isPalindrome(str, left+1, right);
                        boolean removeRight = isPalindrome(str, left, right-1);
                        if(removeLeft || removeRight) {
                            result = 1;
                        }
                        break;
                    }
                    left++;
                    right--;
                }
            }
            System.out.println(result);
        }
    }

    public static boolean isPalindrome(String str, int left, int right) {
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
