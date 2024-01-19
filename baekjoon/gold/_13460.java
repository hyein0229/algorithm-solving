package baekjoon;

import java.io.*;
/*
문제: 13460 구슬 탈출2
난이도: Gold 1
알고리즘: 구현, 그래프, 시뮬레이션, bfs
풀이 방법: dfs + 백트래킹을 이용하여 풀었다. 구슬을 이동시키는 부분을 구현하는 것이 가장 까다로웠다.
        구슬의 위치가 겹칠 때를 어떻게 처리해줄 것인가가 문제인데 먼저 구슬들을 다른 구슬을 신경쓰지 말고
        벽이 아닐 때까지 쭉 이동 시킨 다음 구슬이 같은 위치에 있다면 두 구슬의 이동 거리를 계산하여
        더 먼 거리를 온 구슬, 즉 뒤에 있었던 구슬을 해당 방향의 한 칸 뒤로 위치시킨다.
        만약, 구슬을 이동시키다가 구멍을 만났다면 boolean 값으로 체크해준 후 파란 구슬이 구멍에 빠진 경우
        실패하였으므로 다음 탐색으로 넘어가고 빨간 구슬만 빠진 경우 성공하였으므로 정답을 갱신한다.
        dfs 의 경우엔 성공했어도 최소 횟수가 보장되지 않으므로 탐색을 종료하면 안되고 다시 이전 지점으로 백트래킹하여
        계속 탐색해야 한다. 그래서 dfs 로 풀었지만 bfs 가 더 효율적인 것 같다.
 */
public class _13460 {

    static int N;
    static int M;
    static char[][] board;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][][][] visited;
    static boolean success = false;

    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        board = new char[N][M];
        visited = new boolean[N][M][N][M];
        int rx = 0, ry = 0; // 빨간 구슬 좌표
        int bx = 0, by = 0; // 파란 구슬 좌표

        for(int i=0; i<N; i++) {
            String row = br.readLine();
            for(int j=0; j<M; j++) {
                board[i][j] = row.charAt(j);
                if(board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if(board[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        dfs(0, rx, ry, bx, by);
        if(success) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }

    }

    public static void dfs(int num, int rx, int ry, int bx, int by) {
        // 10번까지만
        if(num == 10) {
            return;
        }
        visited[rx][ry][bx][by] = true; // 방문 체크

        for(int i=0; i<4; i++) {
            // 구슬이 이동할 좌표 위치
            int _rx = rx;
            int _ry = ry;
            int _bx = bx;
            int _by = by;
            boolean redHole = false;
            boolean blueHole = false;
            // 빨간 구슬 이동
            while(board[_rx + dx[i]][_ry + dy[i]] != '#') {
                _rx += dx[i];
                _ry += dy[i];
                // 구멍이면
                if (board[_rx][_ry] == 'O') {
                    redHole = true;
                    break;
                }
            }
            // 파란 구슬 이동
            while(board[_bx + dx[i]][_by + dy[i]] != '#') {
                _bx += dx[i];
                _by += dy[i];
                // 구멍이면
                if (board[_bx][_by] == 'O') {
                    blueHole = true;
                    break;
                }
            }

            if(blueHole) { // 파란 구슬이 빠지면 실패
                continue;
            }

            if(redHole){ // 빨간 구슬만 빠지면 성공
                answer = Math.min(answer, num+1);
                success = true;
                break;
            }

            // 구슬의 위치 조정
            if(_bx == _rx && _by == _ry) {
                int redDist = Math.abs(rx - _rx) + Math.abs(ry - _ry);
                int blueDist = Math.abs(bx - _bx) + Math.abs(by - _by);
                // 파란 공이 앞에 있었던 경우 빨간 공을 뒤로
                if(redDist > blueDist) {
                    _rx -= dx[i];
                    _ry -= dy[i];
                } else { // 빨간 공이 앞에 있었던 경우 파란 공을 뒤로
                    _bx -= dx[i];
                    _by -= dy[i];
                }
            }

            // 두 구슬의 위치 상태가 처음 방문하는 상태인 경우만
            if(!visited[_rx][_ry][_bx][_by]) {
                board[rx][ry] = '.';
                board[bx][by] = '.';
                board[_rx][_ry] = 'R';
                board[_bx][_by] = 'B';

                dfs(num + 1, _rx, _ry, _bx, _by); // 기울이기 계속 진행

                // 백트래킹
                board[rx][ry] = 'R';
                board[bx][by] = 'B';
                board[_rx][_ry] = '.';
                board[_bx][_by] = '.';
            }
        }
        visited[rx][ry][bx][by] = false;
    }
}
