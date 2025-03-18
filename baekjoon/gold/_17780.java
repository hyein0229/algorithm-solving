package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17780 새로운 게임
난이도: Gold 2
알고리즘: 구현, 시뮬레이션
풀이 방법: ChessMan 클래스를 생성하고 위치, 방향, 말 번호를 저장하고 객체로 관리하도록 하였다.
        말 번호를 인덱스로 하는 체스 말 배열 arr 과 각 좌표마다 말 리스트를 저장하도록 2차원 리스트 배열 chessMen 을 생성하였다.
        1번 말부터 순서대로 움직여야 하므로 1부터 K까지 순차적으로 반복문을 돌리는 데,
        말 객체의 x, y 값을 이용해 chessMen[x][y].get(0), 즉 가장 아래에 있는 말을 구한 후
        이동할 수 있는 대상이 맞다면 해당 말을 기준으로 이동, 아니면 넘어간다. (주의, 가장 아래에 있는 말이어야 가능)
 */
public class _17780 {

    static class ChessMan {
        int num;
        int x;
        int y;
        int direct;

        public ChessMan(int num, int x, int y, int direct) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.direct = direct;
        }
    }

    static int N, K;
    static List<ChessMan>[][] chessMen;
    static int[][] board;
    static ChessMan[] arr;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        board = new int[N][N];
        chessMen = new ArrayList[N][N];
        arr = new ChessMan[K+1];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                chessMen[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        for(int i=1; i<=K; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int direct = Integer.parseInt(line[2]);
            ChessMan chessMan = new ChessMan(i, x-1, y-1, direct);
            arr[i] = chessMan;
            chessMen[x-1][y-1].add(chessMan);
        }

        int turn = 1;
        while(turn <= 1000) {
            boolean isContinued = goTurn();
            if(!isContinued) {
                break;
            }
            turn++;
        }
        if(turn > 1000) System.out.println(-1);
        else System.out.println(turn);
    }

    public static boolean goTurn() {
        // 1번부터 K번 말까지 순서대로 이동
        for(int i=1; i<=K; i++) {
            ChessMan chessMan = arr[i];
            int x = chessMan.x;
            int y = chessMan.y;

            List<ChessMan> cur = chessMen[x][y]; // 이동해야 할 말 리스트
            ChessMan target = cur.get(0); // 가장 아래에 있는 체스 말
            if(target.num != i) { // 자신이 이동 대상이 되지 않으면
                continue;
            }

            int nx = x + dx[target.direct];
            int ny = y + dy[target.direct];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 2) { // 범위를 벗어나거나 파란색이면
                target.direct = (target.direct % 2 != 0) ? target.direct + 1 : target.direct - 1; // 반대 방향으로 전환
                nx = x + dx[target.direct];
                ny = y + dy[target.direct];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 2) { // 또다시 파란색이면
                    continue;
                }
            }

            moveChess(cur, nx, ny);
            chessMen[x][y].clear();
            if(chessMen[nx][ny].size() >= 4) { // 말이 4개 이상 쌓이면 종료
                return false;
            }
        }
        return true;
    }

    public static void moveChess(List<ChessMan> cur, int nx, int ny) {

        for(ChessMan chessMan : cur) {
            chessMan.x = nx;
            chessMan.y = ny;
        }

        if(board[nx][ny] == 0) { // 다음 칸이 흰색이면
            chessMen[nx][ny].addAll(cur);
        } else if(board[nx][ny] == 1) { // 다음 칸이 빨간색이면
            Collections.reverse(cur);
            chessMen[nx][ny].addAll(cur);
        }
    }
}
