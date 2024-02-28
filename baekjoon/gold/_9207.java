package baekjoon;

import java.io.*;
/*
문제: 9207 페그 솔리테어
난이도: Gold 4
알고리즘: 구현, 완전 탐색, 백트래킹
풀이 방법: dfs + 백트래킹을 사용한 완전 탐색을 수행하였다.
        보드판의 크기가 5x9 로 정해져 있으므로 dfs 안에서 보드판을 순회하면서
        핀이 있으면 해당 핀을 옮길 수 있는 조건을 검사 후 이동시킨고 인접한 핀을 제거한 후 dfs 를 재귀 호출하고
        호출이 끝난 후엔 원래의 상태로 백트래킹한다.
        핀을 옮길 것이 없을 때까지 반복한 후, 없다면 최소 핀의 개수와 이동 횟수를 갱신한다.
 */
public class _9207 {

    static int N;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static char[][] board;
    static int minPinCnt;
    static int minMoveCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            board = new char[5][9];
            minPinCnt = Integer.MAX_VALUE;
            minMoveCnt = Integer.MAX_VALUE;
            int pinCnt = 0;
            for(int j=0; j<5; j++) {
                String row = br.readLine();
                for(int k=0; k<9; k++) {
                    board[j][k] = row.charAt(k);
                    if(board[j][k] == 'o') {
                        pinCnt++;
                    }
                }
            }
            game(pinCnt, 0);
            System.out.println(minPinCnt + " " + minMoveCnt);
            if(i < N-1) br.readLine();
        }
    }

    public static void game(int pinCnt, int moveCnt) {
        if(!isContinued()) {
            if(minPinCnt > pinCnt) {
                minPinCnt = pinCnt;
                minMoveCnt = moveCnt;
            } else if(minPinCnt == pinCnt) {
                minMoveCnt = Math.min(minMoveCnt, moveCnt);
            }
            return;
        }

        for(int i=0; i<5; i++) {
            for(int j=0; j<9; j++) {
                if(board[i][j] != 'o') {
                    continue;
                }

                for(int k=0; k<4; k++) {
                    int nx = i + (dx[k]*2);
                    int ny = j + (dy[k]*2);
                    // 인접한 칸의 다음 칸의 범위 체크
                    if(nx < 0 || nx >= 5 || ny < 0 || ny >= 9) {
                        continue;
                    }

                    if(board[nx][ny] == '.' && board[nx-dx[k]][ny-dy[k]] == 'o') {
                        board[nx-dx[k]][ny-dy[k]] = '.'; // 인접한 핀 제거
                        board[i][j] = '.';
                        board[nx][ny] ='o'; // 다음 칸으로 핀 옮김

                        game(pinCnt-1, moveCnt + 1);
                        // 백트래킹
                        board[nx-dx[k]][ny-dy[k]] = 'o';
                        board[i][j] = 'o';
                        board[nx][ny] ='.';
                    }
                }
            }
        }
    }

    public static boolean isContinued() {
        for(int i=0; i<5; i++) {
            for(int j=0; j<9; j++) {
                if(board[i][j] != 'o') {
                    continue;
                }

                for(int k=0; k<4; k++) {
                    int nx = i + (dx[k]*2);
                    int ny = j + (dy[k]*2);
                    // 인접한 칸의 다음 칸의 범위 체크
                    if(nx < 0 || nx >= 5 || ny < 0 || ny >= 9) {
                        continue;
                    }
                    if(board[nx][ny] == '.' && board[nx-dx[k]][ny-dy[k]] == 'o') {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
