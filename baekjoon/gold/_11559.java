package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 11559 Puyo Puyo
난이도: Gold 4
알고리즘: 구현, 그래프, 시뮬레이션, bfs
풀이 방법: bfs 를 사용하여 풀이하였다. 4개 이상 연결된 뿌요 그룹이 있는지 bfs 를 사용하여
        구한 다음 4개 이상이면 뿌요를 없애고 연쇄 횟수를 추가한다. 주의할 것은
        해당되는 뿌요 그룹이 여러개여도 연쇄 1번만 추가한다.
        뿌요 그룹을 모두 없앤 후엔 필드의 아래서부터 즉, x=11부터 위로 탐색하면서
        뿌요가 있으면 각 뿌요들을 뿌요가 없는 위치까지 아래로 이동시켜준다.
 */
public class _11559 {

    static char[][] field;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        field = new char[12][6];

        // 필드 입력
        for(int i=0; i<12; i++) {
            String row = br.readLine();
            for(int j=0; j<6; j++) {
                field[i][j] = row.charAt(j);
            }
        }

        boolean isContinued = true;
        int chainCnt = 0; // 연쇄 횟수 카운트
        while(isContinued) {
            isContinued = bomb();
            if(isContinued) {
                chainCnt++;
                movePuyo(); // 뿌요 아래로 이동시킴
            }
        }
        System.out.println(chainCnt);
    }

    public static boolean bomb() {
        visited = new boolean[12][6];
        boolean chain = false; // 연쇄 추가 여부
        // 같은 색 뿌요가 4개 이상 상하좌우 연결되어 있으면 없애기
        for(int i=11; i>=0; i--) {
            for(int j=0; j<6; j++) {
                if(!visited[i][j] && field[i][j] != '.') {
                    if(bfs(i, j)) {
                        chain = true; // 연쇄 일어남
                    }
                }
            }
        }
        return chain;
    }

    public static boolean bfs(int x, int y) {
        List<int[]> list = new ArrayList<>(); // 뿌요 좌표 리스트
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int cnt = 1; // 개수 카운트

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            list.add(new int[]{curX, curY});

            for(int k=0; k<4; k++) {
                int nx = curX + dx[k];
                int ny = curY + dy[k];
                if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6) {
                    continue;
                }
                // 같은 색의 뿌요이고 방문하지 않았다면
                if(!visited[nx][ny] && field[x][y] == field[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    cnt++;
                }
            }
        }

        if(cnt < 4) {
            return false;
        }
        // 4개 이상 모여있으면 터짐
        for(int[] pos : list) { // 뿌요 없애기
            field[pos[0]][pos[1]] = '.';
        }
        return true;
    }

    public static void movePuyo() {
        // 밑에서부터 뿌요 아래로 이동시키기
        for(int i=10; i>=0; i--) {
            for(int j=0; j<6; j++) {
                if(field[i][j] != '.' && field[i+1][j] == '.') {
                    int curX = i+1; // 아래로 이동
                    while(curX < 12 && field[curX][j] == '.') { // 뿌요가 없을 때까지 이동
                        curX++;
                    }
                    field[--curX][j] = field[i][j];
                    field[i][j] = '.';
                }
            }
        }
    }
}
