package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1644 소수의 연속합
난이도: Gold 3
풀이 방법: 에라토스테네스의 체(소수 판별법), 투포인터
난이도: N이하의 수 중 에라토스테네스의 체 방법을 이용해 소수인 것만 골라 list에 삽입
       left, right 투포인터를 두고 left ~ right 사이의 소수의 합을 sum에 저장
       1. sum == N이면 정답 카운트 증가
       2. sum <= N이면 right 증가시켜서 sum 에 해당값 더해주기
       3. sum > N 이면 sum 이 다시 N보다 작거나 같아질 때까지 left 를 늘려가며 sum 에서 해당값 빼기
       주의, N이 1부터 입력가능하므로 N == 1일땐 0 출력하고 바로 종료
 */
public class _1644 {

    static int N;
    static ArrayList<Integer> primeNumbers;
    static boolean[] isPrime;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if(N == 1) {
            System.out.println(0);
            return;
        }

        primeNumbers = new ArrayList<>();
        isPrime = new boolean[N+1];
        ans = 0;
        Arrays.fill(isPrime, true);

        // 소수 판별
        for(int i=2; i<=(int)Math.sqrt(N); i++) {
            if(isPrime[i]) {
                int j=2;
                while(i*j <= N) {
                    isPrime[i*j] = false;
                    j++;
                }
            }
        }

        for(int i=2; i<=N; i++) {
            if(isPrime[i])  {
                primeNumbers.add(i);
            }
        }

        int len = primeNumbers.size();
        int left = 0;
        int right = 0;
        int sum = primeNumbers.get(left);
        while(left <= right) {
//            System.out.println(left + " " + right + " " + sum);
            if(sum == N) ans++;

            if(sum <= N) {
                right++;
                if(right == len) break;
                sum += primeNumbers.get(right);
            } else {
                while(left < right) {
                    sum -= primeNumbers.get(left);
                    left++;
                    if(sum <= N) {
                        break;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
