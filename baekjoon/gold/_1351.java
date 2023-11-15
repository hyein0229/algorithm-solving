package baekjoon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
문제: 1351 무한 수열
난이도: Gold 5
알고리즘: 자료구조, dp
풀이 방법: dp 를 사용하여 A를 구하는 데 dp에 이미 있는 값이라면 dp에 저장된 값을 사용하고 없다면 재귀로 함수를 호출하여
        값을 구하고 dp에 기록한다. 주의할 것은 N,P,Q의 자료형인데 N 범위가 10^12 까지라는 것을 간과하고 int 로 선언하였다가 런타임 에러가 떴다..
        10^12 면 2^40 인데, int 는 32비트 즉 2^31 까지만 가능하다. 따라서 64비트인 long 으로 선언해주어야 한다.
        N의 입력값의 크기가 10^12로 매우 크므로 배열로 선언하기 보단 map 을 이용하여야 사용되는 a값만 기록하도록 하였다.
 */
public class _1351 {
    static long N, P, Q;
    static Map<Long, Long> map = new HashMap<>(); // dp

    public static void main(String[] args) {
        // N, P, Q 입력
        Scanner sc = new Scanner(System.in);
        N = sc.nextLong();
        P = sc.nextLong();
        Q = sc.nextLong();
        map.put(0L, 1L); // A[0]은 1의 값을 가짐

        System.out.println(A(N)); //A[N] 구하여 출력
    }

    // A 값 구하는 재귀 함수
    public static Long A(long i) {
        if (map.containsKey(i)) { // dp 에 이미 저장된 값이면 값 반환
            return map.get(i);
        }
        // dp 에 없다면 재귀호출하여 값을 구하고 dp 에 저장
        map.put(i, A(Math.floorDiv(i, P)) + A(Math.floorDiv(i, Q)));
        return map.get(i);
    }
}
