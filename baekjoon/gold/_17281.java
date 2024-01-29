package baekjoon;

import java.io.*;
/*
문제: 17281 야구
난이도: Gold 4
알고리즘: 구현, 브루트포스
풀이 방법: dfs + 백트래킹을 이용하여 가능한 모든 타순, 즉 순열을 구한 뒤 1~9번까지의 타순이 정해졌을 때
        직접 이닝 시뮬레이션을 돌려 획득 점수를 구한다.
 */
public class _17281 {
    static int N;
    static int[][] inning;
    static int[] players = new int[10];
    static boolean[] visited = new boolean[10];
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inning = new int[N][10];

        for(int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=1; j<=9; j++) {
                inning[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        players[4] = 1; // 4번 타자에 1번 선수
        visited[1] = true;
        dfs(1);
        System.out.println(answer);
    }

    public static void dfs(int num) {
        // 타순을 모두 정하면 게임 시작
        if(num > 9) {
            answer = Math.max(answer, gameSimulation());
            return;
        }

        if(num == 4) { // 4번 타자는 이미 정해짐
            dfs(num+1);
        } else {
            for(int i=1; i<=9; i++) {
                if(!visited[i]) {
                    players[num] = i;
                    visited[i] = true;

                    dfs(num+1);

                    players[num] = 0;
                    visited[i] = false;
                }
            }
        }
    }

    public static int gameSimulation() {
        int cur = 1; // 1번 타자부터 시작
        int totalScore = 0; // 얻은 점수
        for(int i=0; i<N; i++) {
            int[] base = new int[4]; // 1~3번 베이스
            int outCnt = 0;
            while(outCnt < 3) {
                int hit = inning[i][players[cur]]; // 타격
                if(hit == 0) { // 아웃
                    outCnt++;
                } else { // 히트
                    totalScore += advance(players[cur], hit, base);
                }
                cur++; // 다음 타자
                if(cur > 9) {
                    cur = 1;
                }
            }
        }
        return totalScore;
    }

    public static int advance(int player, int hit, int[] base) {
        int score = 0;
        base[0] = player; // 현재 타자
        for(int i=3; i>=0; i--) {
            if(base[i] != 0) { // 타자가 있으면 진루
                if(i+hit > 3) { // 홈에 들어옴
                    base[i] = 0;
                    score++;
                } else { // 해당 베이스로 진루하기
                    base[i+hit] = base[i];
                    base[i] = 0;
                }
            }
        }
        return score;
    }
}
