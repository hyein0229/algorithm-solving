package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
문제:  2608 로마 숫자
난이도: Gold 5
알고리즘: 구현
풀이 방법: 규칙에 따른 코드 구현만이 핵심이다. 아라비아 숫자로 바꿀 땐 문자열을 한 글자씩 보면서 해당하는 value 를 더해주면 되는데,
        3번 규칙만 고려하여 I, X, C 로 시작하는 경우에 예외를 체크해준다.
        그 다음, 로마 숫자로 바꾸는 경우엔 HashMap 의 value 값을 기준으로 먼저 내림차순 정렬을 한다.
        가장 큰 숫자부터 현재 숫자를 나누어 몫을 구하고, 몫만큼 key 문자를 더한다. 숫자를 나누었으므로 그 다음 숫자를 나머지로 갱신한다.
        이런 방법으로 숫자가 0이 될 때까지 차레대로 나누어 key 를 더해가면 된다.
 */

public class _2608 {

    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {

        // 두 개의 로마 숫자 입력받기
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String n1 = bufferedReader.readLine();
        String n2 = bufferedReader.readLine();

        map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        // 두 수를 더한 값
        int resultNum = getNum(n1) + getNum(n2);
        // 로마숫자로 바꾼 결과
        String resultRoma = getRoma(resultNum);

        System.out.println(resultNum);
        System.out.println(resultRoma);

    }

    // 아라비아 숫자를 로마숫자로 바꿈
    public static String getRoma(int n) {
        String result = "";
        // map의 value 를 기준으로 내림차순 정렬
        List<Map.Entry<String, Integer>> lst = new ArrayList<>(map.entrySet());
        lst.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        int index = 0; // 현재 entry 의 위치
        while(n != 0) { // 가장 큰 수부터 차례대로 나눔
            int q = n / lst.get(index).getValue(); // 나눈 몫
            if(q != 0) {
                for(int i=0; i<q; i++) {
                    result += lst.get(index).getKey(); // 몫 만큼 key 문자 더하기
                }
                n = n % lst.get(index).getValue(); // 나머지 숫자로 갱신
            }
            index++;
        }
        return result;
    }

    // 로마 숫자를 아라비아 숫자로 바꿈
    public static int getNum(String n) {
        int result = 0;
        for(int i=0; i<n.length(); i++) {
            String cur = String.valueOf(n.charAt(i));
            if((cur.equals("I") || cur.equals("X") || cur.equals("C")) && i < n.length()-1) {
                String s = n.substring(i, i+2); // 연속 두 문자 추출
                if(map.containsKey(s)) {
                    result += map.get(s);
                    i++;
                    continue;
                }
            }
            result += map.get(cur);
        }
        return result;
    }
}
