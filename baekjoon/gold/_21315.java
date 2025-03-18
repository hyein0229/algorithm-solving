package baekjoon;

import java.io.*;
/*
문제: 21315 카드섞기
난이도: Gold 5
알고리즘: 구현, 브루트포스, 시뮬레이션
풀이 방법: 가능한 모든 k 조합에 대해 카드 섞기 시뮬레이션을 해주어 결과가 맞는지 확인해주면 되고 구현은 어렵지 않았다.
        k의 최댓값은 y = log(2) x = log(10) x / log(10) 2 임을 이용하여 구하였다.
        카드를 맨 위에 올릴 땐 이전에 올렸던 카드 중에 올린다는 것을 주의하여 위로 올린 마지막 카드의 위치를
        기록해두는 것을 주의해야 한다.
 */
public class _21315 {

    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] goal = new int[N];
        int[] arr = new int[N];
        // 초기 카드 상태 저장
        for(int i=1; i<=N; i++) {
            arr[i-1] = i;
        }
        // 최종 상태 입력 받기
        for(int i=0; i<N; i++) {
            goal[i] = Integer.parseInt(line[i]);
        }

        int limit = (int)(Math.log(N) / Math.log(2));
        for(int k1=1; k1<=limit; k1++) {
            for(int k2=1; k2<=limit; k2++) {
                int[] tmp = new int[N];
                for(int j=0; j<N; j++) {
                    tmp[j] = arr[j];
                }
                // 2번의 (2, k)-섞기 진행
                simulation(k1, tmp);
                simulation(k2, tmp);
                if(check(tmp, goal)) { // 최종 상태와 같은지 체크
                    System.out.println(k1 + " " + k2);
                    return;
                }
            }
        }
    }

    public static void simulation(int k, int[] tmp) {
        // (2, k) - 섞기
        int pos = N;
        for(int i=1; i<=k+1; i++) {
            int[] next = new int[N]; // 다음 카드 상태 저장
            int up = (int)Math.pow(2, k-i+1); // 위로 올릴 카드 개수
            int idx = 0;
            // 2^k개의 카드 위로 올리기
            for(int j=pos-up; j<pos; j++) {
                next[idx] = tmp[j];
                idx++;
            }
            // 위에 올린 카드 제외하고 밑으로 순서대로 밀기
            for(int j=0; j<pos-up; j++) {
                next[idx] = tmp[j];
                idx++;
            }
            for(int j=pos; j<N; j++) {
                next[idx] = tmp[j];
                idx++;
            }
            // 상태 갱신
            for(int j=0; j<N; j++) {
                tmp[j] = next[j];
            }
            pos = up;
        }
    }

    public static boolean check(int[] tmp, int[] arr) {
        for(int i=0; i<N; i++) {
            if(tmp[i] != arr[i]){
                return false;
            }
        }
        return true;
    }
}
