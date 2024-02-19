package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 3967 매직 스타
난이도: Gold 5
알고리즘: 구현, 브루트포스, 백트래킹
풀이 방법: 백트래킹을 사용하여 알파벳이 비워져 있는 곳을 순서대로 알파벳으로 하나씩 채워본 후 매직스타인지 확인한다.
        첫째줄의 비워져 있는 곳부터 A에서 L 순서로 하나씩 채움으로 처음 매직스타가 되는 경우가 가장 사전순으로
        앞서는 경우가 되어 바로 정답을 출력하고 종료하면 된다.
 */
public class _3967 {

    static char[][] board = new char[5][9];
    static List<int[]> emptyPos = new ArrayList<>();
    static boolean[] visited = new boolean[12];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<5; i++) {
            String line = br.readLine();
            for(int j=0; j<line.length(); j++) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'x') { // 채워야할 위치
                    emptyPos.add(new int[]{i, j});
                } else if(line.charAt(j) != '.') {
                    visited[board[i][j] - 'A'] = true; // 사용한 알파벳 체크
                }
            }
        }

        dfs(0);

    }

    public static void dfs(int idx) {
        // 다 채웠으면 매직스타인지 확인
        if(idx == emptyPos.size()) {
            if(isMagicStar()) {
                for(int i=0; i<5; i++) {
                    for(int j=0; j<9; j++) {
                        System.out.print(board[i][j] + "");
                    }
                    System.out.println();
                }
                System.exit(0);
            }
            return;
        }

        int[] pos = emptyPos.get(idx); // 현재 알파벳을 채워야할 위치

        for(int i=0; i<12; i++) {
            if (!visited[i]) {
                board[pos[0]][pos[1]] = (char) (65 + i);
                visited[i] = true;
                dfs(idx + 1);
                visited[i] = false;
                board[pos[0]][pos[1]] = 'x';
            }
        }
    }

    public static boolean isMagicStar(){
        int sum1 = (board[0][4]-'A') + (board[1][3]-'A') + (board[2][2]-'A') + (board[3][1]-'A') + 4;
        int sum2 = (board[0][4]-'A') + (board[1][5]-'A') + (board[2][6]-'A') + (board[3][7]-'A') + 4;
        int sum3 = (board[3][1]-'A') + (board[3][3]-'A') + (board[3][5]-'A') + (board[3][7]-'A') + 4;
        int sum4 = (board[1][1]-'A') + (board[1][3]-'A') + (board[1][5]-'A') + (board[1][7]-'A') + 4;
        int sum5 = (board[1][1]-'A') + (board[2][2]-'A') + (board[3][3]-'A') + (board[4][4]-'A') + 4;
        int sum6 = (board[1][7]-'A') + (board[2][6]-'A') + (board[3][5]-'A') + (board[4][4]-'A') + 4;

        if(sum1 != 26 || sum2 != 26 || sum3 != 26 || sum4 != 26 || sum5 != 26 || sum6 != 26){
            return false;
        }
        return true;
    }
}
