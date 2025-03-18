package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1938 통나무 옮기기
난이도: Gold 2
알고리즘: 구현, 그래프, bfs
풀이 방법: 최소 동작 횟수를 구하는 것이므로 bfs를 이용했다.
        통나무는 Log 클래스를 만들어 중간점 좌표와 배치 방향, 동작 횟수를 저장하고 객체로 관리하도록 한다.
        3차원 배열을 사용해 중간점 좌표를 기준으로 가로방향일 때, 세로방향일 때 나누어서 visited 체크를 해준다.
        bfs를 하기 위해 처음 Queue에 시작 통나무 중간점 좌표, 방향, 동작 횟수 0 을 넣는다.
        while 문 돌면서 큐에서 하나씩 꺼내 통나무가 최종 상태에 도달했는지 확인하고,
        도달하지 않았다면 상하좌우 이동 또는 회전을 시키면서 새로운 상태를 큐에 다시 집어넣는다.

 */
public class _1938 {

    static class Log {
        int x, y;
        int dir; // 방향
        int cnt; // 이동 횟수

        public Log(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static int N;
    static char[][] graph;
    static int[][] start, end;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        start = new int[3][2]; // 통나무 시작 위치
        end = new int[3][2]; // 최종 위치

        // 지형의 정보 입력
        int s = 0;
        int e = 0;
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<N; j++) {
                graph[i][j] = line.charAt(j);
                if(graph[i][j] == 'B') {
                    start[s++] = new int[]{i, j};
                } else if(graph[i][j] == 'E') {
                    end[e++] = new int[]{i, j};
                }
            }
        }
        System.out.println(bfs());

    }

    public static int bfs() {
        visited = new boolean[N][N][2]; // 통나무가 가로인 경우, 세로인 경우 나누어서 체크
        Queue<Log> q = new LinkedList<>();
        // 처음 통나무가 놓여진 방향 확인
        int dir;
        if (start[0][1] + 1 == start[1][1]) {
            dir = 0; // 가로 방향
        } else {
            dir = 1; // 세로 방향
        }
        // 통나무의 중간점, 놓아진 방향, 동작 횟수
        q.add(new Log(start[1][0], start[1][1], dir, 0));
        visited[start[1][0]][start[1][1]][dir] = true;

        while (!q.isEmpty()) {
            Log cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int curDir = cur.dir;
            int cnt = cur.cnt;
            // 최종 위치의 중간점과 일치한 경우
            if (cur.x == end[1][0] && cur.y == end[1][1]) {
                // 놓아진 방향도 일치하는 지 확인
                if (cur.dir == 0 && end[0][1] + 1 == end[1][1]) {
                    return cnt;
                }
                if (cur.dir == 1 && end[0][0] + 1 == end[1][0]) {
                    return cnt;
                }
            }
            // 상하좌우 이동
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (!checkMove(curDir, nx, ny, j)) { // 이동 가능 여부 체크
                    continue;
                }
                if (!visited[nx][ny][curDir]) {
                    visited[nx][ny][curDir] = true;
                    q.add(new Log(nx, ny, curDir, cnt + 1));
                }
            }

            if (checkRotate(x, y)) {
                if (curDir == 0 && !visited[x][y][1]) {
                    visited[x][y][1] = true;
                    q.add(new Log(x, y, 1, cnt + 1));
                } else if (curDir == 1 && !visited[x][y][0]) {
                    visited[x][y][0] = true;
                    q.add(new Log(x, y, 0, cnt + 1));
                }
            }
        }
        return 0;
    }

    public static boolean checkMove(int woodDir, int x, int y, int moveDir) {
        // 가로 방향일 때
        if(woodDir == 0) {
            if(moveDir < 2) { // 상하 이동
                if(x < 0 || x >= N) return false;
                if(graph[x][y-1] == '1' || graph[x][y] == '1' || graph[x][y+1] == '1')
                    return false;
            } else { // 좌우 이동
                if(y-1 < 0 || y+1 >= N) return false; // 통나무 끝이 범위를 벗어나는지 확인
                if(graph[x][y-1] == '1' || graph[x][y] == '1' || graph[x][y+1] == '1')
                    return false;
            }

        } else { // 세로 방향일 때
            if(moveDir < 2) { // 상하 이동
                if(x-1 < 0 || x+1 >= N) return false;
                if(graph[x-1][y] == '1' || graph[x][y] == '1' || graph[x+1][y] == '1')
                    return false;
            } else { // 좌우 이동
                if(y < 0 || y >= N) return false;
                if(graph[x-1][y] == '1' || graph[x][y] == '1' || graph[x+1][y] == '1')
                    return false;
            }
        }
        return true;
    }

    public static boolean checkRotate(int x, int y) {
        for(int i=x-1; i<=x+1; i++) {
            for(int j=y-1; j<=y+1; j++) {
                if(i < 0 || i >= N || j < 0 || j >= N) {
                    return false;
                }
                if(graph[i][j] == '1') { // 나무가 있다면 회전 불가
                    return false;
                }
            }
        }
        return true;
    }
}
