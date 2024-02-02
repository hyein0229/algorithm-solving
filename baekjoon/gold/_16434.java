package baekjoon;

import java.io.*;
/*
문제: 16434 드래곤 앤 던전
난이도: Gold 4
알고리즘: 구현, 이분탐색
풀이 방법: 최악의 경우 용사의 공격력이 1이고, 몬스터의 공격력과 체력이 1000000이면 완탐으로 풀었을 때 시간초과가
        난다. 따라서 O(logN) 으로 줄일 수 있는 이분 탐색을 이용한다.
 */
public class Main {

    static class Round {
        int t, a, h;

        public Round(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    static int N;
    static int H;
    static Round[] rounds;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        H = Integer.parseInt(line[1]);
        rounds = new Round[N];
        long left = 1;
        long right = 0;

        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            int t = Integer.parseInt(line[0]);
            int a = Integer.parseInt(line[1]);
            int h = Integer.parseInt(line[2]);
            rounds[i] = new Round(t, a, h);
            if(t == 1) {
                right += ((long)a * h);
            }
        }

        while(left <= right) {
            long mid = (left + right) / 2;
            if (simulation(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    public static boolean simulation(long maxHp) {
        long curHp = maxHp; // 생명력
        long curAtk = H; // 공격력
        for(int i=0; i<N; i++) {
            Round round = rounds[i];
            int t = round.t;
            int a = round.a;
            int h = round.h;
            // 몬스터 공격
            if(t == 1) {
                long cnt = (long)Math.ceil((double)h / curAtk); // 몬스터를 죽이기 까지 총 공격 횟수
                curHp -= (cnt-1) * a;
                if(curHp<= 0) {
                    return false;
                }
            } else { // 포션 충천
                curHp = Math.min(maxHp, curHp + h);
                curAtk += a;
            }
        }
        return true;
    }
}
