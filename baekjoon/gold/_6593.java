package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 6593 상범 빌딩
난이도: Gold 5
알고리즘: 그래프, bfs
풀이 방법: 최단 시간!!! bfs 를 이용하는 전형적인 문제이다.
        층마다 움직이는 것이 가능하므로 이동에서 위아래 방향 움직이는 것만 추가해주면 된다.
        주의할 것은 지나갈 수 없는 칸을 제외시킬 때 '.이면' 가는 것이 아니라 '#가 아니면' 으로 해야 한다.
        '.이면' 으로 필터링하면 E인 곳도 필터링되기 때문에 이런 문제에선 주의할 것
 */
public class _6593 {

    static int L, R, C;
    static int sl, sx, sy;
    static int el, ex, ey;
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {-1, 1, 0, 0, 0, 0};
    static int[] dl = {0, 0, 0, 0, -1, 1}; // 상, 하

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String[] line = br.readLine().split(" ");
            L = Integer.parseInt(line[0]);
            R = Integer.parseInt(line[1]);
            C = Integer.parseInt(line[2]);
            if(L == 0 && R == 0 && C == 0) {
                break;
            }
            char[][][] building = new char[L][R][C];

            for(int l=0; l<L; l++) {
                for(int i=0; i<R; i++) {
                    String row = br.readLine();
                    for(int j=0; j<C; j++) {
                        building[l][i][j] = row.charAt(j);
                        if(building[l][i][j] == 'S') {
                            sl = l;
                            sx = i;
                            sy = j;
                        } else if(building[l][i][j] == 'E') {
                            el = l;
                            ex = i;
                            ey = j;
                        }
                    }
                }
                br.readLine();
            }

            int result = escape(building);
            if(result == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + result + " minute(s).");
            }
        }
    }

    public static int escape(char[][][] building) {
        int answer = -1;
        boolean[][][] visited = new boolean[L][R][C];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sl, sx, sy, 0});
        visited[sl][sx][sy] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int l = cur[0];
            int x = cur[1];
            int y = cur[2];
            int time = cur[3];
            // 출구에 도착했다면
            if(l == el && x == ex && y == ey) {
                answer = time;
                break;
            }

            for(int i=0; i<6; i++) { // 동, 서, 남, 북, 상, 하 이동
                int nl = l + dl[i];
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nl < 0 || nl >= L || nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }

                if(!visited[nl][nx][ny] && building[nl][nx][ny] != '#') {
                    visited[nl][nx][ny] = true;
                    q.add(new int[]{nl, nx, ny, time+1});
                }
            }
        }
        return answer;
    }
}
