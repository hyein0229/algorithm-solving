package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
문제: 2583 영역 구하기
난이도: Silver 1
알고리즘: 그래프 탐색, dfs/bfs
풀이 방법: 분리된 영역은 dfs 를 통해 연속된 칸을 탐색하고, 칸 하나의 넓이를 1로 하여 각각 칸의 개수를 세어주면 분리된 영역의 넓이를 구할 수 있다.
        주의할 것은 현재 문제에선 x, y 축처럼 왼쪽 하단의 좌표가 (0, 0)이므로 2차원 배열의 좌표가 왼쪽 상단 (0, 0)  인 것과 다르다는 것이다.
        배열 탐색 시 괜히 헷갈리지 않기 위해 왼쪽상단, 오른쪽하단 꼭지점을 다시 구해주고 직사각형에 해당하는 좌표의 칸을 1로 채워 주었다.
 */
public class _2583 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int m;
    static int n;
    static int k;
    static int area = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bufferedReader.readLine().split(" ");
        m = Integer.parseInt(line[0]); // y축
        n = Integer.parseInt(line[1]); // x축
        k = Integer.parseInt(line[2]);
        int[][] board = new int[m][n]; // mxn 모눈종이
        int[][] visited = new int[m][n]; // 방문 여부 배열

        // k개의 직사각형 좌표 입력
        for(int i=0; i<k; i++) {
            line = bufferedReader.readLine().split(" ");
            // 직사각형의 왼쪽 상단 꼭지점
            int s_x = Integer.parseInt(line[0]);
            int s_y = m - Integer.parseInt(line[3]);
            // 직사각형의 오른쪽 하단 꼭지점
            int e_x = Integer.parseInt(line[2]);
            int e_y = m - Integer.parseInt(line[1]);

            for(int y=s_y; y<e_y; y++) {
                for(int x=s_x; x<e_x; x++) {
                    board[y][x] = 1; // 직사각형이 있는 위치 1 표시
                }
            }
        }

        int cnt = 0;
        List<Integer> arrayList = new ArrayList<>();
        for(int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == 0 && visited[i][j] != 1) {
                    dfs(i, j, board, visited);
                    cnt += 1; // 분리된 영역 개수 카운트
                    arrayList.add(area); // 구한 영억의 넓이
                    area = 0;
                }
            }
        }

        Collections.sort(arrayList); // 오름차순 정렬
        System.out.println(cnt);
        System.out.println(String.join(" ", arrayList.toString()).replaceAll("[^0-9 ]", ""));

    }

    // dfs 를 이용해서 연속되는 칸을 탐색
    public static void dfs(int y, int x, int[][] board, int[][] visited) {

        visited[y][x] = 1; // 방문 체크
        area += 1; // 영역 넓이 +1

        // 인접한 칸 중 직사각형 내부가 아닌 칸 탐색
        for(int i=0; i<4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            // 좌표의 범위가 유효한지 체크
            if(ny < 0 || ny >= m || nx < 0 || nx >= n) {
                continue;
            }

            if (board[ny][nx] == 0 && visited[ny][nx] == 0) {
                dfs(ny, nx, board, visited);
            }
        }


    }
}
