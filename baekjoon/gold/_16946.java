package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 16946 벽 부수고 이동하기 4
난이도: Gold 2
알고리즘: bfs
풀이 방법: 각 벽마다 bfs 를 시행하여 각각 이동할 수 있는 칸을 구해주면 시간 초과가 나온다. 중복 탐색이므로
        그래서 중복없이 딱 한번만 탐색할 수 있는 방법을 사용해야 한다.
        따라서 0이 인접해있는 칸(이어져있는 칸) 끼리 그룹화를 하여 몇개가 인접해있는지 bfs 로 구하고
        map에 그룹번호를 key로 하여 저장해준다. 그리고 각 0인 좌표의 칸에 그룹 번호를 기록한다.
        그 다음, 각각 벽인 칸 마다 상하좌우 인접한 칸 중 0인 칸을 탐색하여 각 칸의 그룹 번호에 해당하는
        수를 map에서 꺼내어 더한 후 10의 나머지를 구한다.
        알고리즘은 쉽게 생각했으나...굉장히 삽질한 문제..다른 사람들과 코드를 비교해보아도 차이를
        찾아내는 것이 매우 힘들었는데 원래는 ㄴ출력부분을 이중 for 문을 사용하여 배열을 출력해주었는데
        이것을 StringBuilder 사용하는 것으로 수정해주니까 시간 초과가 없어졌다......^^^^^
 */
public class _16946 {

    static int N, M;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Map<Integer, Integer> areaGroups = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        map = new int[N][M];
        visited = new int[N][M];

        // 맵 입력
        for(int i=0; i<N; i++) {
            String row = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }
        // 1번부터 그룹 번호 매기기
        int groupNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && visited[i][j] == 0) {
                    areaGroups.put(groupNum, bfs(i, j, groupNum));
                    groupNum++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) { // 벽이면 이동가능한 칸 수 구하기
                    Set<Integer> set = new HashSet<>(); // 같은 그룹인 칸을 제외시키기 위해 set 사용
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                            continue;
                        }
                        if(visited[nx][ny] != 0) { // 그룹 번호가 매겨져 있으면
                            set.add(visited[nx][ny]);
                        }
                    }

                    int totalCnt = 1;
                    for (int a : set) {
                        totalCnt += areaGroups.get(a);
                    }
                    sb.append(totalCnt % 10);
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int bfs(int x, int y, int num) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = num; // 현재 칸이 들어갈 그룹의 그룹 번호 기록
        int cnt = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                // 0인 칸이면 이동 가능
                if(map[nx][ny] == 0 && visited[nx][ny] == 0) {
                    visited[nx][ny] = num;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return cnt;
    }
}
