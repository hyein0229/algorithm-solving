package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 3055 탈출
난이도: Gold 4
알고리즘: 그래프, BFS
풀이 방법: 최소 시간 -> 최단 거리이므로 너비 우선 탐색을 사용하여 풀었다.
        1. 물을 비어있는 인접한 칸으로 모두 이동시킨 후
        2. 현재 큐에 있는 모든 칸을 하나씩 꺼내어 현재 칸에서 이동 가능한 칸을 이동 가능한 칸 리스트에 모두 삽입한다.
        3. 이동 가능한 칸 리스트에 있는 칸들을 큐에 삽입해주어 다음 상태로 갱신한 후 다시 1번 작업부터 반복한다.
        4. 이동 가능한 칸 리스트에 있는 칸 중 D라고 적힌 칸이 있다면 비버의 굴이므로 탐색 종료한다.
 */
public class _3055 {

    static Character[][] map;
    static int R, C;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new Character[R][C];
        boolean[][] visited = new boolean[R][C]; // 고슴도치 방문 체크
        Queue<int[]> water = new LinkedList<>();
        int x = 0;
        int y = 0;

        for(int i=0; i<R; i++) {
            String line = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'S') {
                    x = i;
                    y = j;
                }
                if(map[i][j] == '*') {
                    water.add(new int[]{i, j});
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 0});
        visited[x][y] = true;

        int answer = 0;
        boolean arrived = false;
        while(!arrived && !q.isEmpty()) {
            // 물 확장
            spread(water);
            // 고슴도치 이동
            List<int[]> moved = new ArrayList<>(); // 이동 가능한 칸 리스트
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                for(int i=0; i<4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                        continue;
                    }
                    // 물과 돌이 없고 방문하지 않은 칸이면 이동 가능
                    if(!visited[nx][ny] && map[nx][ny] != '*' && map[nx][ny] != 'X') {
                        visited[nx][ny] = true;
                        moved.add(new int[]{nx, ny, cur[2]+1});
                    }
                }
            }

            for(int[] m : moved) {
                // 비버의 굴에 도착하면
                if(map[m[0]][m[1]] == 'D') {
                    answer = m[2];
                    arrived = true;
                    break;
                }
                q.add(m);
            }
        }

        if(arrived) {
            System.out.println(answer);
        } else {
            System.out.println("KAKTUS");
        }
    }

    public static void spread(Queue<int[]> water) {
        Queue<int[]> next = new LinkedList<>(); // 물이 이동한 새로운 칸
        while(!water.isEmpty()) {
            int[] w = water.poll();
            for(int i=0; i<4; i++) {
                int nx = w[0] + dx[i];
                int ny = w[1] + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                // 빈 곳이면 물이 이동
                if(map[nx][ny] == '.') {
                    map[nx][ny] = '*';
                    next.add(new int[]{nx, ny});
                }
            }
        }
        while(!next.isEmpty()) { // 다음 상태 갱신
            water.add(next.poll());
        }
    }
}
