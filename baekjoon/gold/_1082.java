package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
  문제: 1082 방 번호
  난이도: Gold 3
  알고리즘: 그리디
  풀이방법: 가장 큰 숫자를 만드려면 고려해야할 조건
            1. 숫자에 상관없이 가장 큰 자릿수를 만들어야 한다. (물론 시작이 0이 될 수 없는 것은 고려해서)
            2. 최장의 자릿수를 만들 수 있다는 조건 안에서 큰 자릿수에 올 수 있는 숫자 중 가장 큰 것을 배치해야 한다.
          위 조건을 만족하기 위해 먼저 숫자의 가격 중 minCost 를 구하고 M 으로 나누어 만들 수 있는 가장 큰 자릿수를 구한다.
          이때, 시작 숫자가 0이 될 수 없다는 것을 고려하기 위해 minCost 가 0인 경우엔 두 번째로 작은 숫자를 시작 숫자로 하고 구해준다.
          자릿수를 구하고 난 뒤엔 각 자리마다 배치시킬 수 있는 숫자 중 가장 큰 숫자를 배치시킨다.
          숫자가 큰 것부터 역순으로 탐색한다. 현재 숫자를 구매하고 난 뒤의 가격이 남은 자릿수를 모두 최소금액 숫자로 채우기 위해 필요한 가격 이상이라면
          해당 숫자를 현재 자리에 배치할 수 있으므로 구매하고 다음 자릿수부터 반복 탐색하여 답을 구한다.
 */
public class _1082 {

    static int N, M;
    static int[] p;
    static int minCost = Integer.MAX_VALUE;
    static int maxLength = 0; // 만들 수 있는 숫자의 최장 길이
    static String ans = "";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        p = new int[N];

        for(int i=0; i<N; i++) {
            p[i] = Integer.parseInt(line[i]);
            if(i != 0) { // 0을 제외하고 최소 가격 구하기
                minCost = Math.min(p[i], minCost);
            }
        }

        M = Integer.parseInt(br.readLine());

        // 가장 큰 방 번호가 되려면?
        // 가장 번호의 자릿수가 길고 => 최대 자릿수가 얼마 나올 수 있는지부터 먼저 구해야할 것 같은디?
        // 무조건 앞 숫자가 크게 시작해야 함
        if(p[0] < minCost) { // 숫자 0의 가격이 더 작다면?
            if(M < minCost) { // minCost 보단 준비 금액이 작으면, 무조건 만들 수 있는 번호는 0뿐
                maxLength = 1;
            } else {
                maxLength += ((M - minCost) / p[0]) + 1; // 시작숫자 붙이고 0으로 채움
                minCost = p[0];
            }
        } else {
            maxLength += M / minCost;
        }

        // 길이 10에서 현재 0인덱스 채우고 남은 길이는 9
        int idx = 0; // 시작 숫자
        while(idx < maxLength) {
            int select = 0;
            for(int i=N-1; i>=0; i--) { // 가장 큰 숫자부터 보기
                int temp = M - p[i]; // 현재 숫자를 사고 난뒤 남은 금액
                if(temp < 0)
                    continue;

                int l = maxLength - (idx + 1); // 남은 길이
                if(l * minCost <= temp) { // 남은 금액 안에서 남은 길이를 최소금액으로 다 채워서 만들 수 있으면
                    select = i;
                    break;
                }
            }
            ans += select;
            M -= p[select];
            idx++;
        }
        System.out.println(ans);
    }
}
