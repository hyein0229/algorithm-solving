package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17837 새로운 게임 2
난이도: Gold 2
알고리즘: 구현, 시뮬레이션
풀이 방법: 문제 자체는 주어진 대로 구현만 하면 되기 때문에 어렵지 않았다.
        각 좌표에 있는 말들을 어떤 자료구조를 사용하여 저장해놓을 것인가가 가장 고민되는 것이었는데
        각 좌표마다 말 리스트를 담을 수 있도록 리스트 2차원 배열을 선언하여 말들의 위치를 관리하도록 하였다.
 */
public class _17837 {

    static class ChessMan {
        int x;
        int y;
        int d;

        public ChessMan(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int N;
    static int K;
    static int[][] board;
    static ArrayList<Integer>[][] list;
    static ChessMan[] chessMen;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        list = new ArrayList[N+1][N+1];
        chessMen = new ChessMan[K+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                list[i][j] = new ArrayList<>();
            }
        }

        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            chessMen[i] = new ChessMan(x, y, d);
            list[x][y].add(i);
        }

        int t = 0;
        boolean isEnd = false;
        while(++t <= 1000) {
            if(!moveHorses()) {
                isEnd = true;
                break;
            }
        }

        if(isEnd) {
            System.out.println(t);
        } else {
            System.out.println(-1);
        }
    }

    public static boolean moveHorses() {
        for(int i=1; i<=K; i++) {
            ChessMan chessMan = chessMen[i];
            ArrayList<Integer> moves = new ArrayList<>(); // 이동할 말들
            int x = chessMan.x;
            int y = chessMan.y;

            // 위에 있는 말들 구하기
            int pos = list[x][y].size();
            for(int j=0; j<list[x][y].size(); j++) {
                if(list[x][y].get(j) == i) {
                    pos = j; // 시작 위치
                }
                if(j >= pos) {
                    moves.add(list[x][y].get(j));
                }
            }
            // 이동할 좌표
            int nx = x + dx[chessMan.d];
            int ny = y + dy[chessMan.d];
            // 경계를 벗어나거나 파란색 칸이면 방향 바꾸기
            if(nx < 1 || nx > N || ny < 1 || ny > N || board[nx][ny] == 2) {
                chessMan.d = (chessMan.d % 2 != 0) ? chessMan.d + 1 : chessMan.d - 1;
                nx = x + dx[chessMan.d];
                ny = y + dy[chessMan.d];
                if(nx < 1 || nx > N || ny < 1 || ny > N || board[nx][ny] == 2) { // 이동 불가
                    continue;
                }
            }

            if(board[nx][ny] == 0) { // 흰색인 경우
                for(Integer num : moves) {
                    list[nx][ny].add(num);
                    chessMen[num].x = nx;
                    chessMen[num].y = ny;
                }
            } else if(board[nx][ny] == 1) { // 빨간색인 경우
                for(int j=moves.size()-1; j>=0; j--) {
                    list[nx][ny].add(moves.get(j));
                    chessMen[moves.get(j)].x = nx;
                    chessMen[moves.get(j)].y = ny;
                }
            }

            // 원래 있던 곳에선 말 제거
            for(int j=list[x][y].size()-1; j>=pos; j--) {
                list[x][y].remove(j);
            }

            // 말이 4개 이상 쌓이면 종료
            if(list[nx][ny].size() >= 4) {
                return false;
            }
        }
        return true;
    }
}
