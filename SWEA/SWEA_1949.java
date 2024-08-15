import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
문제: 1949. [모의 SW 역량테스트] 등산로 조성
난이도: d4 예상
알고리즘: dfs + 백트래킹
풀이 방법: 1. 입력을 받으면서 가장 높은 봉우리가 있는 좌표값들을 리스트에 저장
         2. 리스트에서 1에서 저장한 봉우리 좌표들을 꺼내면서 해당 좌표로부터 dfs 탐색
         3. 현재 봉우리에서 상하좌우를 보면서 더 낮은 봉우리가 있는 경우 len + 1을 하면서 dfs 재귀 호출
         4. 만약, 다음 봉우리 높이가 같거나 크다면 최대 K 만큼 높이를 깎았을 때 현재 봉우리보다 낮아는지 확인 후, 낮아지면 flag = 1하고 호출
              (flag 가 0인 경우만 공사 가능)
         5. dfs 호출이 한 번 끝난 후엔 방문 체크와 공사한 경우 map 의 높이 상태를 원상 복구, 즉 백트래킹 필요
         6. 가장 높은 봉우리 모두에 대해 dfs 탐색하면서 최대 등산로 길이 답 갱신
 */
public class SWEA_1949 {

    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static List<int[]> targets = new ArrayList<>();
    static int maxLen = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            K = Integer.parseInt(line[1]);
            map = new int[N][N];
            visited = new boolean[N][N];
            maxLen = 0;
            targets.clear();

            // 지도 입력
            int max = 0;
            for(int i=0; i<N; i++) {
                line = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(line[j]);
                    if (map[i][j] > max) {
                        targets.clear();
                        targets.add(new int[]{i, j});
                        max = map[i][j];
                    } else if (map[i][j] == max) {
                        targets.add(new int[]{i, j});
                    }
                }
            }

            solve();
            System.out.println("#" + test_case + " " + maxLen);

        }
    }

    public static void solve() {
        for(int i=0; i< targets.size(); i++) {
            int[] pos = targets.get(i); // 가장 높은 봉우리의 위치
            int x = pos[0];
            int y = pos[1];
            trail(x, y, 1, false);
        }
    }

    public static void trail(int x, int y, int len, boolean flag) {
        visited[x][y] = true;
        maxLen = Math.max(maxLen, len);

        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if(!visited[nx][ny]) {
                if(map[x][y] > map[nx][ny]) { // 높이가 더 낮으면
                    trail(nx, ny, len+1, flag);
                }
                else if(!flag && map[x][y] > map[nx][ny] - K) { // 공사를 하고 갈 수 있다면
                    for(int h=1; h<=K; h++) { // 높이를 가능한 가장 최소한만 깎음
                        if(map[x][y] > map[nx][ny] - h) {
                            map[nx][ny] -= h; // h만큼 봉우리 높이를 깎음
                            trail(nx, ny, len+1, true);
                            map[nx][ny] += h;
                            break;
                        }
                    }
                }
            }
        }
        visited[x][y] = false;
    }
}
