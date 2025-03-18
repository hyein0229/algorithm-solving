package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 14658 하늘에서 별똥별이 빗발친다
난이도: Gold 3
알고리즘: 브루트포스
풀이 방법: N, M 의 범위가 둘다 500,000 이므로 모든 좌표에 트램펄린을 설치해보는 방식으로 완전 탐색을 하면
        시간 초과가 일어난다. 따라서 별동별의 수 K의 범위가 100으로 매우 작음을 이용해야 한다.
        풀이 1 (틀림) 별 하나의 좌표를 기준으로 하여 트램펄린을 제 1, 2, 3, 4 사분면으로 설치하여 확인
        => 별 하나를 모서리 끝점으로 설치하면 안되는 반례 존재
           *
        *     *  => 이 경우 별 하나를 모서리 끝점으로 두면 3개가 된다. 모두 변에 위치해야 한다.
           *
        따라서 트램펄린을 설치할 때 별 하나를 기준으로 두면 안된다.
        맞는 풀이는 별 두개를 기준으로 하여 트램펄린을 설치한다.
        예 (1, 1), (5, 5) 두 개의 별이 기준이라고 하면
        각각 x=1, y=1 일 때 x=1, y=5 일 때 x=5, y=5 일 때 x=5, y=1 일 때
        4가지의 구역에 트램펄린을 설치할 수 있다.
 */
public class _14658 {

    static int N, M, L, K;
    static List<int[]> list = new ArrayList<>();
    static int ans = 0;

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        L = Integer.parseInt(line[2]);
        K = Integer.parseInt(line[3]);

        for(int i=0; i<K; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            list.add(new int[]{x, y});
        }

        for(int[] star1 : list) {
            for(int[] star2 : list) {
                ans = Math.max(ans, calc(star1[0], star2[1]));
            }
        }
        System.out.println(K-ans);
    }

    public static int calc(int x, int y) {
        int cnt = 0;
        for(int[] s : list) {
            if(s[0] >= x && s[0] <= x+L && s[1] >= y && s[1] <= y+L)
                cnt++;
        }
        return cnt;
    }
}
